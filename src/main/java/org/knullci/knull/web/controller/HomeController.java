package org.knullci.knull.web.controller;

import org.knullci.knull.domain.enums.BuildStatus;
import org.knullci.knull.domain.repository.BuildRepository;
import org.knullci.knull.domain.repository.CredentialRepository;
import org.knullci.knull.domain.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final JobRepository jobRepository;
    private final BuildRepository buildRepository;
    private final CredentialRepository credentialRepository;

    public HomeController(JobRepository jobRepository,
            BuildRepository buildRepository,
            CredentialRepository credentialRepository) {
        this.jobRepository = jobRepository;
        this.buildRepository = buildRepository;
        this.credentialRepository = credentialRepository;
    }

    @GetMapping
    public String home(Model model) {
        // Fetch statistics
        var totalJobs = jobRepository.getAllJobs().size();
        var totalBuilds = buildRepository.countAll();
        var totalCredentials = credentialRepository.findAll().size();

        // Count active builds (IN_PROGRESS or PENDING)
        var activeBuilds = buildRepository.findAll().stream()
                .filter(build -> build.getStatus() == BuildStatus.IN_PROGRESS ||
                        build.getStatus() == BuildStatus.PENDING)
                .count();

        // Get recent builds (last 10)
        var recentBuilds = buildRepository.findAllPaginated(0, 10);

        model.addAttribute("totalJobs", totalJobs);
        model.addAttribute("totalBuilds", totalBuilds);
        model.addAttribute("totalCredentials", totalCredentials);
        model.addAttribute("activeBuilds", activeBuilds);
        model.addAttribute("recentBuilds", recentBuilds);

        return "index";
    }

}
