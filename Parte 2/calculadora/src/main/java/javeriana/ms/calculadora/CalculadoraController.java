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
    public String calculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        Integer response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}&user={user}", Integer.class, a, b, user);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/suma/files")
    public JSONArray calculadoraHistorialSuma(){
        JSONArray response = restTemplate.getForObject("http://sumador/suma/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/resta")
    public String calculadoraResta(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        Integer response = restTemplate.getForObject("http://sustractor/resta?a={a}&b={b}&user={user}", Integer.class, a, b, user);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/resta/files")
    public JSONArray calculadoraHistorialResta(){
        JSONArray response = restTemplate.getForObject("http://sustractor/resta/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/multip")
    public String calculadoraMultiplicar(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        Integer response = restTemplate.getForObject("http://multiplicador/multip?a={a}&b={b}&user={user}", Integer.class, a, b, user);
        return "Resultado es: "+response;
    }

    @GetMapping("/calculadora/multip/files")
    public JSONArray calculadoraHistorialMultiplicar(){
        JSONArray response = restTemplate.getForObject("http://multiplicador/multip/files", JSONArray.class);
        return response;
    }

    @GetMapping("/calculadora/div")
    public String calculadoraDividir(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        try{
            Integer response = restTemplate.getForObject("http://divisor/div?a={a}&b={b}&user={user}", Integer.class, a, b, user);
            return "Resultado es: "+response;
        }
        catch( HttpClientErrorException e){
            return "Operacion invalida";
        }
    }

    @GetMapping("/calculadora/div/files")
    public JSONArray calculadoraHistorialDividir(){
        JSONArray response = restTemplate.getForObject("http://divisor/div/files", JSONArray.class);
        return response;
    }
}
