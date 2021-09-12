package javeriana.ms.divisor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyDivisorController {
    @Autowired
    Environment environment;
    private static final String FILE_ROUTE = "src/JsonFile/database.json";

    public JSONArray readJson()
    {
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

    @GetMapping("/div/files")
    public JSONArray divisionHistory(){
        return readJson();
    }    



    @GetMapping("/div")
    public ResponseEntity<Integer> divide(@RequestParam int a, @RequestParam int b,@RequestParam String user){
        HashMap<String,String> operationDetails = new HashMap<>();
        String port = environment.getProperty("local.server.port");
        operationDetails.put("Operando1", String.valueOf (a));
        operationDetails.put("Operando2", String.valueOf (b));
        if(b!=0)operationDetails.put("Resultado", String.valueOf (a/b)); else operationDetails.put("Resultado", "Error");
        operationDetails.put("Usuario", user);
        operationDetails.put("Instancia", port);
        operationDetails.put("Fecha", LocalDateTime.now().toString());
        JSONArray information = readJson();
        information.add(new JSONObject(operationDetails));
        writeJSON(information);
        if(b == 0) return new ResponseEntity<Integer>(0,null,HttpStatus.SC_BAD_REQUEST);
        return new ResponseEntity<Integer>(a/b,null,HttpStatus.SC_OK);
    }
}
