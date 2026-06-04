package com.codigo.webClient.service.impl;

import com.codigo.webClient.response.ReniecResponse;
import com.codigo.webClient.service.ReniecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReniecServiceImpl implements ReniecService {

    @Value("${api.reniec.token}")
    private String token;

    @Value("${decolecta.api.reniec.url}")
    private String reniecUrl;

    private final WebClient.Builder webClientBuilder;


    @Override
    public Mono<ReniecResponse> getInfoReniec(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            return Mono.error(new IllegalArgumentException("El DNI debe tener 8 digitos numericos"));
        }

        return webClientBuilder.build()
                .get()
                .uri(reniecUrl + "/dni?numero={dni}", dni)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new RuntimeException("Decolecta no encontro datos para el DNI: " + dni)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new RuntimeException("Decolecta no esta disponible en este momento")))
                .bodyToMono(ReniecResponse.class)
                .doOnNext(reniecResponse -> log.info("Respuesta del API de Reniec para DNI: {}", dni));
    }
}
