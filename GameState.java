
import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class GameState {

	public static Player[] playerArr;
	private int cardCounter, playerTurn, endgame, extra;
	private HashMap<String, Integer> deck, discard;
	private String[] cards;
	private ArrayList<Ticket> ticketPile, longRoutes;
	private ArrayList<City> cities;
	private HashMap<City, ArrayList<Route>> board;
	private City currentCity;
	private boolean turnover, drawingstartingtickets;
	public String status, buyingwithcolor, purchasecity1, purchasecity2, discardTop;
	public ArrayList<String> payingwithcards, extracards;
	public ArrayList<Route> routes;
	public Route purchaseRoute;
	public String[] longestroute;

	public GameState() {
		try {
			cities = readCities();
			createMap(cities);
			createTickets();
			System.out.println("success");
		} catch (Exception e) {
			System.out.println("error");
		}
		createDecks();
		printDeck();
		cards = new String[5];
		for (int i = 0; i < 5; i++) {
			cards[i] = drawCard();
		}
		System.out.println(Arrays.toString(cards));
		cardCounter = 0;
		playerTurn = 0;
		playerArr = new Player[4];
		playerArr[0] = new Player("yellow");
		playerArr[1] = new Player("blue");
		playerArr[2] = new Player("red");
		playerArr[3] = new Player("green");
		endgame = 0;
		turnover = false;
		longestroute = new String[2];
		longestroute[0] = "0";
		longestroute[1] = "none";
		// drawingstartingtickets = true;
		status = "drawing starting tickets";
		currentCity = null;
		buyingwithcolor = "";
		payingwithcards = new ArrayList<>();
		purchasecity1 = "";
		purchasecity2 = "";
		purchaseRoute = null;
		/*for(int i = 0; i < 4; i++) {
			playerArr[0].addCard("blue");
		}
		for(int i = 0; i < 4; i++) {
			playerArr[0].addCard("red");
		}
		for(int i = 0; i < 4; i++) {
			playerArr[0].addCard("black");
		}
		*/
		// drawStartingTickets();
		printPlayers();

	}

	public int playerturn() {
		return playerTurn;
	}

	public void printPlayers() {
		for (Player p : playerArr) {
			System.out.println(p);
		}
	}
	
	public void cancel() {
		purchasecity1 = "";
		purchasecity2 = "";
		buyingwithcolor = "";
		status = "";
	}

	/*
	 * public static void main(String[] args) { System.out.println("hello");
	 * GameState g = new GameState(); //g.test(); }
	 */
	/*public void test() {
		Scanner sc = new Scanner(System.in);
		/*
		 * for(int i = 0; i < 10; i++){ System.out.println("Player " + (playerTurn +
		 * 1)); System.out.println("draw card"); String choice = sc.nextLine();
		 * System.out.println("//////////////////////////"); tryDrawCard(choice);
		 * for(Player p : playerArr){ System.out.println(p); }
		 * System.out.println(Arrays.toString(cards));
		 * 
		 * }
		 */
		// System.out.println("buy route");
		// String[] cardarr = new String[]{"orange", "orange", "orange"};
		// ArrayList<String> cards = new ArrayList<>(Arrays.asList(cardarr));
		// //System.out.println(cards);
		// System.out.println(cities);
		// System.out.println(buyRoute("Munchen", "Wien", cards));
		// for(Player p : playerArr){
		// System.out.println(p);
		// }
		/*String x = sc.nextLine();
		while (!x.equals("stop")) {
			if (x.equals("buy")) {
				System.out.println("cards");
				String[] cardarr = sc.nextLine().split(" ");
				ArrayList<String> cards = new ArrayList<>(Arrays.asList(cardarr));
				System.out.println("city 1");
				String c1 = sc.nextLine();
				System.out.println("city 2");
				String c2 = sc.nextLine();
				System.out.println(buyRoute(c1, c2, cards));
			}
			if (x.equals("check tickets")) {
				for (Ticket t : playerArr[playerTurn].getTickets()) {
					// System.out.println(checkCompleted(t, playerArr[playerTurn].getColor()));
					System.out.println(checkTicket(playerArr[playerTurn], t.city1(), t.city2()));
				}
			}
			if (x.equals("longest route")) {
				System.out.println(findLongestRoute(playerArr[playerTurn].getColor()));
			}
			x = sc.nextLine();
		}
	}
*/

	public void createMap(ArrayList<City> cities) throws IOException {
		board = new HashMap<>();
		Scanner sc = new Scanner("Lisboa Cadiz 2 blue false 0\r\n" + "Madrid Lisboa 3 purple false 0\r\n"
				+ "Madrid Cadiz 3 orange false 0\r\n" + "Madrid Pamplona 3 black true 0\r\n"
				+ "Madrid Pamplona 3 white true 0\r\n" + "Madrid Barcelona 2 yellow false 0\r\n"
				+ "Marseille Pamplona 4 red false 0\r\n" + "Marseille Barcelona 4 gray false 0\r\n"
				+ "Pamplona Barcelona 2 gray true 0\r\n" + "Pamplona Brest 4 purple false 0\r\n"
				+ "Paris Pamplona 4 green false 0\r\n" + "Paris Pamplona 4 blue false 0\r\n"
				+ "Paris Marseille 4 gray false 0\r\n" + "Paris Brest 3 black false 0\r\n"
				+ "Paris Dieppe 1 purple false 0\r\n" + "Paris Zurich 3 gray true 0\r\n"
				+ "Dieppe London 2 gray false 1\r\n" + "Dieppe London 2 gray false 1\r\n"
				+ "Dieppe Brest 2 orange false 0\r\n" + "London Edinburgh 4 black false 0\r\n"
				+ "London Edinburgh 4 orange false 0\r\n" + "Zurich Marseille 2 gray true 0\r\n"
				+ "Zurich Venezia 2 green true 0\r\n" + "Marseille Roma 4 gray true 0\r\n"
				+ "Venezia Roma 2 black false 0\r\n" + "Zurich Munchen 2 yellow true 0\r\n"
				+ "Munchen Venezia 2 blue true 0\r\n" + "Munchen Frankfurt 2 purple false 0\r\n"
				+ "Frankfurt Paris 3 white false 0\r\n" + "Frankfurt Paris 3 orange false 0\r\n"
				+ "Frankfurt Bruxelles 2 blue false 0\r\n" + "Bruxelles Paris 2 yellow false 0\r\n"
				+ "Bruxelles Paris 2 red false 0\r\n" + "Bruxelles Dieppe 2 green false 0\r\n"
				+ "Bruxelles Amsterdam 1 black false 0\r\n" + "Amsterdam London 2 gray false 2\r\n"
				+ "Amsterdam Frankfurt 2 white false 0\r\n" + "Frankfurt Essen 2 green false 0\r\n"
				+ "Amsterdam Essen 3 yellow false 0\r\n" + "Munchen Wien 3 orange false 0\r\n"
				+ "Berlin Wien 3 green false 0\r\n" + "Berlin Essen 2 blue false 0\r\n"
				+ "Berlin Frankfurt 3 black false 0\r\n" + "Berlin Frankfurt 3 red false 0\r\n"
				+ "Wien Zagrab 2 gray false 0\r\n" + "Zagrab Venezia 2 gray false 0\r\n"
				+ "Roma Brindisi 2 white false 0\r\n" + "Roma Palermo 4 gray false 1\r\n"
				+ "Brindisi Palermo 3 gray false 1\r\n" + "Budapest Zagrab 2 orange false 0\r\n"
				+ "Budapest Wien 1 red false 0\r\n" + "Budapest Wien 1 white false 0\r\n"
				+ "Zagrab Sarajevo 3 red false 0\r\n" + "Sarajevo Athina 4 green false 0\r\n"
				+ "Budapest Sarajevo 3 purple false 0\r\n" + "Brindisi Athina 4 gray false 1\r\n"
				+ "Athina Sofia 3 purple false 0\r\n" + "Sofia Sarajevo 2 gray true 0\r\n"
				+ "Sofia Constantinople 3 blue false 0\r\n" + "Athina Smyrna 2 gray false 1\r\n"
				+ "Smyrna Constantinople 2 gray true 0\r\n" + "Palermo Smyrna 6 gray false 2\r\n"
				+ "Constantinople Bucuresti 3 yellow false 0\r\n" + "Bucuresti Sofia 2 gray true 0\r\n"
				+ "Bucuresti Budapest 4 gray true 0\r\n" + "Budapest Kyiv 6 gray true 0\r\n"
				+ "Bucuresti Kyiv 4 gray false 0\r\n" + "Kyiv Warszawa 4 gray false 0\r\n"
				+ "Warszawa Berlin 4 purple false 0\r\n" + "Warszawa Berlin 4 yellow false 0\r\n"
				+ "Warszawa Wien 4 blue false 0\r\n" + "Constantinople Angora 2 gray true 0\r\n"
				+ "Smyrna Angora 3 orange true 0\r\n" + "Angora Erzurum 3 black false 0\r\n"
				+ "Sevastopol Constantinople 4 gray false 2\r\n" + "Sevastopol Erzurum 4 gray false 2\r\n"
				+ "Sochi Erzurum 3 red true 0\r\n" + "Sevastopol Sochi 2 gray false 1\r\n"
				+ "Sochi Rostov 2 gray false 0\r\n" + "Sevastopol Rostov 4 gray false 0\r\n"
				+ "Rostov Kharkov 2 green false 0\r\n" + "Kharkov Kyiv 4 gray false 0\r\n"
				+ "Kharkov Moskva 4 gray false 0\r\n" + "Sevastopol Bucuresti 4 white false 0\r\n"
				+ "Moskva Smolensk 2 orange false 0\r\n" + "Moskva Petrograd 4 white false 0\r\n"
				+ "Petrograd Wilno 4 blue false 0\r\n" + "Petrograd Riga 4 gray false 0\r\n"
				+ "Petrograd Stockholm 8 gray true 0\r\n" + "Stockholm Kobenhavn 3 yellow false 0\r\n"
				+ "Stockholm Kobenhavn 3 white false 0\r\n" + "Kobenhavn Essen 3 gray false 1\r\n"
				+ "Kobenhavn Essen 3 gray false 1\r\n" + "Riga Danzig 3 black false 0\r\n"
				+ "Danzig Berlin 4 gray false 0\r\n" + "Danzig Warszawa 2 gray false 0\r\n"
				+ "Warszawa Wilno 3 red false 0\r\n" + "Wilno Riga 4 green false 0\r\n"
				+ "Wilno Smolensk 3 yellow false 0\r\n" + "Wilno Kyiv 2 gray false 0\r\n"
				+ "Smolensk Kyiv 3 red false 0");
		routes = new ArrayList<Route>();
		City city1, city2;
		while (sc.hasNextLine()) {
			String[] info = sc.nextLine().split(" ");
			// System.out.println(Arrays.toString(info));
			city1 = searchCity(info[0]);
			// System.out.println(city1);
			city2 = searchCity(info[1]);
			Route r = new Route(city1, city2, Integer.parseInt(info[2]), info[3], Boolean.parseBoolean(info[4]),
					Integer.parseInt(info[5]));
			// r.buyRoute("green");
			routes.add(r);
		}
		System.out.println("done");
		for (Route r : routes) {
			// System.out.println("-");
			// System.out.println(r);
		}

		for (City k : cities) {
			ArrayList<Route> tempRouteList = new ArrayList<>();
			for (Route r : routes) {
				if (r.getCity1().equals(k) || r.getCity2().equals(k)) {
					tempRouteList.add(r);
				}
			}
			board.put(k, tempRouteList);
		}

		for (Entry<City, ArrayList<Route>> tentry : board.entrySet()) {
			System.out.println("City: " + tentry.getKey() + " Routes " + tentry.getValue());
		}

	}

	public void checkRoute(int i) {
		routes.get(i).buyRoute("red");
	}

	public City searchCity(String city) {
		for (City k : cities) {
			if (k.name().equals(city)) {
				return k;
			}
		}
		return null;
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}

	public int getPoints(ArrayList<Ticket> tlist) {
		int p = 0;
		if (tlist.isEmpty()) {
			return p;
		}
		for (Ticket t : tlist) {
			p += t.getPoints();
		}
		return p;
	}

	public void getfinalscores() {
		for (Player p : playerArr) {

		}
	}

	public ArrayList<City> readCities() throws IOException {
		System.out.println("read cities");
		Scanner sc = new Scanner("Cadiz 110 822\r\n" + "Lisboa 25 753\r\n" + "Madrid 112 722\r\n"
				+ "Barcelona 259 737\r\n" + "Pamplona 243 618\r\n" + "Marseille 441 611\r\n" + "Brest 144 396\r\n"
				+ "Dieppe 261 359\r\n" + "Paris 325 416\r\n" + "Bruxelles 372 306\r\n" + "Zurich 477 487\r\n"
				+ "Munchen 563 403\r\n" + "Frankfurt 489 347\r\n" + "Roma 595 648\r\n" + "Venezia 585 530\r\n"
				+ "London 276 240\r\n" + "Edinburgh 189 42\r\n" + "Amsterdam 401 244\r\n" + "Essen 507 255\r\n"
				+ "Kobenhavn 599 118\r\n" + "Stockholm 736 17\r\n" + "Berlin 637 275\r\n" + "Wien 714 427\r\n"
				+ "Zagrab 695 546\r\n" + "Brindisi 708 681\r\n" + "Palermo 644 819\r\n" + "Athina 869 784\r\n"
				+ "Sarajevo 806 625\r\n" + "Budapest 774 460\r\n" + "Warszawa 851 264\r\n" + "Danzig 787 173\r\n"
				+ "Riga 894 56\r\n" + "Petrograd 1115 48\r\n" + "Wilno 996 232\r\n" + "Kyiv 1047 335\r\n"
				+ "Bucuresti 974 553\r\n" + "Sofia 892 634\r\n" + "Smyrna 982 820\r\n" + "Constantinople 1039 715\r\n"
				+ "Angora 1140 784\r\n" + "Erzurum 1246 755\r\n" + "Sevastopol 1153 571\r\n" + "Sochi 1268 590\r\n"
				+ "Rostov 1277 474\r\n" + "Kharkov 1224 403\r\n" + "Moskva 1242 206\r\n" + "Smolensk 1130 238");
		ArrayList<City> cities = new ArrayList<>();
		while (sc.hasNextLine()) {
			String[] info = sc.nextLine().split(" ");
			cities.add(new City(info[0]));
		}
		System.out.println(cities);
		return cities;
	}

	public void createTickets() throws IOException {
		ticketPile = new ArrayList<>();
		longRoutes = new ArrayList<>();
		Scanner sc = new Scanner("Amsterdam Pamplona 7 AmsterdamPamplona\r\n" + "Amsterdam Wilno 12 AmsterdamWilno\r\n"
				+ "Angora Kharkov 10 AngoraKharkov\r\n" + "Athina Angora 5 AthinaAngora\r\n"
				+ "Athina Wilno 11 AthinaWilno\r\n" + "Barcelona Bruxelles 8 BarcelonaBruxelles\r\n"
				+ "Barcelona Munchen 8 BarcelonaMunchen\r\n" + "Berlin Bucuresti 8 BerlinBucuresti\r\n"
				+ "Berlin Moskva 12 BerlinMoskva\r\n" + "Berlin Roma 9 BerlinRoma\r\n"
				+ "Brest Marseille 7 BrestMarseille\r\n" + "Brest Petrograd 20 BrestPetrograd\r\n"
				+ "Brest Venezia 8 StarBrestVeneziaWars\r\n" + "Bruxelles Danzig 9 BruxellesDanzig\r\n"
				+ "Budapest Sofia 5 BudapestSofia\r\n" + "Cadiz Stockholm 21 CadizStockholm\r\n"
				+ "Edinburgh Athina 21 EdiburghAthina\r\n" + "Edinburgh Paris 7 EdinburghParis\r\n"
				+ "Essen Kyiv 10 EssenKyiv\r\n" + "Frankfurt Kobenhavn 5 FrankfurtKobenhavn\r\n"
				+ "Frankfurt Smolensk 13 FrankfurtSmolensk\r\n" + "Kobenhavn Erzurum 21 KobenhavnErzurum\r\n"
				+ "Kyiv Petrograd 6 KyivPetrograd\r\n" + "Kyiv Sochi 8 KyivSochi\r\n"
				+ "Lisboa Danzig 20 LisboaDanzig\r\n" + "London Berlin 7 LondonBerlin\r\n"
				+ "London Wien 10 LondonWien\r\n" + "Madrid Dieppe 8 MadridDieppe\r\n"
				+ "Madrid Zurich 8 MadridZurich\r\n" + "Marseille Essen 8 MarseileEssen\r\n"
				+ "Palermo Constantinople 8 PalermoConstantinople\r\n" + "Palermo Moskva 20 PalermoMoskva\r\n"
				+ "Paris Wien 8 ParisWien\r\n" + "Paris Zagrab 7 ParisZagrab\r\n"
				+ "Riga Bucuresti 10 RigaBucuresti\r\n" + "Roma Smyrna 8 RomaSmyrna\r\n"
				+ "Rostov Erzurum 5 RostovErzurum\r\n" + "Sarajevo Sevastopol 8 SarajevoSevastopol\r\n"
				+ "Smolensk Rostov 8 SmoelsnkRostov\r\n" + "Sofia Smyrna 5 SofiaSmyrna\r\n"
				+ "Stockholm Wien 11 StockholmWien\r\n" + "Venezia Constantinople 10 VeneziaConstantinople\r\n"
				+ "Warszawa Smolensk 6 WarszawaSmolensk\r\n" + "Zagrab Brindisi 6 ZagrabBrindisi\r\n"
				+ "Zurich Brindisi 6 ZurichBrindisi\r\n" + "Zurich Budapest 6 ZurichBudapest");
		while (sc.hasNextLine()) {
			String[] info = sc.nextLine().split(" ");
			City city1 = searchCity(info[0]);
			City city2 = searchCity(info[1]);
			Ticket t = new Ticket(city1, city2, Integer.parseInt(info[2]), info[3]);
			if (t.getPoints() >= 20) {
				longRoutes.add(t);
			} else {
				ticketPile.add(t);
			}
		}
		Collections.shuffle(ticketPile);
		Collections.shuffle(longRoutes);
	}

	public Ticket drawTicket() {
		System.out.println(ticketPile);
		return ticketPile.remove(0);
	}

	public Ticket getLongTicket() {
		return longRoutes.remove(0);
	}

	// end of game setup

	public boolean checkCompleted(Ticket t, String p) {
		System.out.println(t);
		City city1 = t.city1();
		City city2 = t.city2();
		ArrayList<City> visited = new ArrayList<>();
		return checkCompleted(city1, city2, p, visited) || checkCompleted(city2, city1, p, visited);
	}

	public boolean checkCompleted(City c1, City c2, String p, ArrayList<City> visited) {
		ArrayList<Route> routeliest = board.get(c1);
		visited.add(c1);
		for (Route r : routeliest) {
			if (r.boughtColor() != null && r.boughtColor().equals(p)) {
				if (r.getCity2().equals(c2) && !visited.contains(c2)) {
					return true;
				}
				return checkCompleted(r.getCity2(), c2, p, visited);
			}
			return false;
		}
		return false;
	}

	public ArrayList<Ticket> drawStartingTickets() {
		// Scanner sc = new Scanner(System.in);
		ArrayList<Ticket> temp = new ArrayList<>();
		// for(int i = 0; i < 4; i++){
		// temp = new ArrayList<>();
		temp.add(getLongTicket());
		for (int j = 0; j < 3; j++) {
			temp.add(drawTicket());
			// }

			// System.out.println("Drawn tickets " + temp);
			// System.out.println("Select the indexes of up to 2 tickets to discard (type
			// STOP to end)");
			// String x = sc.nextLine();
			/*
			 * while(!x.equals("STOP") && temp.size() >= 4){ int index =
			 * Integer.parseInt(x); temp.remove(index); System.out.println(temp); x =
			 * sc.nextLine(); }
			 */
			// for(Ticket t : temp){
			// playerArr[i].addTicket(t);
			// }
		}

		return temp;

	}

	public void finishdrawingstartingtickets() {
		// drawingstartingtickets = false;
		status = "";
	}

	public void addTicketsToPlayer(ArrayList<Ticket> tlist, int playerint) {
		for (Ticket t : tlist) {
			if (!t.isDiscarded()) {
				playerArr[playerint].addTicket(t);
			}
		}
	}

	public boolean drawingstartingtickets() {
		return drawingstartingtickets;
	}

	public Player currentPlayer() {
		return playerArr[playerTurn];
	}

	public boolean tryDrawCard(String choice) {
		// choice will either be deck or faceup index
		System.out.println("player " + playerArr[playerTurn].toString());
		if (cardCounter >= 2 || status != "") {
			return false;
		}
		if (choice.equals("deck")) {
			String c = drawCard();
			System.out.println(c);
			playerArr[playerTurn].addCard(c);
			cardCounter++;
			if (cardCounter == 2) {
				endTurn();
			}
			return true;
		}
		int index = Integer.parseInt(choice);
		String c = drawCard(index);
		System.out.println(c);
		if (c.equals("loco")) {
			if (cardCounter == 0) {
				replaceCard(index);
				playerArr[playerTurn].addCard(c);
				endTurn();
				return true;
			}
			return false;
		}
		replaceCard(index);
		playerArr[playerTurn].addCard(c);
		cardCounter++;
		if (cardCounter == 2) {
			endTurn();
		}
		return true;
	}

	public boolean turnover() {
		return turnover;
	}

	public void endTurn() {
		turnover = true;

	}

	public City getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String c) {
		currentCity = searchCity(c);
	}

	public void changeStatus(String s) {
		status = s;
	}

	public String getCity1() {
		return purchasecity1;
		
	}
	
	public String getCity2() {
		return purchasecity2;
	}
	
	public ArrayList<Ticket> getTickets(){
		return ticketPile;
	}
	public void changeTurn() {
		System.out.println("change turn");
		currentCity = null;
		status = "";
		buyingwithcolor = "";
		purchasecity1 = "";
		purchasecity2 = "";
		payingwithcards = new ArrayList<>();
		if (playerArr[playerTurn].numTrains() <= 2 || endgame != 0) {
			endgame++;
		}
		if (endgame == 4) {
			endGame();
		}
		playerTurn = (playerTurn + 1) % 4;
		cardCounter = 0;
		turnover = false;
	}

	public void printDeck() {
		for (Entry<String, Integer> e : deck.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}

//setting up game
	public void createDecks() {
		deck = Helper.getEmptyDeck();
		discard = Helper.getEmptyDeck();
        discardTop = null;
		for (String s : Helper.colors) {
			if (s.equals("loco")) {
				deck.put(s, 14);
			} else {
				deck.put(s, 12);
			}
		}
	}

    private void reshuffleDeck() {
        for (String s : Helper.colors) {
			deck.put(s, discard.get(s));
		}
        discard = Helper.getEmptyDeck();
    }

	private String drawCard() {
		int count = 0;
		for (String s : Helper.colors) {
			count += deck.get(s);
		}
        if (count == 0) {
            reshuffleDeck();
            return drawCard();
        }
		int drawn = (int) (count * Math.random());
		for (String s : Helper.colors) {
			drawn -= deck.get(s);
			if (drawn < 0) {
				deck.put(s, deck.get(s) - 1);
				return s;
			}
		}
		return null;
	}

    public void discard(String c, int i) {
        discard.put(c, discard.get(c) + i);
        discardTop = c;
    }

	public String drawCard(int i) {
		String k = cards[i];
		return k;
	}

	public void replaceCard(int i) {
		cards[i] = drawCard();
		int lococounter = 0;
		for (String c : cards) {
			if (c.equals("loco"))
				lococounter++;
		}
		if (lococounter >= 3) {
			for (int j = 0; j < 5; j++) {
                discard(cards[j], 1);
				cards[j] = drawCard();
			}
		}
	}

    public String getDiscardTop() {
        return discardTop;
    }

	/*
	 * buy black Amsterdam Bruxelles buy red red Bruxelles Paris buy blue blue loco
	 * loco Paris Pamplona
	 */
	public String[] getCards() {
		return cards;
	}

	public boolean checkTicket(Player player, Ticket t) {
		City city1 = t.city1();
		City city2 = t.city2();
		Queue<City> queue = new LinkedList<>();
		Set<City> visited = new HashSet<>();
		// ArrayList<Route> routelist = board.get(city1);
		queue.offer(city1);
		visited.add(city1);

		while (!queue.isEmpty()) {
			City currentCity = queue.poll();
			if (currentCity == city2) {
				return true;
			}

			for (Route connection : board.get(currentCity)) {
				// String color = player.getColor();
				System.out.println(connection);
				if (connection.boughtColor() != null) {
					if (connection.boughtColor().equals(player.getColor())) {
						City neighbor = connection.getCity1().equals(currentCity) ? connection.getCity2()
								: connection.getCity1();
						if (!visited.contains(neighbor)) {
							queue.offer(neighbor);
							visited.add(neighbor);
						}
					}
				}
			}
		}

		return false;
	}
	/*
	 * public int longestRoute(Player p) { String color = p.getColor(); int length =
	 * 0; Queue<City> queue = new LinkedList<>(); Set<City> visited = new
	 * HashSet<>(); Set<Route> visitedroutes = new HashSet<>(); City city1 =
	 * searchCity("Lisboa", cities); City city2 = searchCity("Petrograd", cities);
	 * queue.offer(city1); visited.add(city1); ArrayList<Integer> lengthlist = new
	 * ArrayList<>(); ArrayList<Integer> lengths = new ArrayList<>(); int index = 0;
	 * 
	 * while (!queue.isEmpty()) { lengths = new ArrayList<>(); City currentCity =
	 * queue.poll(); System.out.println("current city " + currentCity + " length " +
	 * length); if (currentCity == city2) { return length; }
	 * 
	 * for (Route connection : board.get(currentCity)) {
	 * 
	 * System.out.println(connection); City neighbor =
	 * connection.getCity1().equals(currentCity)?connection.getCity2():connection.
	 * getCity1(); if (!visitedroutes.contains(connection) || connection.isDouble())
	 * { if(connection.boughtColor() != null) {
	 * if(connection.boughtColor().equals(color)) {
	 * lengths.add(connection.getLength()); } }
	 * if(!visitedroutes.contains(connection)) { queue.offer(neighbor);
	 * visitedroutes.add(connection); } } } System.out.println(lengths); int
	 * maxlength = getMax(lengths); length += maxlength; } return length;
	 * 
	 * }
	 */

	public Player[] getplayerarr() {
		return playerArr;
	}

	public int findLongestRoute(String p) {
		int longest = 0;
		for (City city : cities) {
			int length = findLongestRoute(p, city, new ArrayList<Route>(), 0);
			if (length > longest)
				longest = length;
		}
		return longest;
	}

	public int findLongestRoute(String p, City c, ArrayList<Route> visited, int length) {
		int longest = 0;
		for (Route r : board.get(c)) {
			if (r.boughtColor() != null && r.boughtColor().equals(p) && !visited.contains(r)) {
				visited.add(r);
				if (r.getCity1().equals(c)) {
					length = findLongestRoute(p, r.getCity2(), visited, length) + r.getLength();
				} else {
					length = findLongestRoute(p, r.getCity1(), visited, length) + r.getLength();
				}
				if (length > longest) {
					longest = length;
				}
				visited.remove(r);
			}
		}
		return longest;
	}

	public void checkLongest(){
		for(Player p : playerArr) {
			int longest = findLongestRoute(p.getColor());
			if(longest > Integer.parseInt(longestroute[0])) {
				longestroute[0] = "" + longest;
				longestroute[1] = p.getColor();
			}
		}
	}
	public int getMax(ArrayList<Integer> list) {
		int max = 0;
		for (int k : list) {
			if (k > max)
				max = k;
		}
		return max;
	}

	public int longestRoute(City c1) {
		return 0;
	}
//buy routes

	public boolean setcity1(String s) {
		if (purchasecity1 == "") {
			purchasecity1 = s;
			return true;
		}
		return false;
	}

	public void setcity2(String s) {
		purchasecity2 = s;
	}

	public void setPurchaseColor(String c) {
		 if (c.equals(buyingwithcolor)) {
             buyingwithcolor = "";
         } else {
             buyingwithcolor = c;
         }
	}
	
	public String getPurchaseColor() {
		return buyingwithcolor;
	}

	public String buyRoute() {
		// if(cards.isEmpty()){
		// return false;
		// }
		// cards = playerArr[playerturn].getCards().get(buyingwithcolor);
		City city1 = searchCity(purchasecity1);
		City city2 = searchCity(purchasecity2);
		ArrayList<Route> routeslist = board.get(city1);
		System.out.println(routeslist);
		String cardcolor = buyingwithcolor;
		// for(String s:cards){
		// System.out.println(s);
		// if(!s.equals("loco")){
		// cardcolor = s;
		// }
		// }
		if (cardcolor.equals(""))
			cardcolor = "loco";
		System.out.println("color " + cardcolor);
		purchaseRoute = null;
		for (Route r : routeslist) {
			if(r.boughtColor() != null && r.boughtColor().equals(playerArr[playerTurn].getColor()) && ((r.getCity1().equals(city1) && r.getCity2().equals(city2))
					|| (r.getCity2().equals(city1) && r.getCity1().equals(city2)))){
					return "double route purchase not allowed";
			}
		}
		for (Route r : routeslist) {
			if (((r.getCity1().equals(city1) && r.getCity2().equals(city2))
					|| (r.getCity2().equals(city1) && r.getCity1().equals(city2)))
					&& (r.color().equals("gray") || r.color().equals(cardcolor) || cardcolor.equals("loco"))) {
				purchaseRoute = r;
			}
		}
		if (purchaseRoute == null) {
			System.out.println("no route found");
			return "No route found";
		}
		System.out.println(purchaseRoute);

		int locomotives = purchaseRoute.getLocomotives();
		int locomotivecards = playerArr[playerTurn].getCards("loco");
		int availablecards = 0;
		if(!cardcolor.equals("loco")) {
			availablecards = playerArr[playerTurn].getCards(cardcolor) + playerArr[playerTurn].getCards("loco");
		}else {
			availablecards = locomotivecards;
		}
		
		if (availablecards < purchaseRoute.getLength()) {
			return "Not enough cards";
		}
		int locomotivesused = purchaseRoute.getLength() - playerArr[playerTurn].getCards(cardcolor);
		if (!purchaseRoute.isTunnel() && locomotives == 0) {
			if(locomotivesused <= 0) {
				playerArr[playerTurn].useCards(cardcolor, purchaseRoute.getLength());
                discard(cardcolor, purchaseRoute.getLength());
			}else {
				playerArr[playerTurn].useCards("loco", locomotivesused);
                discard("loco", locomotivesused);
				playerArr[playerTurn].useCards(cardcolor, purchaseRoute.getLength() - locomotivesused);
                discard(cardcolor, purchaseRoute.getLength() - locomotivesused);
			}
			purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
			playerArr[playerTurn].useTrains(purchaseRoute.getLength());
			playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
			endTurn();
			return "success";
		}
		if (locomotives != 0) {
			if (locomotivecards >= locomotives) {
				if(locomotives > locomotivesused) {
					locomotivesused = locomotives;
				}
				playerArr[playerTurn].useCards("loco", locomotivesused);
                discard("loco", locomotivesused);
				playerArr[playerTurn].useCards(cardcolor, purchaseRoute.getLength() - locomotivesused);
                discard(cardcolor, purchaseRoute.getLength() - locomotivesused);
				purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
				playerArr[playerTurn].useTrains(purchaseRoute.getLength());
				playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
				endTurn();
				return "success: ferry";
			}
			return "not enough locomotives";
		}
		status = "buy tunnel";
		extracards = new ArrayList<>();
		extra = 0;
		for (int i = 0; i < 3; i++) {
			String c = drawCard();
			extracards.add(c);
			if (c.equals(buyingwithcolor) || c.equals("loco")) {
				extra++;
			}
		}
		if(extra == 0) {
			if(locomotivesused <= 0) {
				playerArr[playerTurn].useCards(cardcolor, purchaseRoute.getLength());
                discard(cardcolor, purchaseRoute.getLength());
			}else {
				playerArr[playerTurn].useCards("loco", locomotivesused);
                discard("loco", locomotivesused);
				playerArr[playerTurn].useCards(cardcolor, purchaseRoute.getLength() - locomotivesused);
                discard("loco", purchaseRoute.getLength() - locomotivesused);
			}
			purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
			playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
			playerArr[playerTurn].useTrains(purchaseRoute.getLength());
			status = "bought tunnel";
			return("bought tunnel");
		}
		return "buy tunnel: drawing extra cards";

	}

	public boolean payExtraCards(String c) {
		int locomotivesused = purchaseRoute.getLength() - playerArr[playerTurn].getCards(buyingwithcolor);
		if(locomotivesused < 0) {
			locomotivesused =0;
		}
		int usedcards;
		if(locomotivesused > 0 && !buyingwithcolor.equals("loco")) {
			usedcards = purchaseRoute.getLength() - locomotivesused;
		}else {
			usedcards = purchaseRoute.getLength();
		}
		if(c.equals(buyingwithcolor)){
			if(extra <= playerArr[playerTurn].getCards(c) - usedcards) {
				//playerArr[playerTurn].useCards("loco", locomotivesused);
				playerArr[playerTurn].useCards(c, purchaseRoute.getLength()+ extra);
                discard(c, purchaseRoute.getLength() + extra);
				purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
				playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
				//endTurn();
				playerArr[playerTurn].useTrains(purchaseRoute.getLength());
				extra = 0;
				return true;
			}else {
				return false;
			}
		}
		if(c.equals("loco")) {
			if(extra <= playerArr[playerTurn].getCards("loco") - locomotivesused) {
				playerArr[playerTurn].useCards("loco", locomotivesused + extra);
                discard("loco", locomotivesused + extra);
				playerArr[playerTurn].useCards(buyingwithcolor, purchaseRoute.getLength() - locomotivesused);
                discard(buyingwithcolor, purchaseRoute.getLength() - locomotivesused);
				purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
				playerArr[playerTurn].addPoints(purchaseRoute.getPoints());
				playerArr[playerTurn].useTrains(purchaseRoute.getLength());
				extra = 0;
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	public int getExtra() {
		return extra;
	}
	public ArrayList<String> getExtraCards(){
		return extracards;
	}
	public int tryBuyTunnel() {
		
		
		return extra;
		//if (extra == 0) {
			//purchaseRoute.buyRoute(playerArr[playerTurn].getColor());
			//endTurn();
			//return "succces: tunnel";
		//}
		// ask for extra cards
		

	}
	
	public int cardcounter() {
		return cardCounter;
	}

	public boolean placeStation(String s) {
		City c = searchCity(s);
		Route owned = null;
		for(Route r : board.get(c)) {
			if(r.boughtColor() != null && r.boughtColor().equals(playerArr[playerTurn].getColor())) {
				owned = r;
			}
		}
		if (!c.hasStation() && owned != null) {
			return true;
		}
		return false;
	}

	public int getStationPrice() {
		int price = 4 - playerArr[playerTurn].getStations();
		return price;
	}

	public String getColor(ArrayList<String> cardlist) {
		for (String c : cardlist) {
			if (!c.equals("loco")) {
				return c;
			}
		}
		return "loco";
	}

	public boolean buyStation(String color, City c) {
		if (!payingwithcards.isEmpty() && !color.equals("loco")) {
			if (!(color.equals(getColor(payingwithcards)) || getColor(payingwithcards).equals("loco"))) {
				return false;
			}
		}
		payingwithcards.add(color);
		playerArr[playerTurn].removeCard(color);
		if (payingwithcards.size() >= getStationPrice()) {
			playerArr[playerTurn].useStation();
			c.addStation(playerArr[playerTurn].getColor());
			endTurn();
			return true;
		}
		return false;
	}

	public ArrayList<Ticket> drawTickets() {
		ArrayList<Ticket> tlist = new ArrayList<>();
		if(ticketPile.size() < 3) {
			for (int i = 0; i < ticketPile.size(); i++) {
				tlist.add(drawTicket());
			}
		}else {
			for (int i = 0; i < 3; i++) {
				tlist.add(drawTicket());
			}
		}
		
		return tlist;

	}

//end game
	public void endGame() {
		int s = 0;
		status = "game over";
		// for(Player p : playerArr){
		// s += checkStations(p);
		// s += checkCompleted(p);
		// p.addPoints(s);
		// }
		// check european express
		// switch to end screen
		checkLongest();
		for(Player p : playerArr) {
			ArrayList<Ticket> completedtickets = new ArrayList<>();
			ArrayList<Ticket> incompletedtickets = new ArrayList<>();
			for (Ticket t : p.getTickets()) {
				if (checkTicket(p, t)) {
					completedtickets.add(t);
				} else {
					incompletedtickets.add(t);
				}
			}
			int completed = getPoints(completedtickets);
			int incompleted = getPoints(incompletedtickets);
			if(longestroute[1].equals(p.getColor())) {
				p.addPoints(10);
			}
			int finalscore = completed - incompleted + 4 * p.getStations() + p.getScore();
			p.setScore(finalscore);
		}
	}

	public int checkStations(Player p) {
		return 4 * p.getStations();
	}

	public int checkCompleted(Player p) {
		return 0;
	}

}
