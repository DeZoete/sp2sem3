package dat.dtos;

import dat.entities.Role;
import dat.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO{

    private int id;
    private String username;
    private String password;
    private Enum<Role> role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public UserDTO(String username, String password, Role role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static List<UserDTO> toUserDTOList(List<User> Users) {
        return Users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
