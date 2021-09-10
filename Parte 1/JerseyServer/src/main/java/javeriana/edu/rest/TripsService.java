package javeriana.edu.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("Trips")
public class TripsService {

    private DataController controller = new DataController();

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Trip> getTrips(){
        return controller.readJSON();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTrip(@PathParam(value = "id") String id){
        Boolean finded = false;
        List<Trip> trips = controller.readJSON();
        Integer intId = Integer.parseInt(id);
        for (Trip trip : trips) {
            if(trip.getId() == intId){
                trips.remove(trip);
                finded = true;
                break;
            }
        }
        controller.writeJSON(trips);
        if(finded)
            return Response.status(200).build();
        else
            return Response.status(400).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateTrip(String nTripInfo, @PathParam(value = "id") String id){
        Boolean replaced = false;
        JSONParser jsonParser = new JSONParser();
        JSONObject tripJSON = new JSONObject();
        Integer intId = Integer.parseInt(id);
        try {
            tripJSON = (JSONObject) jsonParser.parse(nTripInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Trip tripInfo = new Trip(tripJSON);
        tripInfo.setId(intId);
        ArrayList<Trip> trips = (ArrayList<Trip>)controller.readJSON();
        int index = 0;
        for (Trip trip : trips) {
            if(trip.getId() == intId){
                trips.set(index, tripInfo);
                replaced = true;
                break;
            }
            index++;
        }
        controller.writeJSON(trips);
        if (replaced)
            return Response.status(200).build();
        else
            return Response.status(400).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTrip(String tripInfo){
        JSONParser jsonParser = new JSONParser();
        JSONObject tripJSON = new JSONObject();
        try {
            tripJSON = (JSONObject) jsonParser.parse(tripInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Trip trip = new Trip(tripJSON);
        List<Trip> trips = controller.readJSON();
        if(trips.size() == 0)
            trip.setId(0);
        else
            trip.setId(trips.get(trips.size()-1).getId()+1);
        trips.add(trip);
        controller.writeJSON(trips);
        return Response.status(201).build();
    }
}
