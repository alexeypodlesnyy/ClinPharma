package com.araragi.pharma.compatibility.check.dao.neo4j.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

import com.araragi.pharma.compatibility.check.dao.neo4j.domain.Item;

/**
 *
 */
@Component
@Profile("neo4j")
public interface ItemsRepository extends GraphRepository<Item> {

    @Query("MATCH (i1:Item {name:{0}})-[INCOMPATIBLE_WITH]-(i2:Item {name:{1}}) RETURN count(i1) as c")
    Integer countIncompatibleItems(String firstItem, String secondItem);

}