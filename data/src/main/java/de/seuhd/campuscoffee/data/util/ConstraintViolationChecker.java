package de.seuhd.campuscoffee.data.util;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * Utility class for checking database constraint violations.
 * Provides robust checking of both exception messages and root causes.
 */
public final class ConstraintViolationChecker {

    /**
     * Checks if the exception is due to a specific constraint violation.
     * Checks both the exception message and root cause for the constraint name.
     *
     * @param exception              the DataIntegrityViolationException to check
     * @param constraintName the database constraint name to look for
     * @return true if the exception is due to the specified constraint violation
     */
    public static boolean isConstraintViolation(DataIntegrityViolationException exception, String constraintName) {
        // Check the exception message for the constraint name
        String message = exception.getMessage();
        if (message != null && message.contains(constraintName)) {
            return true;
        }

        // Also check root cause for constraint violations
        Throwable cause = exception.getRootCause();
        if (cause != null) {
            String causeMessage = cause.getMessage();
            return causeMessage != null && causeMessage.contains(constraintName);
        }

        return false;
    }
}
