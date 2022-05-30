package com.example.work_project.controller.person;

import com.example.work_project.model.Department;
import com.example.work_project.model.Employee;
import com.example.work_project.model.Gender;
import com.example.work_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PersonController {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public PersonController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public String getPersonList(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee/MainEmployeesPage";
    }

    @GetMapping("/employees/add")
    public String addEmployee(Model model){
        return "employee/CreateEmployee";
    }

    @PostMapping("/employees/add")
    public String addPerson(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam int age,
                            @RequestParam(value = "gender") Gender gender,
                            @RequestParam(value = "department") Department department,
                            Model model){

        Employee employee = new Employee(firstName, lastName, age, gender, department);
        department.addEmployeeToDepartment(employee);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{personId}/edit")
    public String employeeInfo(@PathVariable(value = "personId") long id ,Model model){
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();
        employee.ifPresent(result::add);
        model.addAttribute("employee", result);
        return "employee/employee-edit";
    }

    @PostMapping("/employees/{personId}/edit")
    public String editPerson(@PathVariable(value = "personId") long id,@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam int age,
                             @RequestParam(value = "gender") Gender gender,
                             @RequestParam(value = "department") Department department,
                             Model model){
    Employee employee = employeeRepository.findById(id).orElseThrow();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setAge(age);
    employee.setGender(gender);
    employee.setDepartment(department);
    employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{personId}/remove")
    public String removePerson(@PathVariable(value = "personId") long id ,Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);

        return "redirect:/employees";
    }
}
