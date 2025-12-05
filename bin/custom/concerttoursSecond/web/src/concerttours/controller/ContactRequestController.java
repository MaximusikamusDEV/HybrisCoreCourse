package concerttours.controller;

import concerttours.service.ContactRequestService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.training.model.ContactRequestModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;


@Controller
public class ContactRequestController {
    private final ContactRequestService contactRequestService;
    private final ModelService modelService;

    public ContactRequestController(ContactRequestService contactRequestService, ModelService modelService) {
        this.contactRequestService = contactRequestService;
        this.modelService = modelService;
    }

    @GetMapping("/getContact")
    public String getContactRequest(@RequestParam(required = false) String sender, Model model) {

        if (!StringUtils.isEmpty(sender)) {
            try {
                ContactRequestModel contactRequestModel = contactRequestService.getContactRequestBySender(sender);
                model.addAttribute("contactRequest", contactRequestModel);
            } catch (UnknownIdentifierException e) {
                model.addAttribute("error", "sender not found: " + sender);
            }
        }

        return "contactRequest";
    }

    @PostMapping("/addContact")
    public String saveContactRequest(@RequestParam(required = false) String newSender,
                                     @RequestParam(required = false) String newMessage,
                                     Model model) {
        if (!StringUtils.isEmpty(newSender) && !StringUtils.isEmpty(newMessage)) {
            ContactRequestModel contactRequestModel = modelService.create(ContactRequestModel.class);
            contactRequestModel.setSender(newSender);
            contactRequestModel.setMessage(newMessage);
            modelService.save(contactRequestModel);
            model.addAttribute("contactRequest", contactRequestModel);
        }

        return "contactRequest";
    }

}
