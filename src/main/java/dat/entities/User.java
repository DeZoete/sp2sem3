/*
package dat.entities;

import dat.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Setter
    private String username;

    @Column
    @Setter
    private String password;

    @Column
    @Setter
    private Enum<Role> role;

    public User(String username, String password, Enum<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
    }
}

 */