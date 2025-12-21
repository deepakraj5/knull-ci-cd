package org.knullci.knull.persistence.repository;

import org.knullci.knull.domain.model.SecretFile;
import org.knullci.knull.domain.repository.SecretFileRepository;
import org.knullci.knull.persistence.mapper.SecretFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SecretFileRepositoryImpl implements SecretFileRepository {

    private static final Logger logger = LoggerFactory.getLogger(SecretFileRepositoryImpl.class);

    private final JsonKnullRepository<org.knullci.knull.persistence.entity.SecretFile> knullRepository;

    public SecretFileRepositoryImpl() {
        this.knullRepository = new JsonKnullRepository<>(
                "storage/secret-files",
                org.knullci.knull.persistence.entity.SecretFile.class);
    }

    @Override
    public SecretFile save(SecretFile secretFile) {
        logger.info("Saving secret file: {}", secretFile.getName());

        if (secretFile.getId() == null) {
            secretFile.setId(System.currentTimeMillis());
        }

        org.knullci.knull.persistence.entity.SecretFile entity = SecretFileMapper.toEntity(secretFile);
        knullRepository.save(entity.getId().toString(), entity);

        return secretFile;
    }

    @Override
    public Optional<SecretFile> findById(Long id) {
        logger.info("Finding secret file by id: {}", id);
        org.knullci.knull.persistence.entity.SecretFile entity = knullRepository.getByFileName(id.toString() + ".json");
        return Optional.ofNullable(SecretFileMapper.fromEntity(entity));
    }

    @Override
    public Optional<SecretFile> findByName(String name) {
        logger.info("Finding secret file by name: {}", name);
        return findAll().stream()
                .filter(sf -> sf.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<SecretFile> findAll() {
        logger.info("Fetching all secret files");
        return knullRepository.getAll().stream()
                .map(SecretFileMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting secret file by id: {}", id);
        knullRepository.deleteByFileName(id.toString());
    }

    @Override
    public boolean existsByName(String name) {
        return findByName(name).isPresent();
    }
}
