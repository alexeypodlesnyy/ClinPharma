package com.araragi.pharma.compatibility.check.dao;

/**
 *
 */
public interface CompatibilityCheckDao {

    boolean areCompatible(String firstItem, String secondItem);
}
