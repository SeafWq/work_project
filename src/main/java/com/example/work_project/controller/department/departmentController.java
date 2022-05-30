package com.example.work_project.controller.department;

import com.example.work_project.model.Category;
import com.example.work_project.model.Department;
import com.example.work_project.model.Employee;
import com.example.work_project.model.Gender;
import com.example.work_project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class departmentController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public departmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/departments")
    public String getDepartmentList(Model model){
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "department/departments-info";
    }

    @GetMapping("/departments/add")
    public String addDepartment(Model model) {
        return "department/CreateDepartment";
    }

    @PostMapping("/departments/add")
    public String addDepartments(@RequestParam String name,
                            @RequestParam(value = "category") Category category,
                            Model model){
        Department department = new Department(name, category);
        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String departmentInfo(@PathVariable(value = "id") int id , Model model){
        Optional<Department> department = departmentRepository.findById(id);
        ArrayList<Department> result = new ArrayList<>();
        department.ifPresent(result::add);
        model.addAttribute("department", result);
        return "department/department-edit";
    }

    @PostMapping("/departments/{id}/edit")
    public String editDepartment(@PathVariable(value = "id") int id,
                             @RequestParam String name,
                             @RequestParam(value = "category") Category category,
                             Model model){
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(name);
        department.setCategory(category);

        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @PostMapping("/departments/{id}/remove")
    public String removeDepartment(@PathVariable(value = "id") int id ,Model model){
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);

        return "redirect:/departments";
    }

}
