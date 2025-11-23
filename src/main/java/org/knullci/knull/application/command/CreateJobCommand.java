package org.knullci.knull.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.knullci.knull.domain.enums.JobType;

@Getter
@AllArgsConstructor
public class CreateJobCommand {
    private String name;
    private String description;
    private JobType jobType;
}
