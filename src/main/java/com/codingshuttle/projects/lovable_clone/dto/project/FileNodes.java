package com.codingshuttle.projects.lovable_clone.dto.project;

import java.time.Instant;

public record FileNodes(
        String path,
        Instant modifiedAt,
        Long size,
        String type
) {
}
