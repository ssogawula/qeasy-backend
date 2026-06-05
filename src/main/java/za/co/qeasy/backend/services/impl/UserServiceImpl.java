package za.co.qeasy.backend.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.qeasy.backend.domain.UserRegistrationRequest;
import za.co.qeasy.backend.domain.UserRegistrationResponse;
import za.co.qeasy.backend.domain.UserResponse;
import za.co.qeasy.backend.entities.User;
import za.co.qeasy.backend.repository.UserRepository;
import za.co.qeasy.backend.services.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Flux<UserResponse> getUsers() {
        return userRepository.findAll()
                .map(user -> new UserResponse());
    }

    @Override
    public Mono<UserRegistrationResponse> createUser(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setUsername(userRegistrationRequest.username());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.password()));
        user.setEmail(userRegistrationRequest.email());

        return userRepository.save(user)
                .map(savedUser -> new UserRegistrationResponse("User created successfully"));
    }
}
