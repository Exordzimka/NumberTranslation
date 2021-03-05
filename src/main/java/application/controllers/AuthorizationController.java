package application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class AuthorizationController
{
    @GetMapping
    public ModelAndView auth()
    {
        return new ModelAndView("login");
    }
}
