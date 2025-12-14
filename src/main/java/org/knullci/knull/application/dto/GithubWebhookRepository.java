package org.knullci.knull.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubWebhookRepository {
    private Long id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("ssh_url")
    private String sshUrl;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("html_url")
    private String htmlUrl;

    private GithubWebhookOwner owner;
}
