package com.example.client.controller;

import com.example.client.domain.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class clientController {

    private final WebClient webClient;

    // GET http://localhost:8080/pet/{id}}
    @GetMapping("/{id}")
    public Mono<Pet> findById(@PathVariable String id) {
        return webClient.get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Pet.class);
    }

    // GET http://localhost:8080/pet
    @GetMapping()
    public Flux<Pet> findAll() {
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToFlux(Pet.class);
    }

    // POST http://localhost:8080/pet/create
    @PostMapping()
    public Flux<Pet> create(@RequestBody List<Pet> listPet) {
        return webClient
                .post()
                .uri("/create")
                .body(Flux.just(listPet), Pet.class)
                .retrieve()
                .bodyToFlux(Pet.class);
    }

    // PUT http://localhost:8080/pet/create
    @PutMapping()
    public Mono<Pet> update(@RequestBody Pet pet) {
        return webClient
                .put()
                .uri("/update")
                .body(Mono.just(pet), Pet.class)
                .retrieve()
                .bodyToMono(Pet.class);
    }
}