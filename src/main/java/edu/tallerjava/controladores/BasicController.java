package edu.tallerjava.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping(path = "/isAlive")
    public String smoke() {
        return "=)";
    }
}
