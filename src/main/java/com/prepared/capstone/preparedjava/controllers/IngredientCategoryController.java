package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.IngredientCategory;
import com.prepared.capstone.preparedjava.models.data.IngredientCategoryDao;
import com.prepared.capstone.preparedjava.models.forms.EditIngredientCategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="ingredients/category")
public class IngredientCategoryController {

    @Autowired
    private IngredientCategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Ingredient Categories");

        return "ingredient-category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute("category", new IngredientCategory());
        model.addAttribute("stuff", "yo");
        return "ingredient-category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid IngredientCategory newCategory,
                       Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("stuff", "yup. i made it to here.");
            return "ingredient-category/add";
        }

        categoryDao.save(newCategory);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "ingredient-category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int categoryId) {

        categoryDao.delete(categoryId);

        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {

        IngredientCategory category = categoryDao.findOne(id);

        EditIngredientCategoryForm form = new EditIngredientCategoryForm(category);
        form.setName(category.getName());

        model.addAttribute("title", "Edit Category: " + category.getName());
        model.addAttribute("form", form);

        return "ingredient-category/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editCategory( Model model, @ModelAttribute @Valid EditIngredientCategoryForm form,
                                        Errors errors) {
        IngredientCategory category = categoryDao.findOne(form.getCategoryId());

        if (errors.hasErrors()) {
            // re-set the form bc the data is lost? somehow...
            form.setCategory(category);
            model.addAttribute("form", form);
            return "ingredient-category/edit";
        }

        category.setName(form.getName());
        categoryDao.save(category);
        return "redirect:/ingredients/category";
    }
}
