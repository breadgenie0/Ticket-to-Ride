
public class City {
    private String name;
    private String station;
    
    public City(String n){
        name = n;
        station = null;
    }

    public String name(){
        return name;
    }
    
    public String toString(){
        return name;
    }
    
    public boolean hasStation() {
    	return station != null;
    }

    public void addStation(String s){
        if(station == null) {
            station = s;
        }
    }

  
    public String getStation(){
        return station;
    }
}
