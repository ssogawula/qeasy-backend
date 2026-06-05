package za.co.qeasy.backend.entities;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
}
