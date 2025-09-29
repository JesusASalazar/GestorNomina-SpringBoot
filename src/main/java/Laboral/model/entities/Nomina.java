package Laboral.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "nominas")
public class Nomina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_nom_emp"))
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "base_salarial", nullable = false, foreignKey = @ForeignKey(name = "fk_nom_base"))
    private SueldoBase baseSalarial;
    private Integer sueldo;
}
