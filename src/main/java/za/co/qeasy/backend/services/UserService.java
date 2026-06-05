package za.co.qeasy.backend.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.qeasy.backend.domain.response.UserRegistrationResponse;
import za.co.qeasy.backend.domain.request.UserRegistrationRequest;
import za.co.qeasy.backend.domain.response.UserResponse;

public interface UserService {

    Flux<UserResponse> getUsers();

    Mono<UserRegistrationResponse> createUser(UserRegistrationRequest userRegistrationRequest);
}
