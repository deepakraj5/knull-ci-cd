package org.knullci.knull.application.factory;

import org.knullci.knull.application.command.CreateJobCommand;
import org.knullci.knull.application.dto.JobDto;
import org.knullci.knull.domain.model.Job;

import java.util.List;

public class JobFactory {
    public static Job fromCommand(CreateJobCommand command) {
        return new Job(
                null,
                command.getName(),
                command.getDescription(),
                command.getJobType(),
                null,
                null,
                null,
                null,
                null
        );
    }

    public static JobDto toDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getName(),
                job.getDescription(),
                job.getJobType()
        );
    }

    public static List<JobDto> toDto(List<Job> jobs) {
        return jobs.stream().map(JobFactory::toDto).toList();
    }
}
