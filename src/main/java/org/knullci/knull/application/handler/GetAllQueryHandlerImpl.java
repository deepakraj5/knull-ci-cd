package org.knullci.knull.application.handler;

import lombok.SneakyThrows;
import org.knullci.knull.application.dto.JobDto;
import org.knullci.knull.application.factory.JobFactory;
import org.knullci.knull.application.interfaces.GetAllQueryHandler;
import org.knullci.knull.application.query.GetAllJobQuery;
import org.knullci.knull.domain.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllQueryHandlerImpl implements GetAllQueryHandler {

    private final static Logger logger = LoggerFactory.getLogger(GetAllQueryHandlerImpl.class);

    private final JobRepository jobRepository;

    public GetAllQueryHandlerImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @SneakyThrows
    @Override
    public List<JobDto> handle(GetAllJobQuery query) {
        logger.info("Getting all jobs");
        var jobs = this.jobRepository.getAllJobs();
        return JobFactory.toDto(jobs);
    }
}
