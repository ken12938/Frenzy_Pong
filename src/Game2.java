//package com.edu4java.minitennis1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game2 extends JPanel {

	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this, 650);
	Racquet racquet2 = new Racquet(this, 50);
	Racquet2 racquet3 = new Racquet2(this, 50);
	Racquet2 racquet4 = new Racquet2(this, 650);
	ArrayList<Brick> bricks; 
	int score = 0;
	
	private int getScore() {
		return score;
	}
	
	public Game2() {
		
		bricks = new ArrayList<Brick>();
		bricks.add(new Brick(this, 425, 200));
		bricks.add(new Brick(this, 340, 200));
		bricks.add(new Brick(this, 510, 200));
		bricks.add(new Brick(this, 425, 235));
		bricks.add(new Brick(this, 340, 235));
		bricks.add(new Brick(this, 510, 235));
		bricks.add(new Brick(this, 425, 270));
		bricks.add(new Brick(this, 340, 270));
		bricks.add(new Brick(this, 510, 270));
		bricks.add(new Brick(this, 425, 305));
		bricks.add(new Brick(this, 340, 305));
		bricks.add(new Brick(this, 510, 305));
		
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				racquet2.keyReleased(e);
				racquet3.keyReleased(e);
				racquet4.keyReleased(e);
			}
			
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
				racquet2.keyPressed(e);
				racquet3.keyPressed(e);
				racquet4.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racquet.move();
		racquet2.move();
		racquet3.move();
		racquet4.move();
		
		if(bricks.size() == 0) {
			gameOver();
		}
	}	
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		racquet2.paint(g2d);
		racquet3.paint(g2d);
		racquet4.paint(g2d);
		g2d.setColor(Color.GRAY);
		for(int i = 0; i < bricks.size(); i++) {
			bricks.get(i).paint(g2d);
		}

		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}	
	
	public void gameOver() {
		if(bricks.size() == 0) {
			JOptionPane.showMessageDialog(this, "Your score is " + getScore(), "You win!", JOptionPane.YES_NO_OPTION);
		}else {
		JOptionPane.showMessageDialog(this, "Your score is " + getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
		}
		System.exit(ABORT);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game2 game = new Game2();
		frame.add(game);
		frame.setSize(750, 750);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}