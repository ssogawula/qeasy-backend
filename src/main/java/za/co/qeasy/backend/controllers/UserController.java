package za.co.qeasy.backend.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.qeasy.backend.domain.request.UserRegistrationRequest;
import za.co.qeasy.backend.domain.response.UserRegistrationResponse;
import za.co.qeasy.backend.domain.response.UserResponse;
import za.co.qeasy.backend.services.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Mono<ResponseEntity<Flux<UserResponse>>> getAllUsers() {
        return Mono.just(ResponseEntity.ok(userService.getUsers()));

    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserRegistrationResponse>> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return userService.createUser(userRegistrationRequest)
                .map(savedUser -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(savedUser));
    }
}
