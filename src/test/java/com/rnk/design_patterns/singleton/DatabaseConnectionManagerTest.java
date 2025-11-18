package com.rnk.design_patterns.singleton;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.rnk.design_patterns.singleton.connection_manager.DatabaseConnectionManager;
import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DatabaseConnectionManagerTest {

    @Mock
    private Connection mockConnection;

    private AutoCloseable mocks;

    @BeforeEach
    public void setup() throws SQLException {
        mocks = MockitoAnnotations.openMocks(this);
        // Example: default mock behavior
        when(mockConnection.isClosed()).thenReturn(false);
        // You can mock additional behaviors if needed
    }

    @AfterEach
    public void teardown() throws Exception {
        mocks.close();
    }

    @Test
    public void testSingletonInstanceSame() throws SQLException {
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance(5);
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance(10);

        assertSame(instance1, instance2, "getInstance should return the same singleton instance");
    }

    @Test
    public void testGetConnectionReturnsMockedConnection() throws SQLException, InterruptedException {
        DatabaseConnectionManager manager = spy(DatabaseConnectionManager.getInstance(1));

        // Stub the method that creates a new connection to return the mock
        doReturn(mockConnection).when(manager).createNewConnection();

        Connection connection = manager.getConnection();

        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }

    @Test
    public void testReleaseConnectionReturnsConnectionToPool() throws SQLException, InterruptedException {
        DatabaseConnectionManager manager = spy(DatabaseConnectionManager.getInstance(1));
        doReturn(mockConnection).when(manager).createNewConnection();

        Connection connection = manager.getConnection();
        manager.releaseConnection(connection);

        // Calling getConnection again should return the same connection instance
        Connection reusedConnection = manager.getConnection();
        assertSame(connection, reusedConnection);
    }

    @Test
    public void testCloseAllConnectionsClosesAll() throws SQLException {
        DatabaseConnectionManager manager = spy(DatabaseConnectionManager.getInstance(1));
        doReturn(mockConnection).when(manager).createNewConnection();

        manager.closeAllConnections();

        verify(mockConnection, atLeastOnce()).close();
    }

    @Test
    public void testGetConnectionBlocksWhenPoolEmpty() throws SQLException, InterruptedException {
        DatabaseConnectionManager manager = spy(DatabaseConnectionManager.getInstance(1));
        doReturn(mockConnection).when(manager).createNewConnection();

        // Borrow the single connection, so pool is empty now.
        Connection connection = manager.getConnection();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);

        executor.submit(() -> {
            try {
                // This call should block until "connection" is released
                Connection c = manager.getConnection();
                assertNotNull(c);
            } catch (Exception e) {
                fail("getConnection should block and then return connection");
            } finally {
                latch.countDown();
            }
        });

        // Give some time to ensure getConnection is blocking
        Thread.sleep(500);

        // Release connection to unblock the waiting thread
        manager.releaseConnection(connection);

        // Wait for task to complete
        latch.await();

        executor.shutdownNow();
    }

    @Test
    public void testGetConnectionThrowsOnInterrupted() throws SQLException, InterruptedException {
        DatabaseConnectionManager manager = DatabaseConnectionManager.getInstance(1);

        // Simulate interruption during getConnection blocking:
        Thread.currentThread().interrupt();

        assertThrows(SQLException.class, () -> {
            manager.getConnection();
        });

        // Clear interrupted flag to not affect other tests
        Thread.interrupted();
    }

    @Test
    public void testSetMaxConnectionsAdjustsPoolSize() throws SQLException {
        DatabaseConnectionManager manager = DatabaseConnectionManager.getInstance(2);
        int oldMax = manager.getMaxConnections();

        manager.setMaxConnections(oldMax + 1);

        assertEquals(oldMax + 1, manager.getMaxConnections());
    }

    @Test
    public void testInvalidMaxConnectionsThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            DatabaseConnectionManager.getInstance(-1);
        });
    }
}
