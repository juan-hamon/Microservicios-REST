package main.java.javeriana.ms.divisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyDivisorController {
    @Autowired
    Environment environment;

    @GetMapping("/division")
    public Integer divide(@RequestParam int a, @RequestParam int b){
        return a/b;
    }
}
