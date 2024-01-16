package com.github.kostia.ecohub.flows.overtime;

import com.github.kostia.ecohub.flows.job_history.JobHistoryRepo;
import jakarta.servlet.http.HttpSession;
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
public class OvertimeController {

    private final OvertimeRepo overtimeRepo;

    private final JobHistoryRepo jobHistoryRepo;


    public OvertimeController(OvertimeRepo overtimeRepo, JobHistoryRepo jobHistoryRepo) {
        this.overtimeRepo = overtimeRepo;
        this.jobHistoryRepo = jobHistoryRepo;
    }

    @GetMapping("/overtime")
    public String overtime(@RequestParam("jobHistoryId") String jobHistoryId, Model model, HttpSession session) {

        setJobHistoryId(jobHistoryId, session);

        fillDataModel(model, session);

        return "overtime";
    }

    @PostMapping("/overtime")
    public String addOvertime(@ModelAttribute Overtime overtime, Model model, HttpSession session) {

        overtime.setJobHistoryId(getJobHistoryId(session));

        overtimeRepo.save(overtime);

        fillDataModel(model, session);

        return "overtime";
    }

    @GetMapping("/overtime/delete")
    public String deleteOvertime(@RequestParam("overtimeId") String overtimeId, Model model, HttpSession session) {

        Overtime overtime = overtimeRepo.findById(Integer.parseInt(overtimeId)).get();

        try {
            overtimeRepo.delete(overtime);
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete Overtime");
            model.addAttribute("errorMessage", "Can't delete Overtimet");
        }

        fillDataModel(model, session);

        return "overtime";
    }

    private void setJobHistoryId(String jobHistoryId, HttpSession session){
        session.setAttribute("jobHistoryId", jobHistoryId);
    }

    private Integer getJobHistoryId(HttpSession session){
        return Integer.parseInt((String)session.getAttribute("jobHistoryId"));
    }

    private void fillDataModel(Model model, HttpSession session){
        Integer jobHistoryId = getJobHistoryId(session);

        model.addAttribute("jobHistory", jobHistoryRepo.findById(jobHistoryId).get());
        model.addAttribute("overtime", Overtime.builder().build());
        model.addAttribute("allOvertimes", overtimeRepo.findAllOvertimesForJobHistoryId(jobHistoryId));
    }

}
