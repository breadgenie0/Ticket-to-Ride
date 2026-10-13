
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel extends Panel implements MouseListener {
	private BufferedImage map, nextbutton, box, bigbox, donebutton, next2button, circle, usestation, next3btn,
			buyroutebtn, cancel;
	private Map<String, BufferedImage> cards, trains, stations;
	private BufferedImage[] tickets;
	private ArrayList<Integer> citiesX, citiesY;
	private ArrayList<String> cities;
	private HashMap<Integer, ArrayList<Ticket>> startingtickets;
	private ArrayList<Ticket> drawntickets;
	private String prompt;
	private int ticketplayercounter, ticketindex, playerscoringcounter, completedtindex, incompletedtindex,
			routecounter;
	private GameState game;
	private Scanner sc;
	private ArrayList<String> coordlist;

	public GamePanel(Frame f) {
		super(f);
		addMouseListener(this);
		game = new GameState();
		drawstartingstuff();
	}

	public void drawstartingstuff() {
		startingtickets = new HashMap<>();
		ticketplayercounter = 0;
		playerscoringcounter = 0;
		ticketindex = 0;
		completedtindex = 0;
		incompletedtindex = 0;
		routecounter = -1;
		drawntickets = new ArrayList<>();
		coordlist = new ArrayList<>();
		prompt = "Please select up to 2 tickets to discard";
		for (int i = 0; i < 4; i++) {
			ArrayList<Ticket> temp = game.drawStartingTickets();
			startingtickets.put(i, temp);

		}
		try {
			Scanner sc = new Scanner("22 760 70\r\n" + "53 800 13\r\n" + "15 675 0\r\n" + "13 698 90\r\n"
					+ "61 682 30\r\n" + "118 724 40\r\n" + "154 762 70\r\n" + "122 797 -20\r\n" + "109 669 -49\r\n"
					+ "140 632 -49\r\n" + "176 604 -34\r\n" + "122 681 -51\r\n" + "154 646 -45\r\n" + "192 618 -34\r\n"
					+ "149 721 1\r\n" + "197 722 1\r\n" + "257 611 21\r\n" + "291 579 -72\r\n" + "323 565 1\r\n"
					+ "372 569 27\r\n" + "264 684 -52\r\n" + "296 651 -45\r\n" + "333 625 -31\r\n" + "378 607 -21\r\n"
					+ "237 631 85\r\n" + "240 679 85\r\n" + "152 404 22\r\n" + "196 428 53\r\n" + "219 475 78\r\n"
					+ "225 524 90\r\n" + "243 565 -55\r\n" + "271 522 -65\r\n" + "291 474 -75\r\n" + "303 426 -83\r\n"
					+ "224 556 -55\r\n" + "252 514 -67\r\n" + "270 468 -74\r\n" + "284 422 -82\r\n" + "324 444 89\r\n"
					+ "328 489 55\r\n" + "363 526 31\r\n" + "404 554 55\r\n" + "156 389 5\r\n" + "203 392 5\r\n"
					+ "252 398 5\r\n" + "268 360 47\r\n" + "343 434 56\r\n" + "373 472 24\r\n" + "420 490 -3\r\n"
					+ "241 297 -84\r\n" + "247 250 -84\r\n" + "258 298 -84\r\n" + "263 254 -84\r\n" + "149 351 -31\r\n"
					+ "197 344 -1\r\n" + "178 57 64\r\n" + "197 102 64\r\n" + "215 145 64\r\n" + "235 188 64\r\n"
					+ "196 51 64\r\n" + "215 96 64\r\n" + "233 139 64\r\n" + "251 182 64\r\n" + "459 502 -76\r\n"
					+ "446 547 -76\r\n" + "484 489 29\r\n" + "530 507 29\r\n" + "453 579 -34\r\n" + "490 550 -34\r\n"
					+ "527 563 57\r\n" + "553 600 57\r\n" + "578 542 78\r\n" + "585 591 78\r\n" + "480 446 -43\r\n"
					+ "518 411 -43\r\n" + "554 429 77\r\n" + "564 475 77\r\n" + "487 357 77\r\n" + "501 394 -17\r\n"
					+ "345 392 -16\r\n" + "390 369 -31\r\n" + "429 341 -35\r\n" + "353 409 -17\r\n" + "399 385 -32\r\n"
					+ "438 356 -36\r\n" + "381 295 -12\r\n" + "428 299 42\r\n" + "313 352 -61\r\n" + "335 313 -61\r\n"
					+ "329 361 -61\r\n" + "351 318 -61\r\n" + "271 321 -35\r\n" + "314 295 -34\r\n" + "369 250 -66\r\n"
					+ "287 231 1\r\n" + "334 231 1\r\n" + "407 252 45\r\n" + "440 287 45\r\n" + "487 297 -25\r\n"
					+ "516 262 63\r\n" + "392 186 -78\r\n" + "415 191 13\r\n" + "461 208 54\r\n" + "569 408 55\r\n"
					+ "608 447 2\r\n" + "655 419 -40\r\n" + "632 288 73\r\n" + "644 335 60\r\n" + "672 372 52\r\n"
					+ "531 240 6\r\n" + "580 248 6\r\n" + "493 313 -26\r\n" + "536 293 -26\r\n" + "579 270 -26\r\n"
					+ "500 328 -26\r\n" + "543 308 -26\r\n" + "586 286 -26\r\n" + "691 437 -86\r\n" + "687 485 -86\r\n"
					+ "595 508 -12\r\n" + "641 509 32\r\n" + "614 625 -7\r\n" + "661 632 47\r\n" + "601 645 29\r\n"
					+ "641 673 57\r\n" + "666 723 90\r\n" + "641 767 -55\r\n" + "697 693 66\r\n" + "686 743 -46\r\n"
					+ "653 778 -49\r\n" + "736 466 -58\r\n" + "702 504 -44\r\n" + "721 423 32\r\n" + "711 438 32\r\n"
					+ "686 559 82\r\n" + "700 602 35\r\n" + "746 615 -18\r\n" + "794 635 93\r\n" + "792 682 93\r\n"
					+ "789 731 95\r\n" + "808 763 -1\r\n" + "773 470 82\r\n" + "781 520 82\r\n" + "786 567 82\r\n"
					+ "715 686 67\r\n" + "733 731 71\r\n" + "762 773 20\r\n" + "814 783 -8\r\n" + "847 636 -48\r\n"
					+ "838 679 -84\r\n" + "838 728 58\r\n" + "816 590 -27\r\n" + "858 583 66\r\n" + "897 634 29\r\n"
					+ "940 658 29\r\n" + "981 682 29\r\n" + "880 774 0\r\n" + "926 776 24\r\n" + "977 763 -62\r\n"
					+ "1001 720 -65\r\n" + "682 809 0\r\n" + "728 809 0\r\n" + "778 811 0\r\n" + "825 811 0\r\n"
					+ "874 811 0\r\n" + "921 811 0\r\n" + "964 561 67\r\n" + "984 606 68\r\n" + "1002 652 66\r\n"
					+ "901 604 -18\r\n" + "946 561 -84\r\n" + "786 456 27\r\n" + "826 474 27\r\n" + "870 498 27\r\n"
					+ "910 523 27\r\n" + "774 407 -48\r\n" + "807 375 -34\r\n" + "848 353 -25\r\n" + "893 337 -20\r\n"
					+ "941 334 -1\r\n" + "989 333 1\r\n" + "960 490 -72\r\n" + "977 444 -72\r\n" + "993 399 -72\r\n"
					+ "1007 354 -72\r\n" + "848 268 46\r\n" + "887 299 24\r\n" + "939 314 0\r\n" + "988 314 0\r\n"
					+ "645 245 -18\r\n" + "693 237 -8\r\n" + "742 237 1\r\n" + "790 239 10\r\n" + "648 265 -18\r\n"
					+ "696 258 -8\r\n" + "745 256 1\r\n" + "790 259 6\r\n" + "716 387 -34\r\n" + "755 357 -43\r\n"
					+ "789 317 -54\r\n" + "817 276 -59\r\n" + "1043 716 34\r\n" + "1084 745 34\r\n" + "996 811 0\r\n"
					+ "1042 807 -5\r\n" + "1090 787 -27\r\n" + "1145 782 30\r\n" + "1196 797 -8\r\n" + "1232 765 78\r\n"
					+ "1124 582 90\r\n" + "1104 629 -54\r\n" + "1065 650 35\r\n" + "1039 663 -52\r\n"
					+ "1141 583 90\r\n" + "1142 630 81\r\n" + "1153 678 56\r\n" + "1187 716 33\r\n" + "1237 697 -80\r\n"
					+ "1244 651 -78\r\n" + "1251 602 -81\r\n" + "1163 564 9\r\n" + "1210 574 9\r\n" + "1263 533 -86\r\n"
					+ "1267 484 -86\r\n" + "1154 513 -78\r\n" + "1161 466 -78\r\n" + "1167 446 9\r\n" + "1214 453 9\r\n"
					+ "1267 416 90\r\n" + "1238 395 -3\r\n" + "1037 346 68\r\n" + "1065 387 29\r\n" + "1115 409 5\r\n"
					+ "1165 399 -19\r\n" + "1224 354 -51\r\n" + "1254 308 -74\r\n" + "1233 214 63\r\n"
					+ "1254 261 81\r\n" + "979 506 -47\r\n" + "1019 491 -11\r\n" + "1066 493 25\r\n" + "1106 520 51\r\n"
					+ "1142 231 3\r\n" + "1189 205 -37\r\n" + "1123 47 18\r\n" + "1166 65 39\r\n" + "1201 101 63\r\n"
					+ "1220 148 85\r\n" + "994 176 -55\r\n" + "1023 137 -55\r\n" + "1049 97 -55\r\n" + "1078 58 -55\r\n"
					+ "908 47 0\r\n" + "955 47 0\r\n" + "1005 47 0\r\n" + "1054 47 0\r\n" + "735 23 53\r\n"
					+ "773 15 -42\r\n" + "817 10 0\r\n" + "867 10 0\r\n" + "914 10 0\r\n" + "963 11 0\r\n"
					+ "1008 10 0\r\n" + "1056 14 24\r\n" + "597 64 -46\r\n" + "632 32 -43\r\n" + "669 8 -28\r\n"
					+ "610 77 -46\r\n" + "642 45 -42\r\n" + "680 21 -27\r\n" + "495 196 -54\r\n" + "525 155 -58\r\n"
					+ "553 115 -57\r\n" + "512 204 -55\r\n" + "539 167 -54\r\n" + "566 127 -56\r\n" + "780 114 -71\r\n"
					+ "798 72 -54\r\n" + "835 52 -20\r\n" + "632 210 -81\r\n" + "643 167 -52\r\n" + "681 150 -12\r\n"
					+ "728 148 13\r\n" + "788 170 30\r\n" + "823 206 77\r\n" + "856 214 -66\r\n" + "889 195 -12\r\n"
					+ "935 199 32\r\n" + "879 67 -81\r\n" + "874 115 63\r\n" + "902 159 34\r\n" + "943 184 34\r\n"
					+ "1009 186 -57\r\n" + "1033 173 36\r\n" + "1072 201 36\r\n" + "999 235 32\r\n" + "1038 274 -86\r\n"
					+ "1060 326 4\r\n" + "1108 298 -46\r\n" + "1120 248 72");
			while (sc.hasNextLine()) {
				coordlist.add(sc.nextLine());
			}
		} catch (Exception e) {
			System.out.println("route coordinates error");
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Font smallfont = new Font("Sans Serif", Font.BOLD, 20);
		g.drawImage(map, 0, 0, 1300, 840, null);
		if (game.status.equals("drawing starting tickets")) {
			g.drawImage(bigbox, 50, 855, 870, 150, null);
			g.setColor(Color.red);
			g.setFont(smallfont);
			String x = game.getplayerarr()[ticketplayercounter].getColor() + " player: " + prompt;
			g.drawString(x, 170, 848);
			ArrayList<Ticket> ticketlist = startingtickets.get(ticketplayercounter);
			for (int i = 0; i < ticketlist.size(); i++) {
				if (!ticketlist.get(i).isDiscarded()) {
					g.drawImage(ticketlist.get(i).getImage(), 60 + (190 * i), 875, 180, 115, null);

				} else {
					g.drawImage(tickets[0], 60 + (190 * i), 875, 180, 115, null);
				}
			}
			g.drawImage(donebutton, 823, 900, 80, 45, null);
		}

		g.drawImage(tickets[0], 1330, 5, 180, 115, null);
        g.drawImage(Helper.rotate(cards.get("back"), 90), 1330, 125, 74, 115, null);
        if (game.getDiscardTop() != null) {
            g.drawImage(Helper.rotate(cards.get(game.getDiscardTop()), 90), 1435, 125, 74, 115, null);
        }
		String[] deck = game.getCards();
		for (int i = 0; i < 5; i++) {
			g.drawImage(cards.get(deck[i]), 1330, 245 + 120 * i, 180, 115, null);
		}
		g.drawImage(buyroutebtn, 30, 270, 120, 41, null);
		g.drawImage(cancel, 30, 320, 120, 41, null);
		// drawing tickets
		if (game.status.equals("drawing tickets") || game.status.equals("showing tickets")) {
			if (game.status.equals("drawing tickets")) {
				drawntickets = game.drawTickets();
				game.changeStatus("showing tickets");
			}

			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(1318, 210, 200, 450);
			for (int i = 0; i < drawntickets.size(); i++) {
				if (!drawntickets.get(i).isDiscarded()) {
					g.drawImage(drawntickets.get(i).getImage(), 1330, 230 + 120 * i, 180, 115, null);

				} else {
					g.drawImage(tickets[0], 1330, 230 + 120 * i, 180, 115, null);
				}
			}
			g.drawImage(donebutton, 1378, 600, 80, 45, null);
		}
		if (game.status.equals("buy tunnel") || game.status.equals("bought tunnel")) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(1318, 210, 200, 550);
			g.setFont(smallfont);
			g.setColor(Color.black);
			g.drawString("Drawn cards: ", 1330, 235);
			ArrayList<String> extra = game.getExtraCards();
			for (int i = 0; i < extra.size(); i++) {
				g.drawImage(cards.get(extra.get(i)), 1330, 250 + 120 * i, 180, 115, null);
			}
			g.drawString("Please pay " + game.getExtra() + " card(s)", 1325, 630);
			g.drawImage(donebutton, 1378, 640, 80, 45, null);
			g.drawImage(cancel, 1358, 695, 120, 41, null);
		}
		// drawing circle over city
		if (game.getCurrentCity() != null && !game.status.equals("drawing starting tickets")) {
			int i = cities.indexOf(game.getCurrentCity().name());
			g.drawImage(circle, citiesX.get(i) - 25, citiesY.get(i) - 25, 50, 50, null);

		}
		if (!game.getCity1().equals("") && game.status.equals("choosing cities")) {
			int i = cities.indexOf(game.getCity1());
			g.drawImage(circle, citiesX.get(i) - 25, citiesY.get(i) - 25, 50, 50, null);

		}
		if (!game.getCity2().equals("") && game.status.equals("choosing cities")) {
			int i = cities.indexOf(game.getCity2());
			g.drawImage(circle, citiesX.get(i) - 25, citiesY.get(i) - 25, 50, 50, null);

		}
		String coords;
		String[] coordarr;
		int counter = 0;
		// System.out.println(game.getRoutes());
		for (Route r : game.getRoutes()) {
			for (int i = 0; i < r.getLength(); i++) {
				coords = coordlist.get(counter);
				coordarr = coords.split(" ");
				paintTrains(g, r.boughtColor(), Integer.parseInt(coordarr[0]), Integer.parseInt(coordarr[1]),
						Integer.parseInt(coordarr[2]));
				counter++;
			}
		}
		if (game.status.equals("use station") || game.status.equals("buy route")
				|| game.status.equals("choosing cities") || game.status.equals("buy tunnel") || prompt.equals("no more tickets")) {
			g.setFont(smallfont);
			g.setColor(Color.red);
			g.drawString(prompt, 240, 30);
		}
		// drawing stations
		for (int i = 0; i < cities.size(); i++) {
			City c = game.searchCity(cities.get(i));
			if (c.hasStation()) {
				g.drawImage(stations.get(c.getStation()), citiesX.get(i) - 16, citiesY.get(i) - 23, 30, 30, null);
			}
		}
		// scoring panel
		if (game.status.equals("game over")) {
			Player currentp = game.getplayerarr()[playerscoringcounter];
			if (currentp.getColor().equals("yellow")) {
				g.setColor(new Color(250,236,112));
			}
			if (currentp.getColor().equals("blue")) {
				g.setColor(new Color(101, 157, 247));
			}
			if (currentp.getColor().equals("green")) {
				g.setColor(new Color(146, 247, 126));
			}
			if (currentp.getColor().equals("red")) {
				g.setColor(new Color(255, 140, 140));
			}

			g.setFont(smallfont);
			
			g.fillRect(1318, 100, 200, 700);
			g.setColor(Color.black);
			g.drawString(currentp.getColor() + " player", 1357, 135);
			g.setColor(Color.red);
			g.drawString("Completed Tickets", 1330, 165);
			ArrayList<Ticket> completedtickets = new ArrayList<>();
			ArrayList<Ticket> incompletedtickets = new ArrayList<>();
			for (Ticket t : currentp.getTickets()) {
				if (game.checkTicket(currentp, t)) {
					completedtickets.add(t);
				} else {
					incompletedtickets.add(t);
				}
			}
			if (!completedtickets.isEmpty()) {
				if (completedtindex >= completedtickets.size()) {
					completedtindex = 0;
				}
				g.drawImage(completedtickets.get(completedtindex).getImage(), 1330, 180, 180, 115, null);
			}
			// g.drawImage(incompletedtickets.get(completedtindex).getImage(), 1330, 175,
			// 180, 115, null);
			g.drawImage(next2button, 1490, 220, 40, 35, null);
			int completed = game.getPoints(completedtickets);
			int incompleted = game.getPoints(incompletedtickets);
			g.drawString("Total points: " + completed, 1330, 310);
			g.drawString("Incompleted Tickets", 1324, 340);
			if (!incompletedtickets.isEmpty()) {
				if (incompletedtindex >= incompletedtickets.size()) {
					incompletedtindex = 0;
				}
				g.drawImage(incompletedtickets.get(incompletedtindex).getImage(), 1330, 350, 180, 115, null);
				g.drawImage(next2button, 1490, 395, 40, 35, null);
			}
			g.drawString("Total points: " + incompleted, 1330, 485);
			g.drawString("Stations left: " + currentp.getStations(), 1330, 520);
			g.drawString("Longest Route: " + game.findLongestRoute(currentp.getColor()), 1330, 555);
			String europeanexpress = game.longestroute[1].equals(currentp.getColor()) ? "yes" : "no";
			g.drawString("European Express? ", 1330, 590);
			g.drawString(europeanexpress, 1330, 625);
			g.drawString("Final Score: ", 1320, 660);
			// g.drawString("" + completed + " - " + incompleted + " + (4 * " +
			// currentp.getStations() + ") + "
			// + currentp.getScore() + " =", 1330, 650);
			g.drawString("" + currentp.getScore(), 1335, 695);
			g.drawImage(next3btn, 1368, 710, 100, 72, null);

		}
		if (game.turnover()) {
			g.drawImage(nextbutton, 1230, 20, 50, 50, null);

		}

		paintHand(g);

		g.setColor(Color.black);
		for (int i = 0; i < 4; i++) {
			g.setFont(new Font("Sans Serif", Font.BOLD, 50));
			Player p = game.getplayerarr()[i];
			System.out.println(p);
			g.drawImage(trains.get(p.getColor()), 1040 + (i * 120), 900, 100, 50, null);
			g.drawString("" + p.numTrains(), 1060 + (i * 120), 940);
			g.drawString("" + p.getScore(), 1075 + (120 * i), 1000);
			if ((!game.status.equals("drawing starting tickets") && i == game.playerturn())
					|| i == ticketplayercounter) {
				int currplayer = (!game.status.equals("drawing starting tickets") && i == game.playerturn())
						? game.playerturn()
						: ticketplayercounter;
				g.drawImage(box, 1030 + (currplayer * 120), 865, 120, 170, null);
				for (int j = p.getStations(); j > 0; j--) {
					g.drawImage(stations.get(p.getColor()), 975, 800 + 42 * j, 40, 40, null);
				}
				g.drawImage(usestation, 970, 970, 50, 36, null);
				if (!game.status.equals("drawing starting tickets")) {
					g.setFont(smallfont);
					g.drawString("Number of tickets: " + p.getTickets().size(), 755, 860);
					g.drawImage(p.getTickets().get(ticketindex).getImage(), 750, 870, 200, 130, null);
					g.drawImage(next2button, 925, 917, 40, 35, null);
				}

			}
		}
		// g.drawImage(trains.get("green"), 1400, 900, 100, 50, null);
		// g.drawImage(trains.get("red"), 1280, 900, 100, 50, null);
		// g.drawImage(trains.get("blue"), 1160, 900, 100, 50,1 null);
		// g.drawImage(trains.get("yellow"), 1040, 900, 100, 50, null);
		/*
		 * g.drawString("45", 1060, 940); g.drawString("45", 1180, 940);
		 * g.drawString("45", 1300, 940); g.drawString("45", 1420, 940);
		 */
		// g.setColor(Color.GREEN);
		// g.drawString("0", 1420, 1020);
		// g.setColor(Color.RED);
		// g.drawString("0", 1300, 1020);
		// g.setColor(Color.BLUE);
		// g.drawString("0", 1180, 1020);
		// g.setColor(Color.YELLOW);
		// g.drawString("0", 1060, 1020);

	}

	private void paintTrains(Graphics g, String color, int x, int y, int angle) {
		if (color != null) {
			int width = 44;
			int height = 22;
			// System.out.println(x + " " + y);
			g.drawImage(Helper.rotate(trains.get(color), angle), x, y,
					(int) (Math.abs(Math.cos(Math.toRadians(angle)) * width)
							+ Math.abs(Math.sin(Math.toRadians(angle)) * height)),
					(int) (Math.abs(Math.sin(Math.toRadians(angle)) * width)
							+ Math.abs(Math.cos(Math.toRadians(angle)) * height)),
					null);
		}
	}

	private void paintHand(Graphics g) {
		HashMap<String, Integer> hand = game.currentPlayer().getCards();
		if (hand.isEmpty()) {
			return;
		}
		int uniqueColors = 0;
		int xWeight = 0;
		String rightmostColor = null;
		for (String c : Helper.colors) {
			if (hand.get(c) != 0) {
				uniqueColors++;
				rightmostColor = c;
				xWeight += 100 + 15 * hand.get(c);
			}
		}
		if (rightmostColor == null) {
			return;
		}
		xWeight -= 100 + 15 * hand.get(rightmostColor);

		if (uniqueColors == 1) {
			for (String c : Helper.colors) {
				for (int i = 0; i < hand.get(c); i++) {
					if (c.equals(game.getPurchaseColor())) {
						g.drawImage(Helper.rotate(cards.get(c), 90), 20 + 15 * i, 840, 115, 180, null);
					} else {
						g.drawImage(Helper.rotate(cards.get(c), 90), 20 + 15 * i, 860, 115, 180, null);
					}
				}
			}
		} else if (uniqueColors > 1) {
			double gap = (615 - 15 * hand.get(rightmostColor) - xWeight) / (double) (uniqueColors - 1);
			double xOffset = 0;
			for (String c : Helper.colors) {
				if (hand.get(c) != 0) {
					for (int i = 0; i < hand.get(c); i++) {
						if (c.equals(game.getPurchaseColor())) {
							g.drawImage(Helper.rotate(cards.get(c), 90), 20 + (int) xOffset, 840, 115, 180, null);
						} else {
							g.drawImage(Helper.rotate(cards.get(c), 90), 20 + (int) xOffset, 860, 115, 180, null);
						}
						xOffset += 15;
					}
					xOffset += gap + 100;
				}
			}
		}
	}

	public int numDiscardedTickets(ArrayList<Ticket> tlist) {
		int num = 0;
		for (Ticket t : tlist) {
			if (t.isDiscarded())
				num++;
		}
		return num;
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + ", " + y);
		// routecounter++;
		// game.checkRoute(routecounter);
		//if(x > 1280 && x < 1300 && y > 810 && y < 825) {
			//game.changeTurn();;
		//}
		if (!game.turnover()) {
			if (game.status.equals("drawing starting tickets")) {
				ArrayList<Ticket> tickets = startingtickets.get(ticketplayercounter);
				for (int i = 0; i < tickets.size(); i++) {
					if (x > 60 + (190 * i) && x < 240 + (190 * i) && y > 875 && y < 990
							&& (numDiscardedTickets(tickets) < 2 || tickets.get(i).isDiscarded())) {
						tickets.get(i).discard();
					}
				}
				if (x > 823 && x < 903 && y > 900 && y < 945) {
					game.addTicketsToPlayer(tickets, ticketplayercounter);
					ticketplayercounter++;
					if (ticketplayercounter >= 4) {
						game.finishdrawingstartingtickets();
						// game.changeStatus("game over");
						// game.endGame();
						// change later
					}
				}
			} else if (game.status.equals("game over")) {
				if (x > 1490 && x < 1530 && y > 220 && y < 255) {
					completedtindex++;
				}
				if (x > 1490 && x < 1530 && y > 395 && y < 430) {
					incompletedtindex++;
				}
				if (x > 1368 && x < 1468 && y > 710 && y < 782) {
					System.out.println("next");
					playerscoringcounter++;
					if (playerscoringcounter == 4) {
						super.getFrame().toEnd();
					}
				}
			} else if (game.status.equals("showing tickets")) {
				for (int i = 0; i < drawntickets.size(); i++) {
					if (x > 1330 && x < 1510 && y > 230 + 120 * i && y < 345 + 120 * i
							&& (numDiscardedTickets(drawntickets) < 2 || drawntickets.get(i).isDiscarded())) {
						drawntickets.get(i).discard();
					}
				}
				if (x > 1378 && x < 1458 && y > 600 && y < 645) {
					game.addTicketsToPlayer(drawntickets, game.playerturn());
					game.endTurn();
				}
			} else if (x > 30 && x < 150 && y > 320 && y < 361 && game.cardcounter() == 0) {
				game.cancel();
				prompt = "";
			} else if (x > 1378 && x < 1458 && y > 640 && y < 685 && (game.status.equals("bought tunnel"))) {
				System.out.println("-");
				game.endTurn();
				prompt = "";
			} else if (x > 1358 && x < 1478 && y > 695 && y < 736 && game.status.equals("buy tunnel")) {
				game.cancel();
				game.endTurn();
				prompt = "";
			} else if (x < 1300 && y < 840 && game.status.equals("use station")) {
				for (int i = 0; i < cities.size(); i++) {
					if (x > citiesX.get(i) - 25 && x < citiesX.get(i) + 25 && y > citiesY.get(i) - 25
							&& y < citiesY.get(i) + 25) {
						System.out.println(cities.get(i));
						game.setCurrentCity(cities.get(i));
						if (game.placeStation(cities.get(i))) {
							prompt = "Please pay " + game.getStationPrice() + " card(s) of the same color";
						} else {
							prompt = "This city already has a station or you do not have any routes connected to this city";
						}
						if (game.status.equals("use station")) {
							System.out.println("use");
						}
						// game.tryCity(cities.get(i));
					}
				}
			} else if (x > 30 && x < 150 && y > 270 && y < 311 && game.cardcounter() == 0) {
				game.changeStatus("buy route");
				prompt = "Select the cards for your purchase";
			}

			else if (y < 840 && x > 1330 && x < 1510) {
				if (y / 5 % 24 != 0) {
					switch (y / 120) {
					case 0:
						if (game.cardcounter() == 0 && !game.getTickets().isEmpty())
							game.changeStatus("drawing tickets");
						else
							prompt = "no more tickets";
							//game.changeStatus("game over");
						//game.endGame();
						break;
					case 1:
						if(x < 1404) 
						game.tryDrawCard("deck");
						break;
						
					default:
						game.tryDrawCard("" + (y / 120 - 2));
					}
				}
			} else if (x > 970 && x < 1020 && y > 970 && y < 1006 && game.cardcounter() == 0) {
				prompt = "Select a city to place the station";
				game.changeStatus("use station");
				// game.tryStation();
			} else if (x > 925 && x < 965 && y > 917 && y < 952) {
				ticketindex++;
				if (ticketindex >= game.currentPlayer().getTickets().size()) {
					ticketindex = 0;
				}
			} else if (x > 20 && x < 735 && y > 860 && y < 1040 && game.status.equals("use station")) {
				// String c = findColor(x, game.getHand(GamePanel.getCurrentTurn)); something to
				// get the current player's hand
				HashMap<String, Integer> hand = game.currentPlayer().getCards();
				String c = findColor(x, hand);
				if (c != null) {
					System.out.println(c);
					if (game.buyStation(c, game.getCurrentCity())) {
						prompt = "";
					}
					// game.tryHand(c);
				}
			} else if (x > 20 && x < 735 && y > 860 && y < 1040 && game.status.equals("buy route")) {
				// String c = findColor(x, game.getHand(GamePanel.getCurrentTurn)); something to
				// get the current player's hand
				HashMap<String, Integer> hand = game.currentPlayer().getCards();
				String c = findColor(x, hand);
				if (c != null) {
					System.out.println(c);
					game.setPurchaseColor(c);
					prompt = "Select the two cities the route connects";
					game.changeStatus("choosing cities");
					// game.tryHand(c);
				}

			} else if (x > 20 && x < 735 && y > 860 && y < 1040 && game.status.equals("buy tunnel")) {
				// String c = findColor(x, game.getHand(GamePanel.getCurrentTurn)); something to
				// get the current player's hand
				HashMap<String, Integer> hand = game.currentPlayer().getCards();
				String c = findColor(x, hand);
				if (c != null) {
					System.out.println(c);
					if (game.payExtraCards(c)) {
						prompt = "Success: tunnel";
						game.changeStatus("bought tunnel");
					}
					// game.tryHand(c);
				}
			} else if (x < 1300 && y < 840 && game.status.equals("choosing cities")) {
				for (int i = 0; i < cities.size(); i++) {
					if (x > citiesX.get(i) - 25 && x < citiesX.get(i) + 25 && y > citiesY.get(i) - 25
							&& y < citiesY.get(i) + 25) {
						System.out.println(cities.get(i));
						if (!game.setcity1(cities.get(i))) {
							game.setcity2(cities.get(i));
							prompt = game.buyRoute();

						}
						// game.tryCity(cities.get(i));
					}
				}
			}

		} else {
			if (x > 1230 && x < 1280 && y > 20 && y < 70) {
				prompt = "";
				game.changeTurn();
			}
		}
		// getFrame().toEnd();

		repaint();
	}

	private String findColor(int x, HashMap<String, Integer> hand) {
		x -= 20;
		int uniqueColors = 0;
		int xWeight = 0;
		String rightmostColor = null;
		for (String c : Helper.colors) {
			if (hand.get(c) != 0) {
				uniqueColors++;
				rightmostColor = c;
				xWeight += 100 + 15 * hand.get(c);
			}
		}
		xWeight -= 100 + 15 * hand.get(rightmostColor);

		if (uniqueColors == 1) {
			for (String c : Helper.colors) {
				if (hand.get(c) != 0) {
					if (x < 100 + 15 * hand.get(c)) {
						return c;
					} else {
						return null;
					}
				}
			}
		} else if (uniqueColors > 1) {
			double gap = (615 - 15 * hand.get(rightmostColor) - xWeight) / (double) (uniqueColors - 1);
			for (String c : Helper.colors) {
				if (hand.get(c) != 0) {
					for (int i = 0; i < hand.get(c); i++) {
						x -= 15;
					}
					x -= 100;
					if (x < 0) {
						return c;
					}
					x -= gap;
					if (x < 0) {
						return null;
					}
				}
			}
		}
		return null;
	}

	public void loadImages() {
		cards = new HashMap<>();
		trains = new HashMap<>();
		stations = new HashMap<>();
		tickets = new BufferedImage[1];
		cities = new ArrayList<>();
		citiesX = new ArrayList<>();
		citiesY = new ArrayList<>();
		try {
			map = ImageIO.read(Panel.class.getResource("/Images/europeMap.png"));

			cards.put("back", ImageIO.read(Panel.class.getResource("/Images/backCard.png")));
			for (String c : Helper.colors) {
				cards.put(c, ImageIO.read(Panel.class.getResource("/Images/" + c + "Card.png")));
			}
			box = ImageIO.read(Panel.class.getResource("/Images/box.png"));
			bigbox = ImageIO.read(Panel.class.getResource("/Images/biggerbox.png"));
			donebutton = ImageIO.read(Panel.class.getResource("/Images/DONE.png"));
			nextbutton = ImageIO.read(Panel.class.getResource("/Images/nextbutton.png"));
			circle = ImageIO.read(Panel.class.getResource("/Images/circle2.png"));
			usestation = ImageIO.read(Panel.class.getResource("/Images/use.png"));
			next2button = ImageIO.read(Panel.class.getResource("/Images/next2.png"));
			next3btn = ImageIO.read(Panel.class.getResource("/Images/next3.png"));
			buyroutebtn = ImageIO.read(Panel.class.getResource("/Images/buyroute.png"));
			cancel = ImageIO.read(Panel.class.getResource("/Images/cancel.png"));
			trains.put("red", ImageIO.read(Panel.class.getResource("/Images/redTrain.png")));
			trains.put("blue", ImageIO.read(Panel.class.getResource("/Images/blueTrain.png")));
			trains.put("green", ImageIO.read(Panel.class.getResource("/Images/greenTrain.png")));
			trains.put("yellow", ImageIO.read(Panel.class.getResource("/Images/yellowTrain.png")));
			stations.put("red", ImageIO.read(Panel.class.getResource("/Images/redstation.png")));
			stations.put("blue", ImageIO.read(Panel.class.getResource("/Images/bluestation.png")));
			stations.put("green", ImageIO.read(Panel.class.getResource("/Images/greenstation.png")));
			stations.put("yellow", ImageIO.read(Panel.class.getResource("/Images/yellowstation.png")));

			tickets[0] = ImageIO.read(Panel.class.getResource("/Images/backTicket.png"));

			Scanner sc = new Scanner("Cadiz 110 822\r\n" + "Lisboa 25 753\r\n" + "Madrid 112 722\r\n"
					+ "Barcelona 259 737\r\n" + "Pamplona 243 618\r\n" + "Marseille 441 611\r\n" + "Brest 144 396\r\n"
					+ "Dieppe 261 359\r\n" + "Paris 325 416\r\n" + "Bruxelles 372 306\r\n" + "Zurich 477 487\r\n"
					+ "Munchen 563 403\r\n" + "Frankfurt 489 347\r\n" + "Roma 595 648\r\n" + "Venezia 585 530\r\n"
					+ "London 276 240\r\n" + "Edinburgh 189 42\r\n" + "Amsterdam 401 244\r\n" + "Essen 507 255\r\n"
					+ "Kobenhavn 599 118\r\n" + "Stockholm 736 17\r\n" + "Berlin 637 275\r\n" + "Wien 714 427\r\n"
					+ "Zagrab 695 546\r\n" + "Brindisi 708 681\r\n" + "Palermo 644 819\r\n" + "Athina 869 784\r\n"
					+ "Sarajevo 806 625\r\n" + "Budapest 774 460\r\n" + "Warszawa 851 264\r\n" + "Danzig 787 173\r\n"
					+ "Riga 894 56\r\n" + "Petrograd 1115 48\r\n" + "Wilno 996 232\r\n" + "Kyiv 1047 335\r\n"
					+ "Bucuresti 974 553\r\n" + "Sofia 892 634\r\n" + "Smyrna 982 820\r\n"
					+ "Constantinople 1039 715\r\n" + "Angora 1140 784\r\n" + "Erzurum 1246 755\r\n"
					+ "Sevastopol 1153 571\r\n" + "Sochi 1268 590\r\n" + "Rostov 1277 474\r\n" + "Kharkov 1224 403\r\n"
					+ "Moskva 1242 206\r\n" + "Smolensk 1130 238");
			while (sc.hasNext()) {
				cities.add(sc.next());
				citiesX.add(Integer.parseInt(sc.next()));
				citiesY.add(Integer.parseInt(sc.next()));
			}
		} catch (Exception E) {
			System.out.println("FIRE IN THE Game! reading cities");
			return;
		}
	}
}
