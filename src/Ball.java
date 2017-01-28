import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Ball {

	private static final int DIAMETER = 25;
	double x = 25;
	double y = 125;
	double xa = 1.5;
	double ya = 1.5;
	Brick tempBrick;
	private Game2 game;
	
	public Ball(Game2 game) {
		this.game = game;
	}
	
	void move() {
		if (x + xa < 0)
			game.gameOver();
		if (x + xa > game.getWidth() - 30)
			game.gameOver();
		if (y + ya < 0)
			game.gameOver();
		if (y + ya > game.getHeight() - 30)
			game.gameOver();
		if (collision()){
			ya = -ya;
			xa = xa + game.racquet.xSpeed/2;
			y = game.racquet.getTopY() - DIAMETER;
		}
		if (collision2()) {
			ya = -ya;
			xa = xa + game.racquet2.xSpeed/2;
		}
		if (collision3()) {
			xa = -xa;
			ya = ya + game.racquet3.ySpeed/2;
		}
		if (collision4()) {
			xa = -xa;
			ya = ya + game.racquet4.ySpeed/2;
		}
		if (brickCollision()) {
			if((tempBrick.xPos + tempBrick.width - this.x <= 1 && tempBrick.xPos + tempBrick.width - this.x >= -1) || (tempBrick.xPos - DIAMETER - this.x <= 1 && tempBrick.xPos - DIAMETER - this.x >= -1)) {
				game.ball.xa = -game.ball.xa;
			} else/* if(Math.abs(tempBrick.yPos-this.y)<=1)*/ {
				game.ball.ya = -game.ball.ya;
			}
			tempBrick.visible = false;
			game.bricks.remove(tempBrick);
			game.score += 5;
		}
		
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	private boolean collision2() {
		return game.racquet2.getBounds().intersects(getBounds());
	}
	
	private boolean collision3() {
		return game.racquet3.getBounds().intersects(getBounds());
	}
	
	private boolean collision4() {
		return game.racquet4.getBounds().intersects(getBounds());
	}
	
	private boolean brickCollision() {
		for(int i = 0; i < game.bricks.size(); i++) {
			Brick x = game.bricks.get(i);
			if(x.getBounds().intersects(getBounds()) && x.visible == true) {
				tempBrick = x;
				return true;
			}
		}
		return false;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, DIAMETER, DIAMETER);
	}
}
