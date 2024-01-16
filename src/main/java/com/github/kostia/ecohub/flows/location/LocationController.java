package com.github.kostia.ecohub.flows.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LocationController {


    private final LocationRepo locationRepo;

    public LocationController(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @GetMapping("/location")
    public String location(Model model) {
        fillDataModel(model);

        return "location";
    }

    @PostMapping("/location")
    public String addLocation(@ModelAttribute Location location, Model model) {
        locationRepo.save(location);

        fillDataModel(model);

        return "location";
    }

    @GetMapping("/location/delete")
    public String deleteLocation(@RequestParam("locationId") String locationId, Model model) {
        try {
            locationRepo.deleteById(Integer.parseInt(locationId));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete location, there is department related to location");
            model.addAttribute("errorMessage",
                "Can't delete location, there is department related to location");
        }

        fillDataModel(model);

        return "location";
    }

    private void fillDataModel(Model model){
        model.addAttribute("location", Location.builder().build());
        model.addAttribute("allLocations", locationRepo.findAll());
    }

}
