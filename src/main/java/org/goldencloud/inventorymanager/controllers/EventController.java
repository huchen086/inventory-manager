package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.models.SaleEvent;
import org.goldencloud.inventorymanager.models.SaleItem;
import org.goldencloud.inventorymanager.models.dao.SaleEventDao;
import org.goldencloud.inventorymanager.models.dto.SaleEventDto;
import org.goldencloud.inventorymanager.models.dto.SaleItemsDto;
import org.goldencloud.inventorymanager.services.SaleEventService;
import org.goldencloud.inventorymanager.services.SaleItemService;
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
import java.util.List;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private SaleEventDao saleEventDao;

    @Autowired
    private SaleEventService saleEventService;

    @Autowired
    private SaleItemService saleItemService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayEvents(Model model) {

        model.addAttribute("title", "Sales Event List");
        model.addAttribute("events", saleEventDao.findAll());
        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddEvent(Model model) {
        model.addAttribute("title", "Add a New Sales Event");
        model.addAttribute("event", new SaleEvent());
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEvent(@ModelAttribute("event") @Valid SaleEvent newEvent,
                                  BindingResult result,
                                  Errors errors,
                                  Model model) {
        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Add a New Sales Event");
            return "event/add";
        } else {
            saleEventDao.save(newEvent);
            saleEventService.initializeNewSaleEvent(newEvent);
            long id = newEvent.getId();
            return "redirect:/event";
        }
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewEvent(Model model, @PathVariable long id) {
        SaleEvent event = saleEventDao.findById(id).orElse(null);
        if (event == null) {
            return "redirect:/event";
        }
        model.addAttribute("title", "Sales Event Info");
        model.addAttribute("event", event);
        return "event/view";
    }

    @RequestMapping(value = "quantity/{id}", method = RequestMethod.GET)
    public String displayEditQuantity(Model model, @PathVariable long id) {
        SaleEvent event = saleEventDao.findById(id).orElse(null);
        if (event == null) {
            return "redirect:/event";
        }
        List<SaleItem> items = (List<SaleItem>) event.getItems();
        SaleItemsDto itemsForm = new SaleItemsDto(items);
        model.addAttribute("title", "Edit the Quantity of Sales Item");
        model.addAttribute("event", event);
        model.addAttribute("itemsform", itemsForm);
        return "event/editquantity";
    }

    @RequestMapping(value = "quantity/{id}", method = RequestMethod.POST)
    public String updateQuantity(@ModelAttribute("itemsform") @Valid SaleItemsDto itemsForm,
                                 BindingResult result,
                                 Errors errors,
                                 @PathVariable long id,
                                 Model model) {

        SaleEvent event = saleEventDao.findById(id).orElse(null);
        if (event == null) {
            return "redirect:/event";
        }

        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("msg", "invalid quantity");
            return displayEditQuantity(model,id);
        }

        List<SaleItem> newItems = itemsForm.getItems();
        try {
            saleItemService.updateQuantity(newItems);
        } catch (IllegalArgumentException e) {
            model.addAttribute("msg", e.getMessage());
            return displayEditQuantity(model,id);
        }
        return "redirect:/event/view/"+id;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEdit(Model model, @PathVariable long id) {

        SaleEvent event = saleEventDao.findById(id).orElse(null);
        if (event == null) {
            return "redirect:/event";
        }
        model.addAttribute("title", "Edit Sales Event Info");
        model.addAttribute("event", event);
        return "event/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEdit(@PathVariable long id,
                              @ModelAttribute("event") @Valid SaleEventDto eventDto,
                              BindingResult result,
                              Errors errors,
                              Model model) {
        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Edit Sales Event Info");
            return "event/edit";
        }
        SaleEvent theEvent = saleEventDao.findById(eventDto.getId()).orElse(null);
        if (theEvent == null) {
            return "redirect:/event";
        }
        theEvent.setName(eventDto.getName());
        theEvent.setDate(eventDto.getDate());
        theEvent.setLocation(eventDto.getLocation());
        saleEventDao.save(theEvent);
        return "redirect:/event";

    }
}
