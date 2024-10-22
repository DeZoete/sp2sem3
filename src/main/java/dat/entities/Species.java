package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "species_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "species_name", nullable = false)
    private String speciesName;

    @Setter
    @Column(name = "diet", nullable = false)
    private String diet;

    @Setter
    @Column(name = "habitat", nullable = false)
    private String habitat;

    public Species(String speciesName, String diet, String habitat) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
    }
}
