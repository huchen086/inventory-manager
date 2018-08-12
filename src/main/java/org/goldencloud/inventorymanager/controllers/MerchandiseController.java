package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.models.Inventory;
import org.goldencloud.inventorymanager.models.dao.InventoryDao;
import org.goldencloud.inventorymanager.models.dao.MerchandiseDao;
import org.goldencloud.inventorymanager.models.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseDao merchandiseDao;

    @Autowired
    private InventoryDao inventoryDao;

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
    public String processAddForm(@ModelAttribute("merchandise") @Valid Merchandise newMerchandise,
                                 BindingResult result,
                                 Errors errors,
                                 Model model) {

        // NEED TO VALIDATE INVENTORY.QUANTITY
        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title","Add New Merchandise");
            return "merchandise/add";
        } else {
            Inventory newInventory = new Inventory(newMerchandise,newMerchandise.getInventory().getQuantity());
            newMerchandise.setInventory(newInventory);
            merchandiseDao.save(newMerchandise);
            return "redirect:/merchandise";
        }
    }

    @RequestMapping(value = "edit/{sku}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable String sku) {

        Merchandise merchandiseToEdit = merchandiseDao.findById(sku).orElse(null);

        if(merchandiseToEdit==null) {
            return "redirect:/merchandise";
        } else {
            model.addAttribute("title", "Edit Inventory");
            model.addAttribute("merchandiseToEdit", merchandiseToEdit);
            model.addAttribute("inventoryToEdit", merchandiseToEdit.getInventory());
            return "merchandise/edit";
        }

    }

    @RequestMapping(value = "edit/{sku}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable String sku,
                                  @ModelAttribute("merchandiseToEdit") @Valid Merchandise merchandiseToEdit,
                                  BindingResult result,
                                  //@ModelAttribute("inventoryToEdit") @Valid Inventory inventoryToEdit,
                                  //BindingResult resultInv,
                                  Errors errors,
                                  Model model) {

        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Edit Inventory");
            return "merchandise/edit";
        }

        Inventory newInventory = new Inventory(merchandiseToEdit, merchandiseToEdit.getInventory().getQuantity());
        merchandiseToEdit.setInventory(newInventory);
        merchandiseDao.save(merchandiseToEdit);
        return "redirect:/merchandise";
    }

}
