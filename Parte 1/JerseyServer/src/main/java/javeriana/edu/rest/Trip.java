package javeriana.edu.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.json.simple.JSONObject;


@XmlRootElement(name = "Trip")
public class Trip {
    
    private Integer id;
    private String name;
    private String departure;
    private String arrival;
    private Date date;

    public Trip(){}

    public Trip(Integer nId, String nName, String nDeparture, String nArrival, Date nDate){
        this.id = nId;
        this.name = nName;
        this.departure = nDeparture;
        this.arrival = nArrival;
        this.date = nDate;
    }

    public Trip(JSONObject jsonTrip){
        Date tripDate = new Date();
        try {
            tripDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jsonTrip.get("fecha"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if(jsonTrip.get("id") != null)
            this.id = Integer.parseInt((String) jsonTrip.get("id"));
        else
            this.id = 0;
        this.name = (String) jsonTrip.get("nombre");
        this.departure = (String) jsonTrip.get("lugarSalida");
        this.arrival = (String) jsonTrip.get("lugarLlegada");
        this.date = tripDate;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDeparture(String departure){
        this.departure = departure;
    }

    public String getDeparture(){
        return this.departure;
    }

    public void setArrival(String arrival){
        this.arrival = arrival;
    }

    public String getArrival(){
        return this.arrival;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    @Override
    public String toString(){
        return "Trip [id = "+this.id+"] [name = "+this.name+"] [departure = "+this.departure+"] [arrival = "+this.arrival+"] [date = "+this.date.toString()+"]";
    }
}
