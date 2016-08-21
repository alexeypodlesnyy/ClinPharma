package com.araragi.pharma.compatibility.check.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.araragi.pharma.compatibility.check.InternalExecutionException;
import com.araragi.pharma.compatibility.check.service.CompatibilityCheckService;
import com.araragi.pharma.compatibility.check.service.CompatibilityResolution;


/**
 */
@RestController()
@RequestMapping("/compatibility")
public class CompatibilityCheckRestController implements CompatibilityCheckController {

    private static final Logger LOG = LoggerFactory.getLogger(CompatibilityCheckRestController.class);

    private final CompatibilityCheckService compatibilityCheckService;

    @Autowired
    public CompatibilityCheckRestController(final CompatibilityCheckService compatibilityCheckService) {
        this.compatibilityCheckService = compatibilityCheckService;
    }

    /**
     * Handles internal errors exceptions.
     *
     * @param e service exception
     */
    @Required
    @ExceptionHandler(InternalExecutionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleServiceExceptions(final InternalExecutionException e) {
        LOG.error("Internal error has been occurred.", e);
    }

    /**
     * /compatibility/check?firstItem=foo&secondItem=bar
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/check"
    )
    public CompatibilityResolution handleCompatibilityCheck(
            final @RequestParam String firstItem,
            final @RequestParam String secondItem
    ) {
        return compatibilityCheckService.checkCompatibility(firstItem, secondItem);
    }
}
