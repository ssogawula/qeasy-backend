package za.co.qeasy.backend.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import za.co.qeasy.backend.config.AppSecurityConfig;
import za.co.qeasy.backend.services.UserService;

@WebFluxTest
@Import(AppSecurityConfig.class)
public class SpringSecurityIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @MockitoBean
    UserService userService;


    @Test
    void should_rejecct_unauthorized_request() {

        webTestClient.get().uri("/api/v1/users")
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
