
import javax.swing.*;

public class Frame extends JFrame {
	private static final int WIDTH = 1550, HEIGHT = 1100;
	private Panel[] panels = new Panel[4];
	
	public Frame(String framename) {
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

        panels[0] = new StartPanel(this);
		panels[1] = new tutorial(this);
		panels[2] = new GamePanel(this);
		panels[3] = new EndPanel(this);

		add(panels[0]);
		setVisible(true);
	}

	public void toGame() {
		remove(panels[0]);
		add(panels[2]);
		revalidate();
		repaint();
	}

	public void toTutorial() {
		remove(panels[0]);
		add(panels[1]);
		revalidate();
		repaint();
	}
	
	public void toStart() {
		remove(panels[1]);
		add(panels[0]);
		revalidate();
		repaint();
	}

	public void toEnd() {
		remove(panels[2]);
		add(panels[3]);
		revalidate();
		repaint();
	}
}
