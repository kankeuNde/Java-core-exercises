package com.rnk.generics;

import com.rnk.generics.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InMemoryRepositoryLoggerTest {
    private Logger mockLogger;
    private InMemoryRepository<Integer, String> repository;

    @BeforeEach
    public void setUp() {
        mockLogger = mock(Logger.class);
        repository = new InMemoryRepository<>(mockLogger);
    }

    @Test
    public void testSaveLogsInfo() {
        repository.save(1, "Alpha");
        //Verify that info() was called with a message containing "Saved entity"
        verify(mockLogger, atLeastOnce()).info(contains("Saved entity"));
    }

    @Test
    public void testDeleteByIdLogsInfoAndWarning(){
        repository.save(1, "Test");
        repository.deleteById(1);
        verify(mockLogger, atLeastOnce()).info(contains("Deleted entity"));
        // Try deleting non-existing to trigger warning
        assertThrows(EntityNotFoundException.class, () -> repository.deleteById(99));
        verify(mockLogger, atLeastOnce()).warning(contains("Cannot delete"));
    }

    @Test
    public void testNullLoggerInjectionThrows(){
        assertThrows(IllegalArgumentException.class, () -> new InMemoryRepository<>(null));
    }

}
