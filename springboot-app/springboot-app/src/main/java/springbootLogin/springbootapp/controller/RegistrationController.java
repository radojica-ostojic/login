package springbootLogin.springbootapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springbootLogin.springbootapp.config.security.CustomHandler;
import springbootLogin.springbootapp.entity.RegistrationRequest;
import springbootLogin.springbootapp.service.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    public CustomHandler customHandler;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    // using PreAuthorize annotation, pages admin.html and user.html can't be accessed without previous login
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/admin.html")
    public String redirectionAdmin(){
        return "redirect:/admin.html";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "/user.html")
    public String redirectionUser(){
        return "redirect:/user.html";
    }
}
