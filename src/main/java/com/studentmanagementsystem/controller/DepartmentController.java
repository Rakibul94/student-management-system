package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.data.DepartmentData;
import com.studentmanagementsystem.exceptions.NotFoundException;
import com.studentmanagementsystem.servicefacade.DepartmentServiceFacade;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceFacade departmentServiceFacade;

    public DepartmentController(DepartmentServiceFacade departmentServiceFacade) {
        this.departmentServiceFacade = departmentServiceFacade;
    }

    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable Long id,
                                   @Valid @ModelAttribute("departmentData") DepartmentData departmentData,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "department_edit";
        }

        try{
            departmentServiceFacade.updateDepartment(departmentData);
            redirectAttributes.addFlashAttribute("message", "Update Successful");
            return "redirect:/departments";
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "Unexpected Exception");
            return "redirect:/departments";
        }

    }

    @GetMapping("/{id}/edit")
    public String showEditDepartmentForm(@PathVariable Long id,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {

        try {
            model.addAttribute("departmentData", departmentServiceFacade.getDepartmentById(id));
            return "department_edit";
        }
        catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/departments";
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Unexpected Exception");
            return "redirect:/departments";
        }
    }


    @GetMapping("/new")
    public String showAddDepartmentForm(Model model) {
        //This empty object is created for thymeleaf to render the form field to this
        //object
        model.addAttribute("departmentData", new DepartmentData());
        return "department_add";
    }

    @GetMapping
    public String departmentList(Model model) {
        model.addAttribute("departmentList", departmentServiceFacade.getAllDepartments());
        return "department_list";
    }

    @PostMapping
    public String addDepartment(@Valid @ModelAttribute DepartmentData departmentData,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        //If Input validity is violated bindingResult shows error
        if(bindingResult.hasErrors()){
            return "department_add";
        }
        try{
            departmentServiceFacade.createDepartment(departmentData);
            redirectAttributes.addFlashAttribute("message", "Department added successfully");
            return "redirect:/departments";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("message", "Department add failed");
            return "redirect:/departments";
        }



    }

    @DeleteMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id,
                                    RedirectAttributes redirectAttributes) {

        try {
            departmentServiceFacade.deleteDepartmentById(id);
            redirectAttributes.addFlashAttribute("message", "Delete Successful");
            return "redirect:/departments";
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/departments";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Unexpected Exception");
            return "redirect:/departments";
        }


    }
}
