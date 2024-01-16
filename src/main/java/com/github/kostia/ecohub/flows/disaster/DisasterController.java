package com.github.kostia.ecohub.flows.disaster;

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
public class DisasterController {


    private final DisasterRepo disasterRepo;

    public DisasterController(DisasterRepo disasterRepo) {
        this.disasterRepo = disasterRepo;
    }

    @GetMapping("/disaster")
    public String disaster(Model model) {
        fillDataModel(model);

        return "disaster";
    }

    @PostMapping("/disaster")
    public String addDisaster(@ModelAttribute Disaster disaster, Model model) {
        disasterRepo.save(disaster);

        fillDataModel(model);

        return "disaster";
    }

    @GetMapping("/disaster/delete")
    public String deleteDisaster(@RequestParam("disasterId") String disaster, Model model) {
        try {
            disasterRepo.deleteById(Integer.parseInt(disaster));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete disaster");
            model.addAttribute("errorMessage", "Can't delete disaster");
        }

        fillDataModel(model);

        return "disaster";
    }

    private void fillDataModel(Model model){
        model.addAttribute("disaster", Disaster.builder().build());
        model.addAttribute("allDisasters", disasterRepo.findAll());
    }

}
