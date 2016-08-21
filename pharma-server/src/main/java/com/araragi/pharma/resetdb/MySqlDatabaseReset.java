package com.araragi.pharma.resetdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Profile("mysql")
public class MySqlDatabaseReset implements DatabaseReset {

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS `compatibility`";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE `compatibility` (id_first_item VARCHAR(100), id_second_item VARCHAR(100), compatible TINYINT)";

    private static final String SQL_INSERT_COMPATIBLE =
            "INSERT INTO `compatibility` VALUES (\"xxx\", \"yyy\", 1)";

    private static final String SQL_INSERT_UNCOMPATIBLE =
            "INSERT INTO `compatibility` VALUES (\"aaa\", \"bbb\", 0)";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlDatabaseReset(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void reset() {
        jdbcTemplate.update(SQL_DROP_TABLE);
        jdbcTemplate.update(SQL_CREATE_TABLE);
        jdbcTemplate.update(SQL_INSERT_COMPATIBLE);
        jdbcTemplate.update(SQL_INSERT_UNCOMPATIBLE);
    }
}
