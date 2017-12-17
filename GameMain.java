import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameMain extends JFrame implements ActionListener {		
	// Creates JPanels
	private PanelClass gamePanel;
	private MenuClass menuPanel;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Starts timer for action listener
	Timer time = new Timer(5, this);
	
	/** JFrame constructor */
	public GameMain() {
		// Sets up JFrame
		super("Dodge the Ball");
		setSize((int)(screenSize.height/1.2), (int)(screenSize.height/1.5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// Creates menu JPanel
		menuPanel = new MenuClass();
		getContentPane().add(menuPanel);
		time.start();
	}
	
	/** Checks if the play button is hit in the Menu class */
	public void actionPerformed(ActionEvent e) {
		if (menuPanel.playHit) {
			menuPanel.setVisible(false);
			gamePanel = new PanelClass();
			gamePanel.setFocusable(true);
			getContentPane().add(gamePanel);
			gamePanel.requestFocusInWindow();
			time.stop();
		}
	}
	
	/** Creates main JFrame */
	public static void main(String[]args) {
		new GameMain();
	}
}