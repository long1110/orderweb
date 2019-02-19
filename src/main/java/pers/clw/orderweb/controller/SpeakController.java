package pers.clw.orderweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpeakController {
    @GetMapping("speak")
    public String speak(){
        return "speak";
    }
}
