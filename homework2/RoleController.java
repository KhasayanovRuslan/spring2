package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductImage;
import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/add/{id}")
    public String addNewRole(Model model, @PathVariable(name = "id") Long id) {
        Role newRole = roleService.getRoleById(id);
        if (newRole == null) {
            newRole = new Role();
            newRole.setId(0L);
        }
        model.addAttribute("newRole", newRole);
        return "add-role";
    }

    @PostMapping("/add")
    public String processRoleAddForm(@Valid @ModelAttribute("newRole") Role newRole, BindingResult theBindingResult, Model model) {
        if (newRole.getId() == 0 && roleService.isRoleWithNameExists(newRole.getName())) {
            theBindingResult.addError(new ObjectError("role.name", "Роль с таким названием уже существует"));
        }

        if (theBindingResult.hasErrors()) {
            return "add-role";
        }

        roleService.saveNewRole(newRole);
        return "redirect:/shop";
    }

//    @PostMapping("/add")
//    public String addNewRole(Model model, String nameRole) {
//        Role newRole = new Role(nameRole);
//        roleService.saveNewRole(newRole);
//        model.addAttribute("newRole", newRole);
//        return "add-role";
//    }
}
