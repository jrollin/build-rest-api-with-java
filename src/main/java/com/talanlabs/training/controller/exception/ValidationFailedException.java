package com.talanlabs.training.controller.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationFailedException extends RuntimeException {

    private Set<ConstraintViolation<?>> violations;

    public ValidationFailedException(Set<ConstraintViolation<?>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<?>> getViolations() {
        return violations;
    }
}
