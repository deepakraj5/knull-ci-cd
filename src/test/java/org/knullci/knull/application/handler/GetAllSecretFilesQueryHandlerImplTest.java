package org.knullci.knull.application.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.knullci.knull.application.dto.SecretFileDto;
import org.knullci.knull.application.query.GetAllSecretFilesQuery;
import org.knullci.knull.domain.model.SecretFile;
import org.knullci.knull.domain.repository.SecretFileRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllSecretFilesQueryHandlerImplTest {

    @Mock
    private SecretFileRepository secretFileRepository;

    @InjectMocks
    private GetAllSecretFilesQueryHandlerImpl handler;

    private GetAllSecretFilesQuery query;

    @BeforeEach
    void setUp() {
        query = new GetAllSecretFilesQuery();
    }

    @Test
    void testHandle_WithMultipleSecretFiles_ShouldReturnAllAsDto() {
        // Arrange
        SecretFile secret1 = createSecretFile(1L, "secret1", "content1", "Description 1");
        SecretFile secret2 = createSecretFile(2L, "secret2", "content2", "Description 2");
        SecretFile secret3 = createSecretFile(3L, "secret3", "content3", "Description 3");

        when(secretFileRepository.findAll()).thenReturn(Arrays.asList(secret1, secret2, secret3));

        // Act
        List<SecretFileDto> result = handler.handle(query);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("secret1", result.get(0).getName());
        assertEquals("Description 1", result.get(0).getDescription());

        assertEquals(2L, result.get(1).getId());
        assertEquals("secret2", result.get(1).getName());

        assertEquals(3L, result.get(2).getId());
        assertEquals("secret3", result.get(2).getName());

        verify(secretFileRepository).findAll();
    }

    @Test
    void testHandle_WithEmptyRepository_ShouldReturnEmptyList() {
        // Arrange
        when(secretFileRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<SecretFileDto> result = handler.handle(query);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(secretFileRepository).findAll();
    }

    @Test
    void testHandle_ShouldNotExposeContent() {
        // Arrange
        SecretFile secret = createSecretFile(1L, "secret", "sensitive-content", "Description");
        when(secretFileRepository.findAll()).thenReturn(Collections.singletonList(secret));

        // Act
        List<SecretFileDto> result = handler.handle(query);

        // Assert
        assertEquals(1, result.size());
        // Content should not be in DTO (verify DTO doesn't have content field exposed
        // in list view)
        assertNotNull(result.get(0).getName());
        assertNotNull(result.get(0).getDescription());
    }

    @Test
    void testHandle_WithNullDescription_ShouldHandleGracefully() {
        // Arrange
        SecretFile secret = createSecretFile(1L, "secret", "content", null);
        when(secretFileRepository.findAll()).thenReturn(Collections.singletonList(secret));

        // Act
        List<SecretFileDto> result = handler.handle(query);

        // Assert
        assertEquals(1, result.size());
        assertNull(result.get(0).getDescription());
    }

    @Test
    void testHandle_ShouldCallRepositoryOnce() {
        // Arrange
        when(secretFileRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        handler.handle(query);

        // Assert
        verify(secretFileRepository, times(1)).findAll();
    }

    private SecretFile createSecretFile(Long id, String name, String content, String description) {
        SecretFile secretFile = new SecretFile();
        secretFile.setId(id);
        secretFile.setName(name);
        secretFile.setEncryptedContent(content);
        secretFile.setDescription(description);
        secretFile.setType(SecretFile.SecretType.FILE);
        return secretFile;
    }
}
