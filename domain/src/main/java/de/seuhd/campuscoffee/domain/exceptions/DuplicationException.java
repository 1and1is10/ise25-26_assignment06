package de.seuhd.campuscoffee.domain.exceptions;

/**
 * Generic exception thrown when attempting to create or update an entity with a value that already exists.
 * This represents a business rule violation: certain fields must be unique.
 */
public class DuplicationException extends RuntimeException {

    /**
     * Creates an exception for a duplicate entity field.
     *
     * @param entityType the type of entity (e.g., "Pos", "User")
     * @param fieldName  the field name that must be unique (e.g., "name", "login name", "email address")
     * @param fieldValue the duplicate value
     */
    public DuplicationException(Class<?> entityType, String fieldName, String fieldValue) {
        super(entityType.getSimpleName() + " with " + fieldName + " '" + fieldValue + "' already exists.");
    }
}
