package com.github.kostia.ecohub.flows.charity_event;

import com.github.kostia.ecohub.flows.department.DepartmentRepo;
import com.github.kostia.ecohub.flows.head_leader.HeadLeaderRepo;
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
public class CharityEventController {


    private final CharityEventRepo charityEventRepo;

    private final HeadLeaderRepo headLeaderRepo;

    private final DepartmentRepo departmentRepo;

    public CharityEventController(CharityEventRepo charityEventRepo, HeadLeaderRepo headLeaderRepo,
                                  DepartmentRepo departmentRepo) {
        this.charityEventRepo = charityEventRepo;
        this.headLeaderRepo = headLeaderRepo;
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("/charity_event")
    public String charityEvent(@RequestParam("headLeaderId") String headLeaderId, Model model, HttpSession session) {

        setHeadLeaderId(headLeaderId, session);

        fillDataModel(model, session);

        return "charity_event";
    }

    @PostMapping("/charity_event")
    public String addCharityEvent(@ModelAttribute CharityEvent charityEvent, Model model, HttpSession session) {

        charityEvent.setHeadLeaderId(getHeadLeaderId(session));

        charityEventRepo.save(charityEvent);

        fillDataModel(model, session);

        return "charity_event";
    }

    @GetMapping("/charity_event/delete")
    public String deleteCharityEvent(@RequestParam("charityEventId") String charityEventId, Model model, HttpSession session) {

        CharityEvent charityEvent = charityEventRepo.findById(Integer.parseInt(charityEventId)).get();

        try {
            charityEventRepo.deleteById(charityEvent.getId());
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete disaster for department");
            model.addAttribute("errorMessage", "Can't delete disaster for department");
        }

        fillDataModel(model, session);

        return "charity_event";
    }

    private void setHeadLeaderId(String headLeaderId, HttpSession session){
        session.setAttribute("headLeaderId", headLeaderId);
    }

    private Integer getHeadLeaderId(HttpSession session){
        return Integer.parseInt((String)session.getAttribute("headLeaderId"));
    }

    private void fillDataModel(Model model, HttpSession session){

        Integer curHeadLeader = getHeadLeaderId(session);

        model.addAttribute("headLeader", headLeaderRepo.findById(curHeadLeader).get());
        model.addAttribute("allDepartments", departmentRepo.findAll());
        model.addAttribute("charityEvent", CharityEvent.builder().build());
        model.addAttribute("allCharityEvents", charityEventRepo.findAllCharityEventsForLeaderId(curHeadLeader));
    }

}
