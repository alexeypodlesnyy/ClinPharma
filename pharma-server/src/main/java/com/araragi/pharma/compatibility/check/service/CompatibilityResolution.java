package com.araragi.pharma.compatibility.check.service;

/**
 *
 */
public class CompatibilityResolution {

    private final String firstItem;
    private final String secondItem;
    private final boolean compatible;

    public CompatibilityResolution(final String firstItem, final String secondItem, final boolean compatible) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
        this.compatible = compatible;
    }

    public String getFirstItem() {
        return firstItem;
    }

    public String getSecondItem() {
        return secondItem;
    }

    public boolean isCompatible() {
        return compatible;
    }

}
