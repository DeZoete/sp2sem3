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


    public Species(String speciesName, String diet, String habitat, int speciesId) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
        this.speciesId = speciesId;
    }

    public Species(SpeciesDTO speciesDTO) {
        this.speciesId = speciesDTO.getSpeciesId();
        this.speciesName = speciesDTO.getSpeciesName();
        this.diet = speciesDTO.getDiet();
        this.habitat = speciesDTO.getHabitat();
    }

}
