package Laboral.repository;

import Laboral.model.entities.SueldoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SueldoBaseRepository extends JpaRepository<SueldoBase, Integer> {
    Integer findBaseSalarialByCategoria(Integer categoria);
}
