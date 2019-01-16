package com.prepared.capstone.preparedjava.controllers;

import com.prepared.capstone.preparedjava.models.MeasurementType;
import com.prepared.capstone.preparedjava.models.Unit;
import com.prepared.capstone.preparedjava.models.data.MeasurementTypeDao;
import com.prepared.capstone.preparedjava.models.data.UnitDao;
import com.prepared.capstone.preparedjava.models.forms.AddUnitForm;
import com.prepared.capstone.preparedjava.models.forms.EditUnitForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="units")
public class UnitController {

    @Autowired
    UnitDao unitDao;

    @Autowired
    MeasurementTypeDao measurementTypeDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("units", unitDao.findAll());
        model.addAttribute("title", "Units");

        return "units/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        AddUnitForm form = new AddUnitForm(measurementTypeDao.findAll());

        model.addAttribute("title", "Add Unit");
        model.addAttribute("form", form);
        return "units/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid AddUnitForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "units/add";
        }

        // TODO - Form Bug: If abbrev are empty or invalid, it refreshes with no data in the page or form
        // it's not getting to the db, no form Errors popping up... I don't get it...

        Unit newUnit = new Unit(form.getName(), form.getAbbrev(), measurementTypeDao.findOne(form.getTypeId()));
        unitDao.save(newUnit);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("units", unitDao.findAll());
        model.addAttribute("title", "Remove Unit");
        return "units/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int unitId) {

        unitDao.delete(unitId);

        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {

        Unit unit = unitDao.findOne(id);
        Iterable<MeasurementType> types = measurementTypeDao.findAll();

        EditUnitForm form = new EditUnitForm(types, unit);
        form.setName(unit.getName());
        form.setTypeId(unit.getType().getId());

        model.addAttribute("title", "Edit Unit: " + unit.getName());
        model.addAttribute("form", form);

        return "units/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editUnit(Model model,
                           @ModelAttribute @Valid EditUnitForm form,
                           Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "units/edit";
        }

        Unit newUnit = unitDao.findOne(form.getUnitId());
        newUnit.setName(form.getName());
        newUnit.setType(measurementTypeDao.findOne(form.getTypeId()));
        unitDao.save(newUnit);
        return "redirect:/units";
    }
}
