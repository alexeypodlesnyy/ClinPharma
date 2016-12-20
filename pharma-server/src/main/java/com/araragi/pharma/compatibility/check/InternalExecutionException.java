package com.araragi.pharma.compatibility.check;

/**
 * General class for all internal service-specific exceptions.
 */
public class InternalExecutionException extends RuntimeException {

    public InternalExecutionException(final String errorMessage) {
        super(errorMessage);
    }
}
