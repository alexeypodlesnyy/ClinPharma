package com.araragi.pharma.compatibility.check.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class CompatibilityCheckJdbcDao implements CompatibilityCheckDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompatibilityCheckJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String QUERY_COMPATIBILITY =
            "SELECT compatible FROM `compatibility` WHERE id_first_item=? AND id_second_item=?";

    @Override
    public boolean areCompatible(final String firstItem, final String secondItem) {
        return jdbcTemplate.queryForObject(QUERY_COMPATIBILITY, Boolean.class, firstItem, secondItem);
    }
}
