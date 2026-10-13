
public class Route {

    private int length, points, locomotives;
    private String color, boughtColor;
    private boolean isTunnel, doubleRoute, hasStation;
    private City city1, city2;

    public Route(City c1, City c2, int l, String c, boolean t, int lo) {
        // destination = d;
        hasStation = false;
        city1 = c1;
        city2 = c2;
        length = l;
        color = c;
        isTunnel = t;
        locomotives = lo;
        boughtColor = null;
        //doubleRoute = d;
        switch (length) {
            case 1:
                points = 1;
                break;
            case 2:
                points = 2;
                break;
            case 3:
                points = 4;
                break;
            case 4:
                points = 7;
                break;
            case 6:
                points = 15;
                break;
            case 8:
                points = 21;
        }
    }

    
    public boolean isDouble() {
    	return doubleRoute;
    }

    // public City getDestination(){
    //     return destination;
    // }

    public City getCity1(){
        return city1;
    }

    public City getCity2(){
        return city2;
    }

    public int getLength(){
        return length;
    }
    
    public int getPoints(){
        return points;
    }

    public int getLocomotives(){
        return locomotives;
    }

    public String color(){
        return color;
    }

    public String boughtColor(){
        return boughtColor;
    }
    
    public void setStation(String c){
        //color = c;
        hasStation = true;
    }

    public void buyRoute(String c){
        boughtColor = c;
    }

    public boolean isTunnel(){
        return isTunnel;
    }

    public boolean hasStation(){
        return hasStation;
    }

    public String toString(){
        return "Connects " +  city1.name() + " to " + city2.name() + " " + color + " " + length + " " + isTunnel + " " + locomotives + " bought color " + boughtColor;
    }

}
