package org.goldencloud.inventorymanager.controllers;

import org.goldencloud.inventorymanager.models.SaleEvent;
import org.goldencloud.inventorymanager.models.dao.SaleEventDao;
import org.goldencloud.inventorymanager.services.SaleEventService;
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
@RequestMapping("event")
public class EventController {

    @Autowired
    private SaleEventDao saleEventDao;

    @Autowired
    private SaleEventService saleEventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayEvents(Model model) {

        model.addAttribute("title", "Event List");
        model.addAttribute("events", saleEventDao.findAll());
        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddEvent(Model model) {
        model.addAttribute("title", "Add a New Event");
        model.addAttribute("event", new SaleEvent());
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEvent(@ModelAttribute("event") @Valid SaleEvent newEvent,
                                  BindingResult result,
                                  Errors errors,
                                  Model model) {
        if (errors.hasErrors() || result.hasErrors()) {
            model.addAttribute("title", "Add a New Event");
            return "event/add";
        } else {
            saleEventDao.save(newEvent);
            saleEventService.initializeNewSaleEvent(newEvent);
            long id = newEvent.getId();
            return "redirect:/event";
        }
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewEvent(Model model, @PathVariable Long id) {
        SaleEvent event = saleEventDao.findById(id).orElse(null);
        if (event == null) {
            return "redirect:/event";
        }
        model.addAttribute("title", "Sale Event Info");
        model.addAttribute("event", event);
        //model.addAttribute("items", event.getItems());
        return "event/view";
    }
}
