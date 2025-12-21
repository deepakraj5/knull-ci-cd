package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.command.CreateSecretFileCommand;
import org.knullci.knull.application.dto.SecretFileDto;

public interface CreateSecretFileCommandHandler {
    SecretFileDto handle(CreateSecretFileCommand command);
}
