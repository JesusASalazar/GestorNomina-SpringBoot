package Laboral.model.entities;

import Laboral.library.enums.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Persona {

    @Id
    public String dni;
    @Column(nullable = false, length = 25)
	public String nombre;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Sexo sexo;


}
