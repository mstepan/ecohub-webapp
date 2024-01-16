package com.github.kostia.ecohub.flows.day_off;

import com.github.kostia.ecohub.flows.employee.EmployeeRepo;
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
public class DayOffController {


    private final DayOffRepo dayOffRepo;

    private final EmployeeRepo employeeRepo;


    public DayOffController(DayOffRepo dayOffRepo, EmployeeRepo employeeRepo) {
        this.dayOffRepo = dayOffRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/day_off")
    public String dayOff(@RequestParam("employeeId") String employeeId, Model model, HttpSession session) {

        setEmployeeId(employeeId, session);

        fillDataModel(model, session);

        return "day_off";
    }

    @PostMapping("/day_off")
    public String addDayOff(@ModelAttribute DayOff dayOff, Model model, HttpSession session) {

        dayOff.setEmployeeId(getEmployeeId(session));

        dayOffRepo.save(dayOff);

        fillDataModel(model, session);

        return "day_off";
    }

    @GetMapping("/day_off/delete")
    public String deleteDayOff(@RequestParam("dayOffId") String dayOffId, Model model, HttpSession session) {

        DayOff dayOff = dayOffRepo.findById(Integer.parseInt(dayOffId)).get();

        try {
            dayOffRepo.deleteById(dayOff.getId());
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete Day Off");
            model.addAttribute("errorMessage", "Can't delete Day Off");
        }

        fillDataModel(model, session);

        return "day_off";
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

        model.addAttribute("dayOff", DayOff.builder().build());

        model.addAttribute("allDaysOffs", dayOffRepo.findDayOffsByEmployeeId(curEmployeeId));
    }

}
