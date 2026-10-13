
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class TutorialPanel extends Panel implements MouseListener {
	private BufferedImage tutorialScreen;
	
	public TutorialPanel(Frame f) {
		super(f);
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(tutorialScreen, 0, 0, 1550, 1240, null);
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.print(x + " " + y);
		if (x >= 670 && x < 1010 && y >= 890 && y < 1040) {
			getFrame().toStart();
		}
	}
	
	public void loadImages() {
		try {
			tutorialScreen = ImageIO.read(Panel.class.getResource("/Images/tutorialScreen.png"));
		} catch (Exception E) {
			System.out.println("FIRE IN THE Tutorial!");
			return;
		}
	}
}
