package dat.dtos;

import dat.entities.Species;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Setter
public class SpeciesDTO {

    private int speciesId;
    private String speciesName;
    private String diet;
    private String habitat;
    private Set<AnimalDTO> animals = new HashSet<>();

    public SpeciesDTO(Species species) {
        this.speciesId = species.getSpeciesId();
        this.speciesName = species.getSpeciesName();
        this.diet = species.getDiet();
        this.habitat = species.getHabitat();
    }

    public SpeciesDTO(String speciesName, String diet, String habitat) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
    }

    public static List<SpeciesDTO> toSpeciesDTOList(List<Species> speciesList) {
        return speciesList.stream().map(SpeciesDTO::new).collect(Collectors.toList());
    }
}