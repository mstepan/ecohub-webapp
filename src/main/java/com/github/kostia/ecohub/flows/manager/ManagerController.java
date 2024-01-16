package com.github.kostia.ecohub.flows.manager;

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
public class ManagerController {


    private final ManagerRepo managerRepo;

    public ManagerController(ManagerRepo managerRepo) {
        this.managerRepo = managerRepo;
    }

    @GetMapping("/manager")
    public String manager(Model model) {
        fillDataModel(model);

        return "manager";
    }

    @PostMapping("/manager")
    public String addManager(@ModelAttribute Manager manager, Model model) {
        managerRepo.save(manager);

        fillDataModel(model);

        return "manager";
    }

    @GetMapping("/manager/delete")
    public String deleteManager(@RequestParam("managerId") String managerId, Model model) {
        try {
            managerRepo.deleteById(Integer.parseInt(managerId));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete Manager");
            model.addAttribute("errorMessage",
                "Can't delete Manager");
        }

        fillDataModel(model);

        return "manager";
    }

    private void fillDataModel(Model model){
        model.addAttribute("manager", Manager.builder().build());
        model.addAttribute("allManagers", managerRepo.findAll());
    }

}
