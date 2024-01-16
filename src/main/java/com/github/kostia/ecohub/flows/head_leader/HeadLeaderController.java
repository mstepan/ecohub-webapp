package com.github.kostia.ecohub.flows.head_leader;

import com.github.kostia.ecohub.flows.location.Location;
import com.github.kostia.ecohub.flows.location.LocationRepo;
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
public class HeadLeaderController {


    private final HeadLeaderRepo headLeaderRepo;

    public HeadLeaderController(HeadLeaderRepo headLeaderRepo) {
        this.headLeaderRepo = headLeaderRepo;
    }

    @GetMapping("/head_leader")
    public String location(Model model) {
        fillDataModel(model);

        return "head_leader";
    }

    @PostMapping("/head_leader")
    public String addLocation(@ModelAttribute HeadLeader headLeader, Model model) {
        headLeaderRepo.save(headLeader);

        fillDataModel(model);

        return "head_leader";
    }

    @GetMapping("/head_leader/delete")
    public String deleteLocation(@RequestParam("headLeaderId") String headLeaderId, Model model) {
        try {
            headLeaderRepo.deleteById(Integer.parseInt(headLeaderId));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete head leader");
            model.addAttribute("errorMessage",
                "Can't delete head leader");
        }

        fillDataModel(model);

        return "head_leader";
    }

    private void fillDataModel(Model model){
        model.addAttribute("headLeader", HeadLeader.builder().build());
        model.addAttribute("allHeadLeaders", headLeaderRepo.findAll());
    }

}
