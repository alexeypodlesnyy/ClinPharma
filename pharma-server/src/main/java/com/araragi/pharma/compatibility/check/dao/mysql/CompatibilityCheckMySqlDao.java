package com.araragi.pharma.compatibility.check.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.araragi.pharma.compatibility.check.dao.CompatibilityCheckDao;

/**
 *
 */
@Component
@Profile("mysql")
public class CompatibilityCheckMySqlDao implements CompatibilityCheckDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompatibilityCheckMySqlDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String QUERY_COMPATIBILITY =
            "SELECT compatible FROM `compatibility` WHERE id_first_item=? AND id_second_item=?";

    @Override
    public boolean areCompatible(final String firstItem, final String secondItem) {
        return jdbcTemplate.queryForObject(QUERY_COMPATIBILITY, Boolean.class, firstItem, secondItem);
    }
}
