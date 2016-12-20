package com.araragi.pharma.compatibility.check.dao.neo4j.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.araragi.pharma.compatibility.check.dao.neo4j.ItemRelation;

/**
 *
 */
@NodeEntity
public class Item {

    @GraphId
    private Long id;

    private String name;

    @Relationship(type = ItemRelation.INCOMPATIBLE_WITH, direction = Relationship.OUTGOING)
    private Set<Item> incompatibleItems = new HashSet<>();

    public Item() {
    }

    public Item(final String name, final Set<Item> incompatibleItems) {
        this.name = name;
        this.incompatibleItems = incompatibleItems;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Item> getIncompatibleItems() {
        return incompatibleItems;
    }
}
