package za.co.qeasy.backend.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.qeasy.backend.config.AppSecurityConfig;
import za.co.qeasy.backend.domain.request.UserRegistrationRequest;
import za.co.qeasy.backend.domain.response.UserRegistrationResponse;
import za.co.qeasy.backend.domain.response.UserResponse;
import za.co.qeasy.backend.services.UserService;

@WebFluxTest
@Import(AppSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockitoBean
    UserService userService;

    @Test
    @WithMockUser(username = "test", roles = "USER")
    void getAllUsers() {

        Mockito.when(userService.getUsers()).thenReturn(Flux.<UserResponse>empty());

        webTestClient.get().uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void should_register_user() {
        UserRegistrationRequest request = new UserRegistrationRequest("TestUser", "Test123y8728u", "test@gmail.co.za");

        Mockito.when(userService.createUser(request)).thenReturn(Mono.just(new UserRegistrationResponse("User created successfully")));
        webTestClient.post().uri("/api/v1/users/register")
                .bodyValue(request)
                .exchange()
                .expectBody()
                .jsonPath("$.message").isEqualTo("User created successfully");
    }
}
