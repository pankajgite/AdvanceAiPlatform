package com.codingshuttle.projects.lovable_clone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface AiGenerationService {
    Flux<String> streamResponse(String message, Long aLong);
}
