package ru.ValolEr.serverApi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class WebCalcController {
    @GetMapping("/calculator")
    public String webCalc(@RequestParam(value = "operandA", required = false) int operandA,
                          @RequestParam(value = "action", required = false) String action,
                          @RequestParam(value = "operandB", required = false) int operandB,
                          Model calcModel){
try {
    int a = operandA;
    int b = operandB;

    double result;

    switch (action) {
        case ("multiplication"): {
            result = a * b;
            calcModel.addAttribute("Result", "MUL is " + result);
            break;
        }
        case ("addition"): {
             result = a + b;
            calcModel.addAttribute("Result", "ADD is " + result);
            break;
        }
        case ("subtraction"): {
             result = a - b;
            calcModel.addAttribute("Result", "SUB is " + result);
            break;
        }
        case ("division"): {
            if (b == 0) {
                calcModel.addAttribute("Result", "Error! Division by zero!");
            } else {
                result = a / (double) b;
                calcModel.addAttribute("Result", "DEV is " + result);
            }
            break;
        }
    }
}
catch (Exception e){
    calcModel.addAttribute("Result", e.getMessage());
}
        return "first/calculator";
    }
}
