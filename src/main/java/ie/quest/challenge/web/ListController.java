package ie.quest.challenge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.quest.challenge.service.PersonService;

@Controller
@RequestMapping("/list")
public class ListController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("list", personService.findAll());
        return "list";
    }
    
}
