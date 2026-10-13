
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class EndPanel extends Panel {
	private BufferedImage endScreen;
    private Map<String, BufferedImage> stations;
	
	public EndPanel(Frame f) {
		super(f);
	}
	
	public void paint(Graphics g) {
        int[] scores = new int[4];
        String next;
        int max;
        for (int i = 0; i < scores.length; i++) {
            scores[i] = GameState.playerArr[i].getScore();
        }
        System.out.println(Arrays.toString(scores));
		g.drawImage(endScreen, -20, -30, 1600, 1195, null);

        g.setFont(new Font("Sans Serif", Font.BOLD, 90));
        max = Helper.getMax(scores);
        next = GameState.playerArr[max].getColor();
        g.drawImage(stations.get(next), 720, 150, 158, 150, null);
        g.drawString("" + scores[max], 720, 330);
        scores[max] = Integer.MIN_VALUE;
        max = Helper.getMax(scores);
        next = GameState.playerArr[max].getColor();
        g.drawImage(stations.get(next), 280, 330, 158, 150, null);
        g.drawString( "" +scores[max], 280, 510);
        scores[max] = Integer.MIN_VALUE;
        max = Helper.getMax(scores);
        next = GameState.playerArr[max].getColor();
        g.drawImage(stations.get(next), 1160, 330, 158, 150, null);
        g.drawString("" + scores[max], 1160, 510);
        scores[max] = Integer.MIN_VALUE;
        max = Helper.getMax(scores);
        next = GameState.playerArr[max].getColor();
        g.drawImage(stations.get(next), 720, 720, 158, 150, null);
        g.drawString("" + scores[max], 720, 900);
	}

	public void loadImages() {
        stations = new HashMap<>();
		try {
			endScreen = ImageIO.read(Panel.class.getResource("/Images/endScreen.png"));

            stations.put("red", ImageIO.read(Panel.class.getResource("/Images/redStation.png")));
			stations.put("blue", ImageIO.read(Panel.class.getResource("/Images/blueStation.png")));
			stations.put("green", ImageIO.read(Panel.class.getResource("/Images/greenStation.png")));
			stations.put("yellow", ImageIO.read(Panel.class.getResource("/Images/yellowStation.png")));
		} catch (Exception E) {
			System.out.println("FIRE IN THE End!");
			return;
		}
	}
}
