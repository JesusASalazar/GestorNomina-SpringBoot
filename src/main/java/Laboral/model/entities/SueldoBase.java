package Laboral.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "bases_salariales")
public class SueldoBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoria;
    @Column(name = "base", nullable = false)
    private Integer baseSalarial;
    @OneToMany(mappedBy = "baseSalarial")
    private List<Nomina> nominas;
}
