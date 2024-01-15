package com.github.kostia.ecohub.flows.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class LocationController {

    @GetMapping("/location")
    public String location(Model model) {

        model.addAttribute("location", new Location());

        List<Location> allLocations = List.of(
            new Location("Bucuresti-Ploiesti-111", "077166", "Tancabesti-1", "Romania"),
            new Location("Bucuresti-Ploiesti-222", "077167", "Tancabesti-2", "Romania")
        );

        model.addAttribute("allLocations", allLocations);

        return "location";
    }

    @PostMapping("/location")
    public String addLocation(@ModelAttribute Location location, Model model) {
        log.info("location to add {}", location);

        model.addAttribute("location", new Location());

        //TODO:
        return "location";
    }

    @DeleteMapping("/location")
    public String deleteLocation(Model model) {
        //TODO:
        return "location";
    }

}
