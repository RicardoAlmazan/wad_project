package mx.ipn.forms.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ApiController {
    @GetMapping("")
    public String api() {
        return "Forms - Service API";
    }

    @GetMapping("/ping")
    public String ping() {
        return "it's alive!";
    }
}