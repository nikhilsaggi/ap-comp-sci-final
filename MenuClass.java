import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuClass extends JPanel implements ActionListener {
	// Creates variables to be toggled when buttons are clicked
	boolean showInstructions = false;
	boolean playHit = false;
	
	/** JPanel Constructor */
	public MenuClass() {
		// Creates JPanel
		setBackground(Color.BLACK);
		setLayout(null);
		setFocusable(true);	
		
		// Creates JButtons
		JButton playButton = new JButton("Play");
		JButton helpButton = new JButton("Instructions");
		playButton.setBounds(300, 350, 200, 50);
		helpButton.setBounds(300, 410, 200, 50);
		playButton.addActionListener(this);
		helpButton.addActionListener(this);
		add(playButton);
		add(helpButton);
	}
	
	/** Listens for any presses of the buttons and responds accordingly */
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// Changes a variable to indicate play button was pressed
		if(actionCommand.equals("Play"))
			playHit = true;
		// Calls for instructions to be displayed
		else if(actionCommand.equals("Instructions")) {
			showInstructions = true;
			repaint();
		}
	}
	
	/** Creates title text and instructions */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Creates title
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 72));
		g.drawString("Dodge the Ball", 175, 200);
		
		// Creates instructions
		if(showInstructions) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.drawString("You are the white ball.", 300, 600);
			g.drawString("Move with WASD or arrow keys.", 300, 625);
			g.drawString("You have three lives.", 300, 650);
			g.drawString("Don't touch the red balls!", 300, 675);
		}
	}
}