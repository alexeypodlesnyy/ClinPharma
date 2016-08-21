package com.araragi.pharma.compatibility.check.service;

/**
 * Created by mnikitin
 */
public interface CompatibilityCheckService {

    CompatibilityResolution checkCompatibility(String firstItem, String secondItem);
}
