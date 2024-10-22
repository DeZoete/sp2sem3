package dat.entities;

import dat.dtos.AnimalDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    @Column
    private int speciesId;
    @ManyToOne
    private Zoo zoo;
    @Setter
    @Column
    private String name;
    @Setter
    @Column
    private int age;

    public Animal(int id, int speciesId, Zoo zoo, String name, int age) {
        this.id = id;
        this.speciesId = speciesId;
        this.zoo = zoo;
        this.name = name;
        this.age = age;
    }

    public Animal(AnimalDTO animalDTO) {
        this.id = animalDTO.getId();
        this.speciesId = animalDTO.getSpeciesId();
        this.zoo = animalDTO.getZoo();
        this.name = animalDTO.getName();
        this.age = animalDTO.getAge();
    }
}
