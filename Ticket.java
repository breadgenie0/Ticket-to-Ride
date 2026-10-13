
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ticket{
    private City city1, city2;
    private int points;
    private boolean completed, discarded;
    private BufferedImage image;

    public Ticket(City city1, City city2, int p, String img){
    	try {
    		String filename = "/Images/" + img + ".png";
    		image = ImageIO.read(Ticket.class.getResource(filename));
    	}catch(Exception e) {
    		System.out.println("ticket image error");
    	}
        this.city1 = city1;
        this.city2 = city2;
        points = p;
        completed = false;
        discarded = false;
    }


    public void discard() {
    	discarded = !discarded;
    }
    
    public boolean isDiscarded() {
    	return discarded;
    }

    public BufferedImage getImage() {
    	return image;
    }

    public int getPoints(){
        return points;
    }

    public City city1(){
        return city1;
    }

    public City city2(){
        return city2;
    }

    public String toString() {
        return "Ticket from " + city1 + " to " + city2 + " " + points + " " + completed;
    }
}
