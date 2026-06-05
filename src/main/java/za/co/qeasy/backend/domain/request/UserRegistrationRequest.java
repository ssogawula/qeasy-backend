package za.co.qeasy.backend.domain.request;

public record UserRegistrationRequest(String username, String password, String email) {
}
