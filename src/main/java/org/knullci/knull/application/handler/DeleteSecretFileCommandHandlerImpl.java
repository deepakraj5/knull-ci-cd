package org.knullci.knull.application.handler;

import org.knullci.knull.application.command.DeleteSecretFileCommand;
import org.knullci.knull.application.interfaces.DeleteSecretFileCommandHandler;
import org.knullci.knull.domain.repository.SecretFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteSecretFileCommandHandlerImpl implements DeleteSecretFileCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeleteSecretFileCommandHandlerImpl.class);

    private final SecretFileRepository secretFileRepository;

    public DeleteSecretFileCommandHandlerImpl(SecretFileRepository secretFileRepository) {
        this.secretFileRepository = secretFileRepository;
    }

    @Override
    public void handle(DeleteSecretFileCommand command) {
        logger.info("Deleting secret file with id: {}", command.getId());
        secretFileRepository.deleteById(command.getId());
    }
}
