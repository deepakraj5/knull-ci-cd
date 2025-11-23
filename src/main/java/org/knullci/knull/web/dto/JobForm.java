package org.knullci.knull.web.dto;

import lombok.Data;
import org.knullci.knull.domain.enums.JobType;

@Data
public class JobForm {
    private String name;
    private String description;
    private JobType jobType;
}
