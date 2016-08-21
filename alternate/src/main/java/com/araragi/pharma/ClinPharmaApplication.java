package com.araragi.pharma;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.araragi.pharma.resetdb.DatabaseReset;

/**
 * Created by mnikitin
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ClinPharmaApplication {

    private final DatabaseReset databaseReset;

    @Autowired
    public ClinPharmaApplication(final DatabaseReset databaseReset) {
        this.databaseReset = databaseReset;
    }

    @PostConstruct
    public void postConstruct() {
        databaseReset.reset();
    }

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(ClinPharmaApplication.class, args);

        logOutBeanNames(applicationContext.getBeanDefinitionNames());
    }

    private static void logOutBeanNames(final String[] beanNames) {
        Arrays.sort(beanNames);

        System.out.println("Registered beans:");
        for (final String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
