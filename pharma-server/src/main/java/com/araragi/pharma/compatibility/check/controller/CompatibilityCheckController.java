package com.araragi.pharma.compatibility.check.controller;

import com.araragi.pharma.compatibility.check.service.CompatibilityResolution;

/**
 * Created by mnikitin.
 */
public interface CompatibilityCheckController {

    CompatibilityResolution handleCompatibilityCheck(String firstItem, String secondItem);
}
