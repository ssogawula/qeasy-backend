package za.co.qeasy.backend.services;

import reactor.core.publisher.Mono;
import za.co.qeasy.backend.domain.LoginRequest;
import za.co.qeasy.backend.domain.LoginResponse;

public interface AuthService {

    Mono<LoginResponse> login(LoginRequest loginRequest);
}
