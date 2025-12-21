package org.knullci.knull.domain.repository;

import org.knullci.knull.domain.model.SecretFile;

import java.util.List;
import java.util.Optional;

public interface SecretFileRepository {

    /**
     * Save a secret file
     */
    SecretFile save(SecretFile secretFile);

    /**
     * Find a secret file by ID
     */
    Optional<SecretFile> findById(Long id);

    /**
     * Find a secret file by name
     */
    Optional<SecretFile> findByName(String name);

    /**
     * Get all secret files
     */
    List<SecretFile> findAll();

    /**
     * Delete a secret file by ID
     */
    void deleteById(Long id);

    /**
     * Check if a secret file exists by name
     */
    boolean existsByName(String name);
}
