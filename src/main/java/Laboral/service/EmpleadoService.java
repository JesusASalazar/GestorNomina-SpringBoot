package Laboral.service;

import Laboral.model.entities.Empleado;
import Laboral.model.entities.Nomina;
import Laboral.model.entities.SueldoBase;
import Laboral.repository.EmpleadoRepository;
import Laboral.repository.NominaRepository;
import Laboral.repository.SueldoBaseRepository;
import Laboral.rules.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmpleadoService extends LaboralService<EmpleadoRepository, Empleado, String> {
    public final NominaRepository nr;
    public final SueldoBaseRepository sbr;

    @Autowired
    public EmpleadoService(EmpleadoRepository repository, NominaRepository nr, SueldoBaseRepository sbr) {
        super(repository);
        this.nr = nr;
        this.sbr = sbr;
    }

    @Override
    @Transactional
    public List<Empleado> saveAll(List<Empleado> empleados) {
        List<Empleado> guardados = super.saveAll(empleados); // Guardar empleados primero

        for (Empleado e : guardados) {
            // Obtener la base salarial según la categoría
            SueldoBase base = sbr.findById(e.getCategoria())
                    .orElseThrow(() -> new RuntimeException("No existe base salarial para la categoría " + e.getCategoria()));

            // Crear nómina
            Nomina nomina = Nomina.builder()
                    .empleado(e)
                    .baseSalarial(base)
                    .sueldo(base.getBaseSalarial() + 5000 * e.getAnyos()) // sueldo inicial = base
                    .build();

            nr.save(nomina);
        }

        return guardados;
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        Empleado saved = super.save(empleado); // Guardar empleados primero


        SueldoBase base = sbr.findById(saved.getCategoria())
                .orElseThrow(() -> new RuntimeException("No existe base salarial para la categoría " + empleado.getCategoria()));

        // Crear nómina
        Nomina nomina = Nomina.builder()
                .empleado(saved)
                .baseSalarial(base)
                .sueldo(base.getBaseSalarial() + 5000 * saved.getAnyos()) // sueldo inicial = base
                .build();

        nr.save(nomina);

        return saved;
    }

    public Empleado updateCategoria(String dni, Integer categoria) {
        Empleado e = repository.findById(dni).orElseThrow(EntityNotFoundException::new);
        e.setCategoria(categoria);
        return this.save(e);
    }

    public Empleado updateAnyos(String dni, Integer anyos) {
        Empleado e = repository.findById(dni)
                .orElseThrow(EntityNotFoundException::new);
        e.setAnyos(anyos);
        return this.save(e);
    }
}
