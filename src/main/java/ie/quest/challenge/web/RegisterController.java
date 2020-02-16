package ie.quest.challenge.web;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.quest.challenge.model.Person;
import ie.quest.challenge.service.PersonService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private PersonService personService;
    
    @GetMapping
    public String showRegisterForm(Model model) {
    	model.addAttribute("person", new Person());
    	return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("person") @Valid Person person,
                                      BindingResult result){

        Person existing = personService.findByPps(person.getPps());
        if (existing != null){
            result.rejectValue("pps", null, "There is already an account registered with that PPS number");
        }

        long age = LocalDate.from(person.getBirth().toInstant()
        	      .atZone(ZoneId.systemDefault())
        	      .toLocalDateTime()).until(LocalDate.now(), ChronoUnit.YEARS);
        
        if(age < 16){
        	result.rejectValue("birth", null, "Must be over 16 years old");
        }
        
        if(!person.getMobile().isEmpty()  && !person.getMobile().startsWith("08")){
        	result.rejectValue("mobile", null, "Must begin with 08 prefix");
        }
        
        
        if (result.hasErrors()){
            return "register";
        }

        personService.save(person);
        return "redirect:/register?success";
    }

}
