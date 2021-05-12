package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String RollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        String message;
        int randomNumber = (int)(Math.random()*(6-1+1)+1);

        if (guess == randomNumber) {
            message = "Good guess!  ";
        }
        else {
            message ="Sorry, try again.  ";
        }

        model.addAttribute("randomNumber", randomNumber);
        model.addAttribute("guess", guess);
        model.addAttribute("message", message);

        return "roll-dice";
    }
}
