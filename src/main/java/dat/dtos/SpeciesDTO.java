package dat.dtos;

import dat.entities.Species;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SpeciesDTO {

    private int id;
    private String speciesName;
    private String diet;
    private String habitat;

    public SpeciesDTO(Species species) {
        this.id = species.getId();
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