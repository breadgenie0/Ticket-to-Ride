
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class StartPanel extends Panel implements MouseListener {
	private BufferedImage startScreen, gameButton, creditsButton;
	
	public StartPanel(Frame f) {
		super(f);
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(startScreen, -20, -30, 1600, 1195, null);
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x >= 540 && x < 1030 && y >= 320 && y < 550) {
			getFrame().toGame();
		}
		if (x >= 540 && x <= 1030 && y >= 590 && y < 810) {
			getFrame().toTutorial();
		}
	}
	
	public void loadImages() {
		try {
			startScreen = ImageIO.read(Panel.class.getResource("/Images/startScreen.png"));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Start!");
			return;
		}
	}
}
