package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.models.Inventory;
import org.goldencloud.inventorymanager.models.InventoryDetail;
import org.goldencloud.inventorymanager.models.dao.InventoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryDao inventoryDao;

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
            //Could move the code below to a separate service layer for business logic?
            InventoryDetail newInventoryDetail
                    = new InventoryDetail(newInventory.getInventoryDetail().getQuantity(),
                                        newInventory.getInventoryDetail().getPrice());
            newInventoryDetail.setInventory(newInventory);
            newInventory.setInventoryDetail(newInventoryDetail);
            inventoryDao.save(newInventory);
            return "redirect:/inventory";
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable Long id) {

        Inventory inventoryToEdit = inventoryDao.findById(id).orElse(null);

        if(inventoryToEdit ==null) {
            return "redirect:/inventory";
        } else {
            model.addAttribute("title", "Update Inventory Details");
            model.addAttribute("inventoryToEdit", inventoryToEdit);
            return "inventory/edit";
        }

    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable long id,
                                  @ModelAttribute("inventoryToEdit") @Valid Inventory inventoryToEdit,
                                  BindingResult result,
                                  Errors errors,
                                  Model model) {

        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Edit InventoryDetail");
            return "inventory/edit";
        }

        // set parameters for the newInventoryDetail
        Inventory newInventory = inventoryDao.findById(id).orElse(null);
        InventoryDetail newInventoryDetail = newInventory.getInventoryDetail();
        newInventoryDetail.setQuantity(inventoryToEdit.getInventoryDetail().getQuantity());
        newInventoryDetail.setPrice(inventoryToEdit.getInventoryDetail().getPrice());

        inventoryToEdit.setInventoryDetail(newInventoryDetail);
        inventoryDao.save(inventoryToEdit);
        return "redirect:/inventory";
    }

}
