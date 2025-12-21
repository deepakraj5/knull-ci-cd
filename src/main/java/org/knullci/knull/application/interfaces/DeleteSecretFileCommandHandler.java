package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.command.DeleteSecretFileCommand;

public interface DeleteSecretFileCommandHandler {
    void handle(DeleteSecretFileCommand command);
}
