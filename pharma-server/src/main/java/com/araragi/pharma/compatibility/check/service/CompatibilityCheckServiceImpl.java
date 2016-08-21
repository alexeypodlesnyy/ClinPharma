package com.araragi.pharma.compatibility.check.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.araragi.pharma.compatibility.check.InternalExecutionException;
import com.araragi.pharma.compatibility.check.dao.CompatibilityCheckDao;
import com.araragi.pharma.compatibility.check.dao.ItemNotFoundException;

/**
 */
@Service
public class CompatibilityCheckServiceImpl implements CompatibilityCheckService {

    private final CompatibilityCheckDao compatibilityCheckDao;

    @Autowired
    public CompatibilityCheckServiceImpl(final CompatibilityCheckDao compatibilityCheckDao) {
        this.compatibilityCheckDao = compatibilityCheckDao;
    }

    @Override
    public CompatibilityResolution checkCompatibility(final String firstItem,
            final String secondItem) throws ItemNotFoundException {
        if (StringUtils.isEmpty(firstItem) || StringUtils.isEmpty(secondItem)) {
            throw new InternalExecutionException("Unable to check compatibility. Both items should be set.");
        }

        boolean itemsAreCompatible = compatibilityCheckDao.areCompatible(firstItem, secondItem);

        return new CompatibilityResolution(firstItem, secondItem, itemsAreCompatible);
    }
}
