package de.seuhd.campuscoffee.domain.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record User (
        @Nullable Long id,
        @Nullable LocalDateTime createdAt,
        @Nullable LocalDateTime updatedAt,
        @NonNull String loginName,
        @NonNull String emailAddress,
        @NonNull String firstName,
        @NonNull String lastName
) implements Serializable { // serializable to allow cloning (see TestFixtures class).
    @Serial
    private static final long serialVersionUID = 1L;
}
