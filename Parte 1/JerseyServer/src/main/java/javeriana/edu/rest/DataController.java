package javeriana.edu.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataController {

    private static final String FILE_ROUTE = "src/main/java/javeriana/edu/rest/tripdatabase.json";
    
    public List<Trip> readJSON(){
        List<Trip> trips = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(FILE_ROUTE))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray tripList = (JSONArray) obj;
            for (Object trip : tripList) {
                JSONObject jsonTrip = (JSONObject)trip;
                trips.add(
                    new Trip(jsonTrip)
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public void writeJSON(List<Trip> newInfo){
        JSONArray information = new JSONArray();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Trip trip : newInfo) {
            HashMap<String,String> tripDetails = new HashMap<>();
            tripDetails.put("id", trip.getId().toString());
            tripDetails.put("nombre", trip.getName());
            tripDetails.put("lugarSalida", trip.getDeparture());
            tripDetails.put("lugarLlegada", trip.getArrival());
            tripDetails.put("fecha", dateFormat.format(trip.getDate()));
            information.add(new JSONObject(tripDetails));
        }
        try (FileWriter file = new FileWriter(FILE_ROUTE)) {
            file.write(information.toJSONString()); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
