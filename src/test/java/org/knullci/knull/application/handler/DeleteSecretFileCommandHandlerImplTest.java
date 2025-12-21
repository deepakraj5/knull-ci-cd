package org.knullci.knull.application.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.knullci.knull.application.command.DeleteSecretFileCommand;
import org.knullci.knull.domain.repository.SecretFileRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSecretFileCommandHandlerImplTest {

    @Mock
    private SecretFileRepository secretFileRepository;

    @InjectMocks
    private DeleteSecretFileCommandHandlerImpl handler;

    @Test
    void testHandle_WithValidId_ShouldDeleteSuccessfully() {
        // Arrange
        Long secretId = 123L;
        DeleteSecretFileCommand command = new DeleteSecretFileCommand(secretId);
        doNothing().when(secretFileRepository).deleteById(secretId);

        // Act
        handler.handle(command);

        // Assert
        verify(secretFileRepository).deleteById(secretId);
    }

    @Test
    void testHandle_ShouldCallDeleteOnce() {
        // Arrange
        DeleteSecretFileCommand command = new DeleteSecretFileCommand(456L);
        doNothing().when(secretFileRepository).deleteById(anyLong());

        // Act
        handler.handle(command);

        // Assert
        verify(secretFileRepository, times(1)).deleteById(456L);
    }

    @Test
    void testHandle_WithDifferentIds_ShouldDeleteCorrectOne() {
        // Arrange
        DeleteSecretFileCommand command = new DeleteSecretFileCommand(789L);
        doNothing().when(secretFileRepository).deleteById(anyLong());

        // Act
        handler.handle(command);

        // Assert
        verify(secretFileRepository).deleteById(789L);
        verify(secretFileRepository, never()).deleteById(123L);
        verify(secretFileRepository, never()).deleteById(456L);
    }

    @Test
    void testHandle_WhenRepositoryThrowsException_ShouldPropagateException() {
        // Arrange
        DeleteSecretFileCommand command = new DeleteSecretFileCommand(999L);
        doThrow(new RuntimeException("Database error")).when(secretFileRepository).deleteById(999L);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> handler.handle(command));
        verify(secretFileRepository).deleteById(999L);
    }
}
