package dat.daos;

import dat.dtos.ZooDTO;
import dat.entities.Zoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ZooDAO {

    private static ZooDAO instance;
    private static EntityManagerFactory emf;

    public static ZooDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ZooDAO();
        }
        return instance;
    }

    public ZooDTO read(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Zoo zoo = em.find(Zoo.class, id);
            return new ZooDTO(zoo);
        }
    }

    public List<ZooDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ZooDTO> query = em.createQuery("SELECT new dat.dtos.ZooDTO(z) FROM Zoo z", ZooDTO.class);
            return query.getResultList();
        }
    }

    public ZooDTO create(ZooDTO zooDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Zoo zoo = new Zoo(zooDTO);
            em.persist(zoo);
            em.getTransaction().commit();
            return new ZooDTO(zoo);
        }
    }

    public ZooDTO update(Integer id, ZooDTO zooDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Zoo zoo = em.find(Zoo.class, id);
            zoo.setName(zooDTO.getName());
            zoo.setLocation(zooDTO.getLocation());
            Zoo mergedZoo = em.merge(zoo);
            em.getTransaction().commit();
            return mergedZoo != null ? new ZooDTO(mergedZoo) : null;
        }
    }

    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Zoo zoo = em.find(Zoo.class, id);
            if (zoo != null) {
                em.remove(zoo);
            }
            em.getTransaction().commit();
        }
    }

    public boolean validatePrimaryKey(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Zoo zoo = em.find(Zoo.class, id);
            return zoo != null;
        }
    }
}