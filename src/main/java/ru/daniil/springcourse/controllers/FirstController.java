package ru.daniil.springcourse.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(Model model, @RequestParam(value = "name",required = false) String name, @RequestParam(value = "surname",required = false)String surname){


        model.addAttribute("message","Hello, "+name+" "+surname);

        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }
    @GetMapping("/calculator")
    public String calculator(Model model, @RequestParam(value = "a",required = false) int a, @RequestParam(value = "b",required = false) int b, @RequestParam(value = "action",required = false) String action){
        int answer = 0;
        switch (action){
            case("multiplication"):{ answer = multiplication(a,b);
            }
            break;
            case("addition"):{
                answer = addition(a,b);
            }
            break;
            case("subtraction"):{
                answer = subtraction(a,b);
            }
            break;
            case("division"):{
                answer = division(a,b);
            }
            break;
            default:{
                model.addAttribute("answer","invalid action!");
            }

        }
        model.addAttribute("answer","Answer with a and b, on action "+action+" is: "+answer);

        return "first/calculator";

    }

    private static int multiplication(int a, int b){
        return a*b;
    }
    private static int addition(int a,int b){
        return a*b;
    }
    private static int subtraction(int a, int b){
        return a-b;
    }
    private static int division(int a,int b){
        return a/b;
    }
}
