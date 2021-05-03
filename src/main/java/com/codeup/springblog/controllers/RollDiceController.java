package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model) {

            //Find a random number between 1 and 6.
            int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
            //Compare the guess to the random number.
            //Store if the number was guessed correctly in a model attribute.
            model.addAttribute("diceRoll", randomNum);
            model.addAttribute("userChoice", n);
            model.addAttribute("isCorrect", n == randomNum);

            return "/roll-dice";
//        int randomNum = (int)(Math.random() * (7 - 1) + 1);
//
//        model.addAttribute("diceRoll", randomNum);
//        model.addAttribute("userChoice", n);
//        model.addAttribute("isCorrect", randomNum == n);
//        return "roll-dice";

    }

}