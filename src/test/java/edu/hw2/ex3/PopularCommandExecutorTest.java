package edu.hw2.ex3;

import edu.hw2.ex3.connection.Connection;
import edu.hw2.ex3.connection.FaultyConnection;
import edu.hw2.ex3.connection.StableConnection;
import edu.hw2.ex3.connection_manager.DefaultConnectionManager;
import edu.hw2.ex3.exception.ConnectionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PopularCommandExecutorTest {

    @Mock
    private static DefaultConnectionManager mockedConnectionManager;
    @Mock
    private static Connection mockedConnection;
    @Mock
    private static Random mockedConnectionRandom;
    @Mock
    private static Random mockedExecutionRandom;

    @BeforeAll
    public void init() throws NoSuchFieldException, IllegalAccessException {
        /*  otherwise:
            Java 21 (65) is not supported by the current version of Byte Buddy
            which officially supports Java 20 (64)
        */
        System.setProperty("net.bytebuddy.experimental", "true");
        openMocks(this);
        setStatic(DefaultConnectionManager.class.getDeclaredField("random"), mockedConnectionRandom);
        setStatic(FaultyConnection.class.getDeclaredField("random"), mockedExecutionRandom);
    }

    static void setStatic(Field field, Object newValue) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(null, newValue);
    }

    @Test
    @DisplayName("Failed to close connection")
    void updatePackages_shouldThrowConnectionException_whenFailedToCloseConnection() throws Exception {
        doThrow(new RuntimeException()).when(mockedConnection).close();
        doAnswer(invocationOnMock -> {
            new StableConnection().execute(anyString());
            return null;
        }).when(mockedConnection).execute(anyString());
        when(mockedConnectionManager.getConnection()).thenReturn(mockedConnection);
        PopularCommandExecutor PopularCommandExecutorWithStable = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        assertThatExceptionOfType(ConnectionException.class)
            .isThrownBy(PopularCommandExecutorWithStable::updatePackages);

    }

    @Test
    @DisplayName("Invalid argument: maxAttempts")
    void popularCommandExecutorConstructor_shouldThrowIllegalArgumentException_whenInvalidArgumentPassed() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(
                () -> new PopularCommandExecutor(0, new DefaultConnectionManager())
            );
    }

    // test with mocking Random, not getConnection() and execute(String command) methods
    @Test
    @DisplayName("Executed successfully (stable connection)")
    void updatePackages_shouldBeExecutedSuccessfully_whenStableConnection() {
        when(mockedConnectionRandom.nextInt(anyInt(), anyInt())).thenReturn(7); // 7 != 0 so should be stable
        assertThat(new DefaultConnectionManager().getConnection()).isInstanceOf(StableConnection.class);
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, new DefaultConnectionManager()
        );
        assertThatNoException()
            .isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Executed successfully (faulty connection but successful attempt)")
    void updatePackages_shouldBeExecutedSuccessfully_whenFaultyConnectionButSuccessfulAttempt() {
        when(mockedConnectionRandom.nextInt(anyInt(), anyInt())).thenReturn(0); // faulty connection
        when(mockedExecutionRandom.nextInt(anyInt(), anyInt()))
            .thenReturn(0)
            .thenReturn(1)
            .thenReturn(5) // successful attempt
            .thenReturn(0);
        assertThat(new DefaultConnectionManager().getConnection())
            .isInstanceOf(FaultyConnection.class);
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, new DefaultConnectionManager()
        );
        assertThatNoException()
            .isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Execution failed (faulty connection, all attempts failed)")
    void updatePackages_shouldThrowConnectionException_whenFaultyConnectionAndAllAttemptsFailed() {
        when(mockedConnectionRandom.nextInt(anyInt(), anyInt())).thenReturn(0); // faulty connection
        when(mockedExecutionRandom.nextInt(anyInt(), anyInt()))
            .thenReturn(0)
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3)
            .thenReturn(0); // 5 attempts are failed
        assertThat(new DefaultConnectionManager().getConnection())
            .isInstanceOf(FaultyConnection.class);
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, new DefaultConnectionManager()
        );
        assertThatExceptionOfType(ConnectionException.class)
            .isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }
}
