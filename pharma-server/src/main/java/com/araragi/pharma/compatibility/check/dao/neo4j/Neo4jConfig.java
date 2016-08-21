package com.araragi.pharma.compatibility.check.dao.neo4j;

import java.io.File;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "com.araragi.pharma.compatibility.check.dao.neo4j.repository")
@EnableTransactionManagement
@ComponentScan()
public class Neo4jConfig extends Neo4jConfiguration {

    public static final String NEO4J_DATABASE_DRIVER = "org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver";
    public static final String DATABASE_TEMP_FILE_URI =
            "file://" + System.getProperty("java.io.tmpdir") + File.separator + "items.db";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        final org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
              .setDriverClassName(NEO4J_DATABASE_DRIVER)
              .setURI(DATABASE_TEMP_FILE_URI);

        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(
                getConfiguration(),
                "com.araragi.pharma.compatibility.check.dao.neo4j.domain");
    }

}
