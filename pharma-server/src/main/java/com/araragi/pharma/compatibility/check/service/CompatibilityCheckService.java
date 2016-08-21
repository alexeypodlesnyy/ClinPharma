package com.araragi.pharma.compatibility.check.service;

import com.araragi.pharma.compatibility.check.dao.ItemNotFoundException;

/**
 * Created by mnikitin
 */
public interface CompatibilityCheckService {

    CompatibilityResolution checkCompatibility(String firstItem, String secondItem) throws ItemNotFoundException;
}
