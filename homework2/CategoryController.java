package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.services.CategoryService;
import com.geekbrains.geekmarketwinter.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add/{id}")
    public String addNewCategory(Model model, @PathVariable(name = "id") Long id) {
        Category newCategory = categoryService.getCategoryById(id);
        if (newCategory == null) {
            newCategory = new Category();
            newCategory.setId(0L);
        }
        model.addAttribute("newCategory", newCategory);
        return "add-category";
    }

    @PostMapping("/add")
    public String processCategoryAddForm(@Valid @ModelAttribute("newCategory") Category newCategory, BindingResult theBindingResult, Model model) {
        if (newCategory.getId() == 0 && categoryService.isCategoryWithTitleExists(newCategory.getTitle())) {
            theBindingResult.addError(new ObjectError("category.title", "Категория с таким названием уже существует"));
        }

        if (theBindingResult.hasErrors()) {
            return "add-category";
        }

        categoryService.saveNewCategory(newCategory);
        return "redirect:/shop";
    }

}

