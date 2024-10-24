package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.SpeciesDTO;
import dat.entities.Animal;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

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
                    h.setAnimalAge(AnimalDTO.getAnimalAge());
                    h.setAnimalName(AnimalDTO.getAnimalName());
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
    public AnimalDTO readById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Animal animal = em.find(Animal.class, id); // Retrieve the animal
            return animal != null ? new AnimalDTO(animal) : null;
        }
    }

    public Zoo getZooByAnimalId(Integer animalId) {
        try (EntityManager em = emf.createEntityManager()) {
            Animal animal = em.find(Animal.class, animalId); // Retrieve the animal
            return animal != null ? animal.getZoo() : null; // Return the Zoo object or null if not found
        }
    }

        public boolean validatePrimaryKey(Integer integer) {
            try (EntityManager em = emf.createEntityManager()) {
                Animal Animal = em.find(Animal.class, integer);
                return Animal != null;
            }
        }

}
