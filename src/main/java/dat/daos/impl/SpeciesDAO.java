package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.SpeciesDTO;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SpeciesDAO implements IDAO<SpeciesDTO, Integer> {

    private static SpeciesDAO instance;
    private static EntityManagerFactory emf;

    public static SpeciesDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SpeciesDAO();
        }
        return instance;
    }

    public SpeciesDTO read(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Species species = em.find(Species.class, id);
            return new SpeciesDTO(species);
        }
    }

    public List<SpeciesDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<SpeciesDTO> query = em.createQuery("SELECT new dat.dtos.SpeciesDTO(s) FROM Species s", SpeciesDTO.class);
            return query.getResultList();
        }
    }

    public SpeciesDTO create(SpeciesDTO speciesDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Species species = new Species(speciesDTO);
            em.persist(species);
            em.getTransaction().commit();
            return new SpeciesDTO(species);
        }
    }

    public SpeciesDTO update(Integer id, SpeciesDTO speciesDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Species species = em.find(Species.class, id);
            species.setSpeciesName(speciesDTO.getSpeciesName());
            species.setDiet(speciesDTO.getDiet());
            species.setHabitat(speciesDTO.getHabitat());
            Species mergedSpecies = em.merge(species);
            em.getTransaction().commit();
            return mergedSpecies != null ? new SpeciesDTO(mergedSpecies) : null;
        }
    }

    public void delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Species species = em.find(Species.class, id);
            if (species != null) {
                em.remove(species);
            }
            em.getTransaction().commit();
        }
    }
    public List<AnimalDTO> getAnimalsBySpeciesId(Integer speciesId) {
        try (EntityManager em = emf.createEntityManager()) {
            // Query to retrieve animals by species ID
            TypedQuery<AnimalDTO> query = em.createQuery(
                    "SELECT new dat.dtos.AnimalDTO(a) FROM Animal a WHERE a.speciesId = :speciesId",
                    AnimalDTO.class);
            query.setParameter("speciesId", speciesId); // Setting the species ID parameter
            return query.getResultList(); // Returning the list of AnimalDTOs
        }
    }
/*
    public Zoo getZooBySpeciesId(Integer speciesId) {
        try (EntityManager em = emf.createEntityManager()) {
            // Retrieve the species by its ID
            Species species = em.find(Species.class, speciesId);
            // Return the Zoo object associated with the species or null if not found
            return species != null ? species.getZoo() : null; // Change to species.getZoo() for Zoo object
        }
    }*/

    public boolean validatePrimaryKey(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Species species = em.find(Species.class, id);
            return species != null;
        }
    }
}