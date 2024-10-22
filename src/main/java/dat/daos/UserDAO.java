package dat.daos;

import dat.dtos.UserDTO;
import dat.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private static EntityManagerFactory emf;

    public static UserDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserDAO();
        }
        return instance;
    }

    public UserDTO read(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            User user = em.find(User.class, id);
            return new UserDTO(user);
        }
    }

    public List<UserDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<UserDTO> query = em.createQuery("SELECT new dat.dtos.UserDTO(u) FROM User u", UserDTO.class);
            return query.getResultList();
        }
    }

    public UserDTO create(UserDTO userDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = new User(userDTO);
            em.persist(user);
            em.getTransaction().commit();
            return new UserDTO(user);
        }
    }

    public UserDTO update(Integer id, UserDTO userDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            User mergedUser = em.merge(user);
            em.getTransaction().commit();
            return mergedUser != null ? new UserDTO(mergedUser) : null;
        }
    }

    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        }
    }

    public boolean validatePrimaryKey(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            User user = em.find(User.class, id);
            return user != null;
        }
    }
}