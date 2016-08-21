package com.araragi.pharma.compatibility.check.dao.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.araragi.pharma.compatibility.check.dao.CompatibilityCheckDao;
import com.araragi.pharma.compatibility.check.dao.ItemNotFoundException;
import com.araragi.pharma.compatibility.check.dao.neo4j.repository.ItemsRepository;

/**
 *
 */
@Component
@Profile("neo4j")
public class CompatibilityCheckNeo4jDao implements CompatibilityCheckDao {

    private final ItemsRepository repo;

    @Autowired
    public CompatibilityCheckNeo4jDao(final ItemsRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean areCompatible(final String firstItem, final String secondItem) throws ItemNotFoundException {
        return repo.countIncompatibleItems(firstItem, secondItem) == 0;
    }
}
