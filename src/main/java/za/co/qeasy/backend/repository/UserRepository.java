package za.co.qeasy.backend.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import za.co.qeasy.backend.entities.User;

public interface UserRepository extends R2dbcRepository<User, Long> {
}
