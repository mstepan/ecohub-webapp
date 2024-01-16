package com.github.kostia.ecohub.flows.employee;

import com.github.kostia.ecohub.flows.department.DepartmentRepo;
import com.github.kostia.ecohub.flows.manager.ManagerRepo;
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
public class EmployeeController {


    private final EmployeeRepo employeeRepo;

    private final DepartmentRepo departmentRepo;

    private final ManagerRepo managerRepo;

    public EmployeeController(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, ManagerRepo managerRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.managerRepo = managerRepo;
    }

    @GetMapping("/employee")
    public String employee(Model model) {
        fillDataModel(model);

        return "employee";
    }

    @PostMapping("/employee")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        if( employee.getAge() < 16 || employee.getAge() > 120 ){
            model.addAttribute("errorMessage",
                "Incorrect employee age, should be in range [16; 120]");
        }
        else {
            try {
                employeeRepo.save(employee);
            } catch (DbActionExecutionException dbEx) {
                log.error("Can't save Employee");
                model.addAttribute("errorMessage",
                    "Can't save Employee");
            }
        }

        fillDataModel(model);

        return "employee";
    }

    @GetMapping("/employee/delete")
    public String deleteEmployee(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            employeeRepo.deleteById(Integer.parseInt(employeeId));
        }
        catch(DbActionExecutionException dbEx){
            log.error("Can't delete Employee");
            model.addAttribute("errorMessage",
                "Can't delete Employee");
        }

        fillDataModel(model);

        return "employee";
    }

    private void fillDataModel(Model model){
        model.addAttribute("employee", Employee.builder().build());

        model.addAttribute("allDepartments", departmentRepo.findAll());
        model.addAttribute("allManagers", managerRepo.findAll());

        model.addAttribute("allEmployees", employeeRepo.findAll());
    }

}
