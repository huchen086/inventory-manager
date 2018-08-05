package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.DAO.MerchandiseDao;
import org.goldencloud.inventorymanager.models.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseDao merchandiseDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayMerchandise(Model model) {
        model.addAttribute("title","Merchandise List");
        model.addAttribute("merchandises",merchandiseDao.findAll());
        return "merchandise/index";
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute("title","Add New Merchandise");
        model.addAttribute(new Merchandise());
        return "merchandise/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute("merchandise") @Valid Merchandise newMerchandise, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Add New Merchandise");
            model.addAttribute("merchandise",newMerchandise);
            return "merchandise/add";
        } else {
            merchandiseDao.save(newMerchandise);
            return "redirect:/merchandise";
        }
    }

}
