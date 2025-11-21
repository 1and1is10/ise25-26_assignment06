package de.seuhd.campuscoffee.domain.exceptions;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Base exception thrown when an entity fails Bean Validation.
 */
@Getter
public class ValidationException extends RuntimeException {
    private final Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super(formatViolations(violations));
        this.violations = violations;
    }

    /**
     * Formats constraint violations into a readable message.
     */
    private static String formatViolations(Set<? extends ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
    }
}
