package com.rnk.generics;

import com.rnk.generics.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class InMemoryRepository<ID, T> implements Repository<ID, T>{
    private static final Logger logger = Logger.getLogger(InMemoryRepository.class.getName());

    private Map<ID, T> storage = new HashMap();

    public InMemoryRepository(){
        logger.info("InMemoryRepository created");
    }

    @Override
    public void save(ID id, T entity) {
        if(id == null) throw new IllegalArgumentException("ID cannot be null");
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null");
        storage.put(id, entity);
        logger.info(()->String.format("Saved entity with ID=%s", id));
    }

    @Override
    public T findById(ID id) {
        if(id == null) throw new IllegalArgumentException("ID cannot be null");
        T entity = storage.get(id);
        if(entity == null){
            logger.warning(()->String.format("Entity with ID=%s not found", id));
            throw new EntityNotFoundException("Entity with ID="+id+" not fond");
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
            logger.warning(()->String.format("Cannot delete - no entity with ID=%s", id));
            throw new EntityNotFoundException("Cannot delete: Entity with ID " + id + " not found");
        }
        storage.remove(id);
        logger.info(() -> String.format("Deleted entity with ID=%s", id));
    }
}
