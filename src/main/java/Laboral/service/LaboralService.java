package Laboral.service;

import Laboral.rules.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase abstracta genérica que sirve como superclase para todos los services
 * <p>
 * Esta clase permite definir métodos comunes a todos los servicios, como guardar,
 * eliminar o buscar entidades, sin necesidad de repetir código en cada service concreto.
 * </p>
 *
 * @param <R>  Repository: repositorio correspondiente que extienda de {@link JpaRepository}.
 * @param <E>  Entity: la que maneja el servicio.
 * @param <ID> Id: identificador de la entidad (clave primaria).
 */
@AllArgsConstructor
public abstract class LaboralService<R extends JpaRepository<E, ID>, E, ID> {
    protected final R repository;

    //GET
    public List<E> getAll(){return repository.findAll();}
    public E findById(ID id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Long count(){return repository.count();}

    //POST
    @Transactional
    public List<E> saveAll(List<E> entities){return repository.saveAll(entities);}
    @Transactional
    public E save(E entity){return repository.save(entity);}

    //

    //DELETE
    public void deleteAll(List<E> entities){repository.deleteAll(entities);}
    public void delete(E entity){repository.delete(entity);}

}
