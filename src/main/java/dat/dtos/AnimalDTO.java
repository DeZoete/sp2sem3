package dat.dtos;

import dat.entities.Animal;
import dat.entities.Zoo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter

public class AnimalDTO {
    private int animalId;
    private String animalName;
    private int animalAge;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.animalName = animal.getAnimalName();
        this.animalAge = animal.getAnimalAge();
    }

    public static List<AnimalDTO> toAnimalDTOList(List<Animal> animalList) {
        return animalList.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }
}