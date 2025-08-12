package com.rnk.generics;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import com.rnk.generics.exceptions.*;

public class InMemoryRepositoryTest {
    private Repository<Integer, String> repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryRepository<>();
    }

    @Test
    public void testSaveAndFindById() {
        repository.save(1, "Customer A");
        assertEquals("Customer A", repository.findById(1));
    }

    @Test
    public void testFindAll() {
        repository.save(1, "Entity1");
        repository.save(2, "Entity2");
        List<String> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains("Entity1"));
        assertTrue(all.contains("Entity2"));
    }

    @Test
    public void testDeleteByIdExisting() {
        repository.save(1, "Entity1");
        repository.deleteById(1);
        assertThrows(EntityNotFoundException.class, () -> repository.findById(1));
    }

    @Test
    public void testDeleteByIdNonExisting() {
        assertThrows(EntityNotFoundException.class, () -> repository.deleteById(99));
    }

    @Test
    public void testFindByIdNonExisting() {
        assertThrows(EntityNotFoundException.class, () -> repository.findById(42));
    }

    @Test
    public void testSaveNullIdShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(null, "Entity"));
    }

    @Test
    public void testSaveNullEntityShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> repository.save(1, null));
    }



}
