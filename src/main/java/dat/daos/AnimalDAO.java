package dat.daos;

import dat.dtos.AnimalDTO;
import dat.entities.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

public class AnimalDAO {

    private static AnimalDAO instance;

        private static EntityManagerFactory emf;

        public static AnimalDAO getInstance(EntityManagerFactory _emf) {
            if (instance == null) {
                emf = _emf;
                instance = new AnimalDAO();
            }
            return instance;
        }

        public AnimalDTO read(Integer integer) {
            try (EntityManager em = emf.createEntityManager()) {
                Animal animal = em.find(Animal.class, integer);
                return new AnimalDTO(animal);
            }
        }

        public List<AnimalDTO> readAll() {
            try (EntityManager em = emf.createEntityManager()) {
                TypedQuery<AnimalDTO> query = em.createQuery("SELECT new dat.dtos.AnimalDTO(h) FROM Animal h", AnimalDTO.class);
                return query.getResultList();
            }
        }


        public AnimalDTO create(AnimalDTO AnimalDTO) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                Animal Animal = new Animal(AnimalDTO);
                em.persist(Animal);
                em.getTransaction().commit();
                return new AnimalDTO(Animal);
            }
        }


        public AnimalDTO update(Integer integer, AnimalDTO AnimalDTO) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                Animal h = em.find(Animal.class, integer);
                h.setSpeciesId(AnimalDTO.getSpeciesId());
                h.setAge(AnimalDTO.getAge());
                h.setName(AnimalDTO.getName());
                Animal mergedAnimal = em.merge(h);
                em.getTransaction().commit();
                return mergedAnimal != null ? new AnimalDTO(mergedAnimal) : null;
            }

        }


        public void delete(Integer integer) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                Animal Animal = em.find(Animal.class, integer);
                if (Animal != null) {
                    em.remove(Animal);
                }
                em.getTransaction().commit();
            }
        }

        public boolean validatePrimaryKey(Integer integer) {
            try (EntityManager em = emf.createEntityManager()) {
                Animal Animal = em.find(Animal.class, integer);
                return Animal != null;
            }
        }
}
