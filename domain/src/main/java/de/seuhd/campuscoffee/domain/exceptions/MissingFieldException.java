package de.seuhd.campuscoffee.domain.exceptions;

/**
 * Generic exception thrown when an entity is missing a required field.
 * This represents a business rule violation: certain fields are mandatory.
 */
public class MissingFieldException extends RuntimeException {
    public MissingFieldException(Class<?> entityType, Long id, String fieldName) {
        super(entityType.getSimpleName() + " with ID " + id + " does not have the required fields. " +
                "Field '" + fieldName + "' is missing.");
    }
}
