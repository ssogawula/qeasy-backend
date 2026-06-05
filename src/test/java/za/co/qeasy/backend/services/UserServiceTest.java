package za.co.qeasy.backend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.qeasy.backend.domain.request.UserRegistrationRequest;
import za.co.qeasy.backend.domain.response.UserRegistrationResponse;
import za.co.qeasy.backend.entities.User;
import za.co.qeasy.backend.repository.UserRepository;
import za.co.qeasy.backend.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    UserService userService;

    User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@gmail.com");

        userService = new UserServiceImpl(userRepository,passwordEncoder);
    }

    @Test
    void should_create_user() {
        UserRegistrationRequest request = new UserRegistrationRequest("test", "test", "test@gmail.com");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(Mono.just(user));

        StepVerifier.create(userService.createUser(request))
                .expectNext(new UserRegistrationResponse("User created successfully"))
                .verifyComplete();
    }

}
