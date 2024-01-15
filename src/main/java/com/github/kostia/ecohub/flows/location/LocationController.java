package com.github.kostia.ecohub.flows.location;

import lombok.extern.slf4j.Slf4j;
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
        model.addAttribute("location", Location.builder().build());
        model.addAttribute("allLocations", locationRepo.findAll());

        return "location";
    }

    @PostMapping("/location")
    public String addLocation(@ModelAttribute Location location, Model model) {
        locationRepo.save(location);

        model.addAttribute("location", Location.builder().build());
        model.addAttribute("allLocations", locationRepo.findAll());

        return "location";
    }

    @GetMapping("/location/delete")
    public String deleteLocation(@RequestParam("locationId") String locationId, Model model) {

        locationRepo.deleteById(Integer.parseInt(locationId));

        model.addAttribute("location", Location.builder().build());
        model.addAttribute("allLocations", locationRepo.findAll());

        return "redirect:/location";
    }

}
