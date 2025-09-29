package Laboral.repository;

import Laboral.model.entities.Empleado;
import Laboral.model.entities.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> {
    Optional<Nomina> findByEmpleado(Empleado empleado);
}
