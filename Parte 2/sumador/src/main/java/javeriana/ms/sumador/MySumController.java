package main.java.javeriana.ms.sumador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySumController {
    @Autowired
    Environment environment;

    @GetMapping("/suma")
    public Integer sum(@RequestParam int a, @RequestParam int b){
        return a+b;
    }
}
