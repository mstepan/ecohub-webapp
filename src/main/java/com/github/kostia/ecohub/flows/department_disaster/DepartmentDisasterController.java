package com.github.kostia.ecohub.flows.department_disaster;

import com.github.kostia.ecohub.flows.department.DepartmentRepo;
import com.github.kostia.ecohub.flows.disaster.Disaster;
import com.github.kostia.ecohub.flows.disaster.DisasterRepo;
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
public class DepartmentDisasterController {


    private final DepartmentDisasterRepo depDisasterRepo;

    private final DisasterRepo disasterRepo;

    private final DepartmentRepo departmentRepo;

    public DepartmentDisasterController(DepartmentDisasterRepo depDisasterRepo, DisasterRepo disasterRepo,
                                        DepartmentRepo departmentRepo) {
        this.depDisasterRepo = depDisasterRepo;
        this.disasterRepo = disasterRepo;
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("/department_disaster")
    public String departmentDisaster(@RequestParam("departmentId") String departmentId, Model model, HttpSession session) {

        setDepartmentId(departmentId, session);

        fillDataModel(model, session);

        return "department_disaster";
    }

    @PostMapping("/department_disaster")
    public String addDepartmentDisaster(@ModelAttribute DepartmentDisaster departmentDisaster, Model model, HttpSession session) {

        departmentDisaster.setDepartmentId(getDepartmentId(session));

        depDisasterRepo.save(departmentDisaster);

        fillDataModel(model, session);

        return "department_disaster";
    }

    @GetMapping("/department_disaster/delete")
    public String deleteDepartmentDisaster(@RequestParam("departmentDisasterId") String depDisasterId, Model model, HttpSession session) {

        DepartmentDisaster depDis = depDisasterRepo.findById(Integer.parseInt(depDisasterId)).get();

        try {
            depDisasterRepo.deleteById(Integer.parseInt(depDisasterId));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete disaster for department");
            model.addAttribute("errorMessage", "Can't delete disaster for department");
        }

        fillDataModel(model, session);

        return "department_disaster";
    }

    private void setDepartmentId(String departmentId, HttpSession session){
        session.setAttribute("departmentId", departmentId);
    }

    private Integer getDepartmentId(HttpSession session){
        return Integer.parseInt((String)session.getAttribute("departmentId"));
    }

    private void fillDataModel(Model model, HttpSession session){

        Integer curDepId = getDepartmentId(session);

        model.addAttribute("department", departmentRepo.findById(curDepId).get());
        model.addAttribute("allDisasters", disasterRepo.findAll());
        model.addAttribute("departmentDisaster", DepartmentDisaster.builder().build());
        model.addAttribute("allDepartmentDisasters", depDisasterRepo.findAllDisastersForDepartmentId(curDepId));
    }

}
