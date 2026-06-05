package za.co.qeasy.backend.services;

import reactor.core.publisher.Mono;
import za.co.qeasy.backend.domain.request.LoginRequest;
import za.co.qeasy.backend.domain.response.LoginResponse;

public interface AuthService {

    Mono<LoginResponse> login(LoginRequest loginRequest);
}
