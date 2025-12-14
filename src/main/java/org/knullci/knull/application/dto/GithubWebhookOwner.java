package org.knullci.knull.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubWebhookOwner {
    private String name;
    private String login;
    private Long id;
}
