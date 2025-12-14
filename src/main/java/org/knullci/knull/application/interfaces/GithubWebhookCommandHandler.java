package org.knullci.knull.application.interfaces;

import org.knullci.knull.application.command.GithubWebhookCommand;
import org.knullci.knull.application.dto.GithubWebhookResponseDto;

public interface GithubWebhookCommandHandler extends CommandHandler<GithubWebhookCommand, GithubWebhookResponseDto>{
}
