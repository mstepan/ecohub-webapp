package com.github.kostia.ecohub.flows.department_disaster;

import com.github.kostia.ecohub.flows.disaster.Disaster;
import com.github.kostia.ecohub.flows.disaster.DisasterRepo;
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


    private final DisasterRepo disasterRepo;

    public DepartmentDisasterController(DisasterRepo disasterRepo) {
        this.disasterRepo = disasterRepo;
    }

    @GetMapping("/department_disaster")
    public String departmentDisaster(Model model) {
        fillDataModel(model);

        return "department_disaster";
    }

    @PostMapping("/department_disaster")
    public String addDepartmentDisaster(@ModelAttribute Disaster disaster, Model model) {
        disasterRepo.save(disaster);

        fillDataModel(model);

        return "department_disaster";
    }

    @GetMapping("/department_disaster/delete")
    public String deleteDepartmentDisaster(@RequestParam("disasterId") String disaster, Model model) {
        try {
            disasterRepo.deleteById(Integer.parseInt(disaster));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete disaster for department");
            model.addAttribute("errorMessage", "Can't delete disaster for department");
        }

        fillDataModel(model);

        return "department_disaster";
    }

    private void fillDataModel(Model model){
        model.addAttribute("departmentDisaster", Disaster.builder().build());
        model.addAttribute("allDepartmentDisasters", disasterRepo.findAll());
    }

}
