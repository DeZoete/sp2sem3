package dat.dtos;

import dat.config.HibernateConfig;
import dat.daos.impl.ZooDAO;
import dat.entities.Animal;
import dat.entities.Zoo;
import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AnimalDTO {
    private int animalId;
    private String animalName;
    private int animalAge;
    private int speciesId;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.animalName = animal.getAnimalName();
        this.animalAge = animal.getAnimalAge();
        this.speciesId = animal.getSpeciesId();
    }
    public AnimalDTO(String animalName, int animalAge, int speciesId) {
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.speciesId = speciesId;
    }

    public static List<AnimalDTO> toAnimalDTOList(List<Animal> animalList) {
        return animalList.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }
}