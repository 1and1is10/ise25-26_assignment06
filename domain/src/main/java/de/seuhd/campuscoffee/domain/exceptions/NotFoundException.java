package de.seuhd.campuscoffee.domain.exceptions;

/**
 * Generic exception thrown when an entity is not found in the database.
 * Supports finding by ID or by a specific field name and value.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Creates an exception for an entity not found by ID.
     *
     * @param entityType the type of entity (e.g., "Pos", "User")
     * @param id         the ID that was not found
     */
    public NotFoundException(Class<?> entityType, Long id) {
        super(entityType.getSimpleName() + " with ID " + id + " does not exist.");
    }

    /**
     * Creates an exception for an entity not found by a specific field.
     *
     * @param entityType the type of entity (e.g., "Pos", "User")
     * @param fieldName  the field name (e.g., "name", "login name")
     * @param fieldValue the field value that was not found
     */
    public NotFoundException(Class<?> entityType, String fieldName, String fieldValue) {
        super(entityType.getSimpleName() + " with " + fieldName + " '" + fieldValue + "' does not exist.");
    }
}
