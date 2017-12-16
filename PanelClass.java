import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelClass extends JPanel implements ActionListener, KeyListener{
	// Declare variables for location, speed and size of graphics
	int x = 400,y = 375, 
			x1 = (int)(Math.random()*700), y1=(int)(Math.random()*680), 
			x2 = (int)(Math.random()*700), y2=(int)(Math.random()*680), 
			x3 = (int)(Math.random()*700), y3=(int)(Math.random()*680), 
			x4 = (int)(Math.random()*700), y4=(int)(Math.random()*680),
			powerX = 1000000, powerY = 1000000, boxPowerX = 20, boxPowerY = 20, 
			vX = 5, vY = 5, vX1 = 0, vY1 = 1, vX2 = 1, vY2 = 0, vX3 = 2, vY3 = 0, vX4 = 0, vY4 = 2, 
			boxX = 20, boxY = 20, boxX1 = 40, boxY1 = 40, boxX2 = 20, boxY2 = 20, divideNum = 1000;
	// Used for keeping track of lives and carrying out effects of losing a life
	boolean justHit=false;
	int livesLeft=3, num=0;
	Color colorToUse = Color.BLACK;
	// Creates a score variable
	int score=0;
	// Creates timer for use with ActionListener
	Timer timer = new Timer(5, this);
	// Creates variable to indicate if player has lost
	boolean gameOver = false;
	// Booleans checking status of powerup
	boolean makePowerup = false;
	// Create Array list to store keypresses, allows for multiple keypresses to be executed at once (diagonal movement)
	private ArrayList<Integer>keyList;
	
	/** JPanel Constructor */
	public PanelClass() {
		// Creates JPanel
		setBackground(Color.BLACK);
		setLayout(null);
		setFocusable(true);
		requestFocusInWindow();

		// Creates keylistener and its components
		keyList = new ArrayList<Integer>();
		addKeyListener((KeyListener)this);
		
		// Starts timer
		timer.start();
	}
	
	/** Create player, enemy balls, and end game text */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Creates flashing graphic after collision
		if(justHit) {
			if(num % 50 == 0)
				colorToUse = Color.BLACK;
			else if(num % 50 == 25)
				colorToUse = Color.WHITE;
			g.setColor(colorToUse);
			if(num >= 750)
				justHit = false;
		}
		num++;
		
		// Creates player ball
		if(!justHit)
			g.setColor(Color.WHITE);
		g.fillOval(x, y, boxX, boxY);
		
		// Creates enemy balls
		g.setColor(Color.RED);
		g.fillOval(x1, y1, boxX1, boxY1);
		g.fillOval(x2, y2, boxX1, boxY1);
		g.fillOval(x3, y3, boxX2, boxY2);
		g.fillOval(x4, y4, boxX2, boxY2);
		
		// Creates displayed score in top right
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		g.drawString("Score: " + score, this.getWidth() - 150, 30);
		
		// Initializes location of powerup if set time has elapsed
		if(makePowerup) {
			powerX = (int)(Math.random()*700);
			powerY = (int)(Math.random()*700);
			makePowerup = false;
		}
		g.setColor(Color.GREEN);
		g.fillRect(powerX, powerY, boxPowerX, boxPowerY);
		
		// Displays game over message if player loses
		if(gameOver) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Game Over!  Your score: " + score, (int)((double)(this.getWidth()) / 3.55),
						 (int)((double)(this.getHeight()) / 2.5));
		}
	}
	
	/** Movement for enemy balls, increments score, stops game if collision occurs */
	public void actionPerformed(ActionEvent e)
	{
		// Checks if enemy balls are at edge of panel, and turns them around if so
		if(x1 <= 0 || x1 >= this.getWidth() - boxX1)
			vX1 *= -1;
		if(y1 <= 0 || y1 >= this.getHeight() - boxY1)
			vY1 *= -1;
		x1 += vX1;
		y1 += vY1;
		
		if(x2 <= 0 || x2 >= this.getWidth() - boxX1)
			vX2 *= -1;
		if(y2 <= 0 || y2 >= this.getHeight() - boxY1)
			vY2 *= -1;
		x2 += vX2;
		y2 += vY2;
		
		if(x3 <= 0 || x3 >= this.getWidth() - boxX1)
			vX3 *= -1;
		if(y3 <= 0 || y3 >= this.getHeight() - boxY1)
			vY3 *= -1;
		x3 += vX3;
		y3 += vY3;
		
		if(x4 <= 0 || x4 >= this.getWidth() - boxX1)
			vX4 *= -1;
		if(y4 <= 0 || y4 >= this.getHeight() - boxY1)
			vY4 *= -1;
		x4 += vX4;
		y4 += vY4;
		
		// Increments score
		score++;
		
		// Speeds up player and enemy balls over time
		if(score % divideNum==0) {
			vX += 2;
			vY += 2;
			if(vX1 < 0) vX1 -= 1; else vX1 += 1;
			if(vY1 < 0) vY1 -= 1; else vY1 += 1;
			if(vX2 < 0) vX2 -= 1; else vX2 += 1;
			if(vY2 < 0) vY2 -= 1; else vY2 += 1;
			if(vX3 < 0) vX3 -= 1; else vX3 += 1;
			if(vY3 < 0) vY3 -= 1; else vY3 += 1;
			if(vX4 < 0) vX4 -= 1; else vX4 += 1;
			if(vY4 < 0) vY4 -= 1; else vY4 += 1;
			divideNum *= 2;
		}
		
		// Calls for creation of power up
		if(score % 1000 == 0)
			makePowerup = true;
		
		if(gotPowerup()) {
			score += 1000;
			powerX = 1000000;
			powerY = 1000000;
		}
		
		// If player hits enemy ball, deducts one life and ends game if player is out of lives
		if(isCollided() && !justHit) {
			livesLeft--;
			justHit = true;
			num = 0;
			
			if(livesLeft == 0) {
				timer.stop();
				this.removeKeyListener(this);
				gameOver = true;
			}
		}
		repaint();
	}
	
	/** Checks if enemy balls have come in contact with the player */
	public boolean isCollided() {
		if((x+10 <= x1+boxX1 && x+10 >= x1) && (y+10 <= y1+boxY1 && y+10 >= y1))
			return true;
		if((x+10 <= x2+boxX1 && x+10 >= x2) && (y+10 <= y2+boxY1 && y+10 >= y2))
			return true;
		if((x+10 <= x3+boxX2 && x+10 >= x3) && (y+10 <= y3+boxY2 && y+10 >= y3))
			return true;
		if((x+10 <= x4+boxX2 && x+10 >= x4) && (y+10 <= y4+boxY2 && y+10 >= y4))
			return true;
		return false;
	}
	
	/** Checks if player has collided with the power up */
	public boolean gotPowerup() {
		return((x+10 <= powerX+boxPowerX && x+10 >= powerX) && (y+10 <= powerY+boxPowerY && y+10 >= powerY));
	}
	
	/** Referenced from https://www.youtube.com/watch?v=5UaEUrbpDPE
	  * Processes newly clicked keys into array list */
	public void keyPressed(KeyEvent e) {
		if(!keyList.contains(e.getKeyCode()) && e.getKeyCode() != 86)
			keyList.add(new Integer(e.getKeyCode()));
		move();
	}
	
	/** Removes keys that have been unpressed from array list */
	public void keyReleased(KeyEvent e) {
		keyList.remove(new Integer(e.getKeyCode()));
	}
	
	/** Produces the outputs for all keypresses only if player is not going into a wall */
	public void move() {
		int newX = x;
		int newY = y;
		
		if((keyList.contains(KeyEvent.VK_W) || keyList.contains(KeyEvent.VK_UP)) && y > 0)
			newY -= vY;
		if((keyList.contains(KeyEvent.VK_A) || keyList.contains(KeyEvent.VK_LEFT)) && x > 0)
			newX -= vX;
		if((keyList.contains(KeyEvent.VK_S) || keyList.contains(KeyEvent.VK_DOWN)) && y < this.getHeight()-boxY+1)
			newY += vY;
		if((keyList.contains(KeyEvent.VK_D) || keyList.contains(KeyEvent.VK_RIGHT)) && x < this.getWidth()-boxX+1)
			newX += vX;
		x = newX;
		y = newY;
		repaint();
	}
	
	/** Unused method; kept because KeyListener interface was implemented */
	public void keyTyped(KeyEvent e) {}
}