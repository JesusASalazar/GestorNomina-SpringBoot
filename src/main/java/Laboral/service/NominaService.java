package Laboral.service;

import Laboral.model.entities.Empleado;
import Laboral.model.entities.Nomina;
import Laboral.repository.NominaRepository;
import Laboral.rules.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominaService extends LaboralService<NominaRepository, Nomina, Long> {
    @Autowired
    public NominaService(NominaRepository repository) {
        super(repository);
    }

    public Nomina findByEmpleado(Empleado empleado){
        return repository.findByEmpleado(empleado).orElseThrow(EntityNotFoundException::new);
    }
}
