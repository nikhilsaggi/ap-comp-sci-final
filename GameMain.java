import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameMain extends JFrame implements ActionListener {		
	// Creates JPanels
	private PanelClass gamePanel;
	private MenuClass menuPanel;
	
	// Starts timer for action listener
	Timer time = new Timer(5,this);
	
	/** JFrame constructor */
	public GameMain() {
		// Sets up JFrame
		super("Dodge the Ball");
		setSize(800, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// Creates menu JPanel
		menuPanel = new MenuClass();
		getContentPane().add(menuPanel);
		time.start();
	}
	
	/** Checks if the play button is hit in the Menu class */
	public void actionPerformed(ActionEvent e) {
		if(menuPanel.playHit) {
			menuPanel.setVisible(false);
			gamePanel = new PanelClass();
			gamePanel.setFocusable(true);
			getContentPane().add(gamePanel);
			gamePanel.requestFocusInWindow();
			time.stop();
		}
	}
	
	/** Method called by main, necessary for main method to run */
	private void createAndDisplayGUI() throws InterruptedException {}
	
	/** Referenced from http://stackoverflow.com/questions/2861933/best-way-to-implement-game-loop-without-freezing-ui-thread
	  * Main method, calls a nonstatic method createAndDisplayGUI */
	public static void main(String[]args) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                try {
						new GameMain().createAndDisplayGUI();
					} 
	                catch (InterruptedException e) {
						e.printStackTrace();
					}
	            }
	            
	 } );
	}
}