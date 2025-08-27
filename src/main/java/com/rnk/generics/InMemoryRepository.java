package com.rnk.generics;

import com.rnk.generics.exceptions.EntityNotFoundException;
import com.rnk.generics.filter.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class InMemoryRepository<ID, T> implements Repository<ID, T>{
    private final Logger logger;

    private Map<ID, T> storage = new HashMap<>();

    public InMemoryRepository(Logger logger){
        if(logger == null)
            throw new IllegalArgumentException("Logger cannot be null");
        this.logger = logger;
        logger.info("InMemoryRepository created");
    }

    @Override
    public void save(ID id, T entity) throws IllegalArgumentException{
        if(id == null) throw new IllegalArgumentException("ID cannot be null");
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null");
        storage.put(id, entity);
        logger.info("Saved entity with ID: " + id);
        logger.info(()->String.format("Saved entity with ID=%s", id));
    }

    @Override
    public T findById(ID id) {
        if(id == null) throw new IllegalArgumentException("ID cannot be null");
        T entity = storage.get(id);
        if(entity == null){
            logger.warning(()->String.format("Entity with ID=%s not found", id));
            throw new EntityNotFoundException("Entity with ID="+id+" not found");
        }
        logger.info(()->String.format("Found entity with ID=%s", id));
        return entity;
    }

    @Override
    public List<T> findAll() {
        logger.info("Fetching all entities");
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(ID id) {
        if(id == null) throw new IllegalArgumentException("ID cannot be null");
        if(!storage.containsKey(id)){
            logger.warning("Cannot delete - no entity with ID: " + id);
            logger.warning(()->String.format("Cannot delete - no entity with ID=%s", id));
            throw new EntityNotFoundException("Cannot delete: Entity with ID " + id + " not found");
        }
        storage.remove(id);
        logger.info("Deleted entity");
        logger.info(() -> String.format("Deleted entity with ID=%s", id));
    }

    /**
     * Returns the list of entities matching the given filter.
     * @param filter Predicate to apply for filtering
     * @return a list of filtered entities.
     * @throws IllegalArgumentException if filter is null.
     * @throws EntityNotFoundException if no entities match the filter.
     */
    @Override
    @Deprecated
    public List<T> findBy(Predicate<T> filter) throws EntityNotFoundException{
        if(filter == null) throw new IllegalArgumentException("Filter cannot be null");
        logger.info(() -> String.format("Applying filter predicate to %d entities", storage.size()));
        List<T> filtered = storage.values().stream().filter(filter).toList();
        logger.info(() -> String.format("Filter returned %d entities", filtered.size()));
        if(filtered.isEmpty()){
            logger.warning(()->String.format("No Entity found matching filter: %s", filter));
            throw new EntityNotFoundException("Entity with filter="+filter+" not found");
        }
        logger.info("Number of element(s) found: " + filtered.size());
        return new ArrayList<>(filtered);
    }

    /**
     * Return the list of entities matching the given specification
     * @param specification filter to apply
     * @return a list of filtered entities
     * @throws IllegalArgumentException if Specification is null
     * @throws EntityNotFoundException if no entities match the specification
     */
    @Override
    public List<T> findBySpecification(Specification specification){
        if(specification == null) throw new IllegalArgumentException("Specification cannot be null");
        logger.info(() -> String.format("Applying specification to filter %d entities", storage.size()));
        List<T> filtered = storage.values().stream().filter(specification::isSatisfiedBy).toList();
        logger.info(() -> String.format("Specification returned %d entities", filtered.size()));
        logger.info("Number of element(s) found: " + filtered.size());
        return new ArrayList<>(filtered);
    }
}
