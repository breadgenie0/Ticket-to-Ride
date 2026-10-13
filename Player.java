
import java.util.*;

public class Player{
    private int score, numTrains, numStations;
    private HashMap<String, Integer> cards;
    private ArrayList<Ticket> tickets;
    private String color;

    public Player(String c){
        score = 0;
        numTrains = 45;
        numStations = 3;
        tickets = new ArrayList<>();

        cards = Helper.getEmptyDeck();
        // for(String s : cards.keySet()) {
        //	cards.put(s, cards.get(s) + 3);
        // }
        color = c;
    }

    public void setScore(int x) {
    	score = x;
    }
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    public void addPoints(int x){
        score += x;
    }

    public void drawTicket(Ticket t){
        tickets.add(t);
    }

    public void addCard(String c){
        cards.put(c, cards.get(c) + 1);
    }

    public void removeCard(String c){
        if(cards.get(c) > 0){
            cards.put(c, cards.get(c) - 1);
        }
        else{
            System.out.println("You don't have any " + c + " cards left.");
        }
    }

    public void useStation(){
        numStations--;
    }

    public void addTicket(Ticket t){
        tickets.add(t);
    }

    public String getColor(){
        return color;
    }

    public int getScore(){
        return score;
    }

    public int getStations(){
        return numStations;
    }

    public void useTrains(int k) {
    	numTrains -=k;
    }

    public int numTrains(){
        return numTrains;
    }

    public HashMap<String, Integer> getCards(){
    	return cards;
    }
    
    public int getCards(String c) {
    	return cards.get(c);
    }
    
    public void useCards(String c, int x) {
    	cards.put(c, cards.get(c) - x);
    }
    public String toString(){
        // StringBuilder sb = new StringBuilder();
        // sb.append("Player Color: " + color + "\n");
        // sb.append("Score: " + score + "\n");
        // sb.append("Number of Trains: " + numTrains + "\n");
        // sb.append("Number of Stations: " + numStations + "\n");
        // sb.append("Cards: \n");

        // for(Map.Entry<String, Integer> entry : cards.entrySet()){
        //     sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        // }

        
        // return sb.toString();

        return color + " player " + numTrains + " trains " + numStations + " stations " + tickets + " " + cardtostring();
    }

    public String cardtostring(){
        String x = "";
        for(Map.Entry<String, Integer> entry : cards.entrySet()){
                x += entry.getKey() + ": " + entry.getValue() + "   ";
        }
        return x;
    }
}
