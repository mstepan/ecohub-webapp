package com.github.kostia.ecohub.flows.job_history;

import com.github.kostia.ecohub.flows.charity_event.CharityEvent;
import com.github.kostia.ecohub.flows.charity_event.CharityEventRepo;
import com.github.kostia.ecohub.flows.department.DepartmentRepo;
import com.github.kostia.ecohub.flows.employee.EmployeeRepo;
import com.github.kostia.ecohub.flows.head_leader.HeadLeaderRepo;
import com.github.kostia.ecohub.flows.job.JobRepo;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class JobHistoryController {


    private final JobHistoryRepo jobHistoryRepo;

    private final EmployeeRepo employeeRepo;

    private final DepartmentRepo departmentRepo;

    private final JobRepo jobRepo;

    public JobHistoryController(JobHistoryRepo jobHistoryRepo, EmployeeRepo employeeRepo,
    DepartmentRepo departmentRepo, JobRepo jobRepo) {
        this.jobHistoryRepo = jobHistoryRepo;
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.jobRepo = jobRepo;
    }

    @GetMapping("/job_history")
    public String charityEvent(@RequestParam("employeeId") String employeeId, Model model, HttpSession session) {

        setEmployeeId(employeeId, session);

        fillDataModel(model, session);

        return "job_history";
    }

    @PostMapping("/job_history")
    public String addJobHistory(@ModelAttribute JobHistory jobHistory, Model model, HttpSession session) {

        jobHistory.setEmployeeId(getEmployeeId(session));

        jobHistoryRepo.save(jobHistory);

        fillDataModel(model, session);

        return "job_history";
    }

    @GetMapping("/job_history/delete")
    public String deleteJobHistory(@RequestParam("jobHistoryId") String jobHistoryId, Model model, HttpSession session) {

        JobHistory jobHistory = jobHistoryRepo.findById(Integer.parseInt(jobHistoryId)).get();

        try {
            jobHistoryRepo.deleteById(jobHistory.getId());
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete Job History");
            model.addAttribute("errorMessage", "Can't delete Job History");
        }

        fillDataModel(model, session);

        return "job_history";
    }

    private void setEmployeeId(String employeeId, HttpSession session){
        session.setAttribute("employeeId", employeeId);
    }

    private Integer getEmployeeId(HttpSession session){
        return Integer.parseInt((String)session.getAttribute("employeeId"));
    }

    private void fillDataModel(Model model, HttpSession session){
        Integer curEmployeeId = getEmployeeId(session);

        model.addAttribute("employee", employeeRepo.findById(curEmployeeId).get());

        model.addAttribute("allJobs", jobRepo.findAll());

        model.addAttribute("jobHistory", JobHistory.builder().build());

        List<JobHistory> allJobHistory = jobHistoryRepo.findJobHistoryForEmployeeId(curEmployeeId);

        allJobHistory.forEach(
            singleJobHistory -> singleJobHistory.setJob(jobRepo.findById(singleJobHistory.getJobId()).orElseThrow())
        );

        model.addAttribute("allJobHistory", allJobHistory);
    }

}
