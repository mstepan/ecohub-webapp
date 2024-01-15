package com.github.kostia.ecohub.flows.department;

import com.github.kostia.ecohub.flows.location.LocationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class DepartmentController {


    private final DepartmentRepo departmentRepo;

    private final LocationRepo locationRepo;

    public DepartmentController(DepartmentRepo departmentRepo, LocationRepo locationRepo) {
        this.departmentRepo = departmentRepo;
        this.locationRepo = locationRepo;
    }

    @GetMapping("/department")
    public String department(Model model) {

        model.addAttribute("department", Department.builder().build());

        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allDepartments", departmentRepo.findAll());

        return "department";
    }

    @PostMapping("/department")
    public String addDepartment(@ModelAttribute Department department, Model model) {
        departmentRepo.save(department);

        model.addAttribute("department", Department.builder().build());

        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allDepartments", departmentRepo.findAll());

        return "department";
    }

    @DeleteMapping("/department")
    public String deleteDepartment(@RequestParam("departmentId") String departmentId, Model model) {

        departmentRepo.deleteById(Integer.parseInt(departmentId));

        model.addAttribute("department", Department.builder().build());

        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allDepartments", departmentRepo.findAll());

        return "department";
    }

}
