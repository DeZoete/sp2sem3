package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.SpeciesDTO;
import dat.dtos.ZooDTO;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class ZooDAO implements IDAO<ZooDTO, Integer> {

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

    public ZooDTO update(Integer id, ZooDTO zooDTO){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Zoo zoo = em.find(Zoo.class, id);
            if (zoo != null) {
                zoo.setZooName(zooDTO.getZooName());
                zoo.setZooLocation(zooDTO.getZooLocation());
            }
            em.getTransaction().commit();
            return new ZooDTO(zoo);
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

    public List<AnimalDTO> readAllAnimals(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<AnimalDTO> query = em.createQuery("SELECT new dat.dtos.AnimalDTO(a) FROM Animal a WHERE a.zoo.id = :zooId", AnimalDTO.class);
            query.setParameter("zooId", id);
            return query.getResultList();
        }
    }

    public List<SpeciesDTO> readAllSpecies(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<SpeciesDTO> query = em.createQuery(
                    "SELECT DISTINCT new dat.dtos.SpeciesDTO(s) " +
                            "FROM Species s JOIN s.animals a " +
                            "WHERE a.zoo.id = :zooId", SpeciesDTO.class);
            query.setParameter("zooId", id);
            return query.getResultList();
        }
    }
}