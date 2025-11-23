package org.knullci.knull.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.knullci.knull.domain.enums.JobType;

@AllArgsConstructor
@Getter
public class JobDto {
    private Long id;
    private String name;
    private String description;
    private JobType jobType;
}
