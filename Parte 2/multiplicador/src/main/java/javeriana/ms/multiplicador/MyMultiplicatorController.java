package main.java.javeriana.ms.multiplicador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyMultiplicatorController {
    @Autowired
    Environment environment;

    @GetMapping("/multiplicar")
    public Integer multiply(@RequestParam int a, @RequestParam int b){
        return a*b;
    }
}
