package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Setter
    @Enumerated(EnumType.STRING) // Store the enum as a string
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
