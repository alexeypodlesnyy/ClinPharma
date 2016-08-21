package com.araragi.pharma.resetdb;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.araragi.pharma.compatibility.check.dao.neo4j.Neo4jConfig;
import com.araragi.pharma.compatibility.check.dao.neo4j.domain.Item;

/**
 *
 */
@Component
@Profile("neo4j")
public class Neo4jDatabaseReset implements DatabaseReset {

    private final Neo4jConfig persistenceContext;

    @Autowired
    public Neo4jDatabaseReset(final Neo4jConfig persistenceContext) {
        this.persistenceContext = persistenceContext;
    }

    @Override
    public void reset() {
        /*
        * This Neo4j database is embedded (in-memory), so no need to clean up (drop) the persistence.
        * We only need to populate test data to play with.
        * */

        /*
        * Let's insert couple of fully compatible items and two incompatible to each other.
        * */
        Stream.of(
                new Item("aaa", new HashSet<>()),
                new Item("bbb", new HashSet<>()),
                new Item("xxx", Collections.singleton(new Item("yyy", new HashSet<>())))
        )
              .forEach(item -> persistenceContext.getSessionFactory().openSession().save(item));
    }
}
