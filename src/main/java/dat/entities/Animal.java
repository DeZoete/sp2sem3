package dat.entities;

import dat.dtos.AnimalDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false, unique = true)
    private int animalId;

    @Setter
    @Column(name = "animal_name", nullable = false)
    private String animalName;

    @Setter
    @Column(name = "animal_age", nullable = false)
    private int animalAge;

    @Setter
    @Column(name = "species_id", nullable = false)
    private int speciesId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "zoo_id", nullable = false)
    private Zoo zoo;

    public Animal(String animalName, int animalAge) {
        this.animalName = animalName;
        this.animalAge = animalAge;
    }

    public Animal(String animalName, int animalAge, int speciesId) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.speciesId = speciesId;
    }

    public Animal(AnimalDTO animalDTO) {
        this.animalId = animalDTO.getAnimalId();
        this.animalName = animalDTO.getAnimalName();
        this.animalAge = animalDTO.getAnimalAge();
    }
}
