package edu.hw2.ex3;

import edu.hw2.ex3.connection.FaultyConnection;
import edu.hw2.ex3.connection.StableConnection;
import edu.hw2.ex3.connection_manager.DefaultConnectionManager;
import edu.hw2.ex3.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PopularCommandExecutorTest {

    @BeforeAll
    public void init() {
        /*  otherwise:
            Java 21 (65) is not supported by the current version of Byte Buddy
            which officially supports Java 20 (64)
        */
        System.setProperty("net.bytebuddy.experimental", "true");
        openMocks(this);
    }

    @Mock
    private static DefaultConnectionManager mockedConnectionManager;
    @Mock
    private static FaultyConnection mockedFaultyConnection;
    @Mock
    private static StableConnection mockedStableConnection;

    @Test
    @DisplayName("Execution failed (faulty connection, all attempts failed)")
    void updatePackages_shouldThrowConnectionException_whenFaultyConnectionAndAllAttemptsFailed() {
        doThrow(new ConnectionException("Failed to connect")).when(mockedFaultyConnection).execute(anyString());
        doCallRealMethod().when(mockedFaultyConnection).close();
        when(mockedConnectionManager.getConnection()).thenReturn(mockedFaultyConnection);
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        assertThatExceptionOfType(ConnectionException.class).isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Executed successfully (stable connection)")
    void updatePackages_shouldBeExecutedSuccessfully_whenStableConnection() {
        when(mockedConnectionManager.getConnection()).thenReturn(new StableConnection());
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        assertThatNoException().isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Executed successfully (faulty connection but successful attempt)")
    void updatePackages_shouldBeExecutedSuccessfully_whenFaultyConnectionButSuccessfulAttempt() {
        doAnswer(invocationOnMock -> {
            LogManager.getLogger().info("Executed successfully");
            return null;
        }).when(mockedFaultyConnection).execute(anyString());
        doCallRealMethod().when(mockedFaultyConnection).close();
        when(mockedConnectionManager.getConnection()).thenReturn(mockedFaultyConnection);
        PopularCommandExecutor mockedPopularCommandExecutor = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        assertThatNoException().isThrownBy(mockedPopularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Failed to close connection")
    void updatePackages_shouldThrowConnectionException_whenFailedToCloseConnection() {
        doThrow(new RuntimeException()).when(mockedStableConnection).close();
        doCallRealMethod().when(mockedStableConnection).execute(anyString());
        doThrow(new RuntimeException()).when(mockedFaultyConnection).close();
        doAnswer(invocationOnMock -> {
            LogManager.getLogger().info("Executed successfully");
            return null;
        }).when(mockedFaultyConnection).execute(anyString());
        when(mockedConnectionManager.getConnection()).thenReturn(mockedStableConnection);
        PopularCommandExecutor mockedPopularCommandExecutorWithStable = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        when(mockedConnectionManager.getConnection()).thenReturn(mockedFaultyConnection);
        PopularCommandExecutor mockedPopularCommandExecutorWithFaulty = new PopularCommandExecutor(
            5, mockedConnectionManager
        );
        assertThatExceptionOfType(ConnectionException.class)
            .isThrownBy(mockedPopularCommandExecutorWithStable::updatePackages);
        assertThatExceptionOfType(ConnectionException.class)
            .isThrownBy(mockedPopularCommandExecutorWithFaulty::updatePackages);
    }

    @Test
    @DisplayName("Invalid argument: maxAttempts")
    void popularCommandExecutorConstructor_shouldThrowIllegalArgumentException_whenInvalidArgumentPassed() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(
                () -> new PopularCommandExecutor(0, new DefaultConnectionManager())
            );
    }
}
