package dat.entities;

import dat.dtos.SpeciesDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "species_id", nullable = false, unique = true)
    private int speciesId;


    @Setter
    @Column(name = "species_name", nullable = false, unique = true)
    private String speciesName;

    @Setter
    @Column(name = "diet", nullable = false)
    private String diet;

    @Setter
    @Column(name = "habitat", nullable = false)
    private String habitat;

    @OneToMany(mappedBy = "species", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    public Species(String speciesName, String diet, String habitat) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
    }

    public Species(SpeciesDTO speciesDTO) {
        this.speciesId = speciesDTO.getSpeciesId();
        this.speciesName = speciesDTO.getSpeciesName();
        this.diet = speciesDTO.getDiet();
        this.habitat = speciesDTO.getHabitat();
        if (speciesDTO.getAnimals() != null) {
            speciesDTO.getAnimals().forEach(animalDTO -> animals.add(new Animal(animalDTO)));
        }

    }

    // Bi-directional relationship for all animals in a zoo
    public void setAnimals(Set<Animal> animals) {
        if(animals != null) {
            this.animals = animals;
            for (Animal animal : animals) {
                animal.setSpecies(this);
            }
        }
    }

    // Bi-directional relationship
    public void addAnimal(Animal animal) {
        if ( animal != null) {
            this.animals.add(animal);
            animal.setSpecies(this);
        }
    }


}
