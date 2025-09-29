package Laboral.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "empleados")
public class Empleado extends Persona {

    @Column(nullable = false, columnDefinition = "integer default 1")
	private Integer categoria;

    @Column(nullable = false, columnDefinition = "integer default 0")
    public Integer anyos;

    @OneToOne(mappedBy = "empleado")
    public Nomina nomina;
	
}
