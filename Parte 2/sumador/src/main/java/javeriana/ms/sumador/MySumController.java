package javeriana.ms.sumador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySumController {
    
    @Autowired
    Environment environment;

    private static final String FILE_ROUTE = "src/JsonFile/database.json";

    public JSONArray readJson(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_ROUTE))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray information = (JSONArray) obj;
            return information;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            return new JSONArray();
        }
        return new JSONArray();
    }

    public void writeJSON(JSONArray newInfo){
        try (FileWriter file = new FileWriter(FILE_ROUTE)) {
            file.write(newInfo.toJSONString()); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/suma/files")
    public JSONArray sumHistory(){
        return readJson();
    }

    @GetMapping("/suma")
    public Integer sum(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        Integer result = a+b;
        String port = environment.getProperty("local.server.port");
        HashMap<String,String> operationDetails = new HashMap<>();
        operationDetails.put("Operando1", String.valueOf(a));
        operationDetails.put("Operando2", String.valueOf(b));
        operationDetails.put("Resultado", String.valueOf(result));
        operationDetails.put("Usuario", user);
        operationDetails.put("Instancia", port);
        operationDetails.put("Fecha", LocalDate.now().toString());
        JSONArray values = readJson();
        values.add(operationDetails);
        writeJSON(values);
        return result;
    }
}
