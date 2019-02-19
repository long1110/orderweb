package pers.clw.orderweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeController {
    @GetMapping("we")
    public String we(){
        return "we";
    }
}
