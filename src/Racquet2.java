import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet2 {
	
	private int X;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 60;
	double yPos = 300;
	double ySpeed = 0;
	double yAccel = 0;
	boolean keyReleased = true;
	private Game2 game;

	public Racquet2(Game2 game, int xValue) {
		this.game= game;
		this.X = xValue;
	}

	public void move() {
		ySpeed = ySpeed + yAccel;
		if(ySpeed > 0) {
			ySpeed = Math.min(6,ySpeed); // Maximum speed
			if(keyReleased) {
				ySpeed = ySpeed - 0.4; // Deceleration
				if(Math.abs(ySpeed) <= 0.2) {
					ySpeed = 0;
					yAccel = 0; // Makes the racquet stop if it is close enough to zero
				}
			}
		}
		if(ySpeed < 0) {
			ySpeed = Math.max(-6, ySpeed); // Minimum speed
			if(keyReleased) {
				ySpeed = ySpeed + 0.4; // Deceleration
				if(Math.abs(ySpeed) <= 0.2) {
					ySpeed = 0;
					yAccel = 0; // Makes the racquet stop if it is close enough to zero
				}
			}
		}
		
		if (yPos + ySpeed > 25 && yPos + ySpeed < game.getWidth()-85)
			yPos = yPos + ySpeed;
		
	}

	public void paint(Graphics2D g) {
		int yPosInt = (int)yPos;
		g.fillRect(X, yPosInt, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		keyReleased = true;
		yAccel = 0;
	}

	public void keyPressed(KeyEvent e) {
		keyReleased = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			yAccel = -0.3;
			if(ySpeed > -2 && yAccel == -0.3) {
				ySpeed = -2; // Starts out at 2 speed when switching directions
			}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			yAccel = 0.3;
			if(ySpeed < 2 && yAccel == 0.3) {
				ySpeed = 2; // Starts out at 2 speed when switching directions
			}
	}

	public Rectangle getBounds() {
		int yPosInt = (int)yPos;
		return new Rectangle(X, yPosInt, WIDTH, HEIGHT);
	}

	public int getTopX() {
		return X;
	}
	
	public double getSpeed() {
		return ySpeed;
	}
}