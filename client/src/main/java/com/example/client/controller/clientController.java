package com.example.client.controller;

import com.example.client.domain.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class clientController {

    private final WebClient webClient;

    // GET http://localhost:8080/pet/{id}
    // this is non-blocking case. because the postman must not wait return data from server.
    @GetMapping("/{id}")
    public void findById(@PathVariable String id) {
        Mono<Pet> pet =  webClient.get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Pet.class).log();
        System.out.println(pet);
        // the return immediately is ack of request. It means this request sent successful to server. It is not return data of server
    }

    // GET http://localhost:8080/pet
    // this is blocking case. because the postman must wait return data from server (10 seconds)
    @GetMapping()
    public Flux<Pet> findAll() {
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToFlux(Pet.class).log();
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