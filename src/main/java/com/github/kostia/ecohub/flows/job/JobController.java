package com.github.kostia.ecohub.flows.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class JobController {

    private final JobRepo jobRepo;

    public JobController(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @GetMapping("/job")
    public String job(Model model) {
        fillDataModel(model);

        return "job";
    }

    @PostMapping("/job")
    public String addJob(@ModelAttribute Job job, Model model) {

        if(job.getMinSalary().compareTo(job.getMaxSalary()) > 0 ){
            model.addAttribute("errorMessage",
                "Min Job salary can't be less than Max Job Salary");
        }
        else {
            jobRepo.save(job);
        }

        fillDataModel(model);

        return "job";
    }

    @GetMapping("/job/delete")
    public String deleteJob(@RequestParam("jobId") String jobId, Model model) {
        try {
            jobRepo.deleteById(Integer.parseInt(jobId));
        } catch (DbActionExecutionException dbEx) {
            log.error("Can't delete Job");
            model.addAttribute("errorMessage",
                "Can't delete Job");
        }

        fillDataModel(model);

        return "job";
    }

    private void fillDataModel(Model model) {
        model.addAttribute("job", Job.builder().build());
        model.addAttribute("allJobs", jobRepo.findAll());
    }

}
