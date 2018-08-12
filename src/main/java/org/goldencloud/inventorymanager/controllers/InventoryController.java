package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.models.InventoryDetail;
import org.goldencloud.inventorymanager.models.dao.InventoryDetailDao;
import org.goldencloud.inventorymanager.models.dao.InventoryDao;
import org.goldencloud.inventorymanager.models.Inventory;
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
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private InventoryDetailDao inventoryDetailDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayInventory(Model model) {
        model.addAttribute("title","Inventory List");
        model.addAttribute("inventories", inventoryDao.findAll());
        return "inventory/index";
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    public String displayAddForm(Model model) {
        model.addAttribute("title","Add New Merchandise");
        model.addAttribute("inventory", new Inventory());
        return "inventory/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute("inventory") @Valid Inventory newInventory,
                                 BindingResult result,
                                 Errors errors,
                                 Model model) {

        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title","Add New Merchandise");
            return "inventory/add";
        } else {
            //Could move the code below to a separate service layer for business logic
            InventoryDetail newInventoryDetail = new InventoryDetail(newInventory, newInventory.getInventoryDetail().getQuantity());
            newInventory.setInventoryDetail(newInventoryDetail);
            inventoryDao.save(newInventory);
            return "redirect:/inventory";
        }
    }

    @RequestMapping(value = "edit/{sku}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable String sku) {

        Inventory inventoryToEdit = inventoryDao.findById(sku).orElse(null);

        if(inventoryToEdit ==null) {
            return "redirect:/inventory";
        } else {
            model.addAttribute("title", "Update Inventory Details");
            model.addAttribute("inventoryToEdit", inventoryToEdit);
            return "inventory/edit";
        }

    }

    @RequestMapping(value = "edit/{sku}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable String sku,
                                  @ModelAttribute("inventoryToEdit") @Valid Inventory inventoryToEdit,
                                  BindingResult result,
                                  Errors errors,
                                  Model model) {

        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Edit InventoryDetail");
            return "inventory/edit";
        }

        InventoryDetail newInventoryDetail = new InventoryDetail(inventoryToEdit, inventoryToEdit.getInventoryDetail().getQuantity());
        inventoryToEdit.setInventoryDetail(newInventoryDetail);
        inventoryDao.save(inventoryToEdit);
        return "redirect:/inventory";
    }

}
