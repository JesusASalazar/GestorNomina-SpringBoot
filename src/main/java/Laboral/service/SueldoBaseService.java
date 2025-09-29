package Laboral.service;

import Laboral.model.entities.SueldoBase;
import Laboral.repository.SueldoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SueldoBaseService extends LaboralService<SueldoBaseRepository, SueldoBase, Integer> {
    @Autowired
    public SueldoBaseService(SueldoBaseRepository repository) {
        super(repository);
    }

    public Integer findBaseSalarialById(Integer categoria){
        return repository.findBaseSalarialByCategoria(categoria);
    }
}
