package main.java.javeriana.ms.sustractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySubtractController {
    @Autowired
    Environment environment;

    @GetMapping("/resta")
    public Integer subtract(@RequestParam int a, @RequestParam int b){
        return a-b;
    }
}