package org.knullci.knull.web.controller;

import org.knullci.knull.application.command.CreateJobCommand;
import org.knullci.knull.application.interfaces.CreateJobCommandHandler;
import org.knullci.knull.application.interfaces.GetAllQueryHandler;
import org.knullci.knull.application.query.GetAllJobQuery;
import org.knullci.knull.domain.enums.JobType;
import org.knullci.knull.web.dto.JobForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final CreateJobCommandHandler createJobCommandHandler;
    private final GetAllQueryHandler getAllQueryHandler;

    public JobController(CreateJobCommandHandler createJobCommandHandler, GetAllQueryHandler getAllQueryHandler) {
        this.createJobCommandHandler = createJobCommandHandler;
        this.getAllQueryHandler = getAllQueryHandler;
    }

    @GetMapping("/create")
    public String showCreateJob(Model model) {
        model.addAttribute("jobForm", new JobForm());
        model.addAttribute("jobTypes", JobType.values());

        return "jobs/create";
    }

    @PostMapping
    public String createJob(@ModelAttribute("jobForm") JobForm jobForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("jobTypes", JobType.values());
            return "jobs/create";
        }

        createJobCommandHandler.handle(new CreateJobCommand(
                jobForm.getName(),
                jobForm.getDescription(),
                jobForm.getJobType()
        ));

        return "redirect:/jobs";
    }

    @GetMapping
    public String getAllJobs(Model model) {
        var jobs = getAllQueryHandler.handle(new GetAllJobQuery());
        model.addAttribute("jobs", jobs);

        return "jobs/index";
    }

}
