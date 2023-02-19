package springbootLogin.springbootapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springbootLogin.springbootapp.entity.RegistrationRequest;
import springbootLogin.springbootapp.service.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
