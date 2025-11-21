package de.seuhd.campuscoffee.api.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ControllerUtils {
    /**
     * Builds the location URI for a newly created resource.
     * @param resourceId the ID of the created resource
     * @return the location URI
     */
    public static URI getLocation(Long resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
