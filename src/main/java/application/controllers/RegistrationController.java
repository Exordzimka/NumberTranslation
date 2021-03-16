package application.controllers;

import application.models.NumberTranslationUser;
import application.models.Role;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController
{
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String registration()
    {
        return "registration";
    }

    @PostMapping
    public String registerUser(NumberTranslationUser user, Model model){

        if(userRepository.findByUsername(user.getUsername()) != null)
        {
            model.addAttribute("exception", "User already exists!");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
