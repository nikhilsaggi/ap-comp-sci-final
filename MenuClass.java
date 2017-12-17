import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuClass extends JPanel implements ActionListener {
	// Creates variables to be toggled when buttons are clicked
	boolean showInstructions = false;
	boolean playHit = false;
	int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().height/1.5);
	int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().height/1.2);
	int buttonWidth = width/3;
	int buttonHeight = height/10;
	
	/** JPanel Constructor */
	public MenuClass() {
		// Creates JPanel
		setBackground(Color.BLACK);
		setLayout(null);
		setFocusable(true);	
		System.out.println(width);
		// Creates JButtons
		Font font = new Font("Dialog", Font.BOLD, width/40);
		JButton playButton = new JButton("Play");
		playButton.setFont(font);
		JButton helpButton = new JButton("Instructions");
		helpButton.setFont(font);
		playButton.setBounds(width/2-buttonWidth/2, (int)(height/2)-buttonHeight/2, buttonWidth, buttonHeight);
		helpButton.setBounds(width/2-buttonWidth/2, (int)(height/2-buttonHeight/2+buttonHeight*1.2), 
							 buttonWidth, buttonHeight);
		playButton.addActionListener(this);
		helpButton.addActionListener(this);
		add(playButton);
		add(helpButton);
	}
	
	/** Listens for any presses of the buttons and responds accordingly */
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// Changes a variable to indicate play button was pressed
		if (actionCommand.equals("Play"))
			playHit = true;
		// Calls for instructions to be displayed
		else if (actionCommand.equals("Instructions")) {
			showInstructions = true;
			repaint();
		}
	}
	
	/** Creates title text and instructions */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Creates title
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, width/8));
		g.drawString("Dodge the Ball", width/8, height/4);
		
		// Creates instructions
		if (showInstructions) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, width/40));
			g.drawString("You are the white ball.", (int)(width/2.8), (int)(height/1.4));
			g.drawString("Move with WASD or arrow keys.", (int)(width/2.8), (int)(height/1.4+height*.03125));
			g.drawString("You have three lives.", (int)(width/2.8), (int)(height/1.4+height*.0625));
			g.drawString("Don't touch the red balls!", (int)(width/2.8), (int)(height/1.4+height*.09375));
		}
	}
}