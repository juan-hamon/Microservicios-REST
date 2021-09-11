package javeriana.ms.calculadora;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class CalculadoraController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/calculadora/suma")
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String name){
        Integer response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}&name={name}", Integer.class, a, b, name);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/suma/files")
    public JSONArray calculadoraHistorialSuma(){
        JSONArray response = restTemplate.getForObject("http://sumador/suma/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/resta")
    public String calculadoraResta(@RequestParam int a, @RequestParam int b, @RequestParam String name){
        Integer response = restTemplate.getForObject("http://sustractor/resta?a={a}&b={b}&name={name}", Integer.class, a, b, name);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/resta/files")
    public JSONArray calculadoraHistorialResta(){
        JSONArray response = restTemplate.getForObject("http://sustractor/resta/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/multiplicar")
    public String calculadoraMultiplicar(@RequestParam int a, @RequestParam int b, @RequestParam String name){
        Integer response = restTemplate.getForObject("http://multiplicador/multiplicar?a={a}&b={b}&name={name}", Integer.class, a, b, name);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/multiplicar/files")
    public JSONArray calculadoraHistorialMultiplicar(){
        JSONArray response = restTemplate.getForObject("http://multiplicador/multiplicar/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/division")
    public String calculadoraDividir(@RequestParam int a, @RequestParam int b, @RequestParam String name){
        try{
            ResponseEntity<Integer> response = restTemplate.getForObject("http://divisor/division?a={a}&b={b}&name={name}", ResponseEntity.class, a, b, name);
            return "Resultado es: "+response.getBody();
        }
        catch( HttpClientErrorException e){
            return "Operacion invalida";
        }
    }

    @GetMapping("/calculadora/division/files")
    public JSONArray calculadoraHistorialDividir(){
        JSONArray response = restTemplate.getForObject("http://divisor/division/files", JSONArray.class);
        return response;
    }
}
