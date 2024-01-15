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

import java.util.List;


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
        fillDataModel(model);

        return "department";
    }

    @PostMapping("/department")
    public String addDepartment(@ModelAttribute Department department, Model model) {

        departmentRepo.save(department);

        fillDataModel(model);

        return "department";
    }

    @GetMapping("/department/delete")
    public String deleteDepartment(@RequestParam("departmentId") String departmentId, Model model) {

        departmentRepo.deleteById(Integer.parseInt(departmentId));

        fillDataModel(model);

        return "redirect:/department";
    }

    private void fillDataModel(Model model) {
        model.addAttribute("department", Department.builder().build());

        List<Department> allDepartments = departmentRepo.findAllWithLocations();

        allDepartments.forEach( singleDep ->
            singleDep.setLocation(locationRepo.findById(singleDep.getLocationId()).orElseThrow()));

        model.addAttribute("allLocations", locationRepo.findAll());
        model.addAttribute("allDepartments",  allDepartments);
    }

}
