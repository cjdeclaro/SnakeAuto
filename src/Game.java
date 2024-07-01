import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import java.util.Random; 

import javax.swing.*;

public class Game extends JPanel{
	int bodyLength = 4;
	
	Random rand = new Random();
	int foodX = rand.nextInt(25) * 20;
	int foodY = rand.nextInt(24) * 20;
	
	int[][] bodyHistory = {
			{20, 20},
			{20, 20},
			{20, 20},
			{20, 20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
			{-20, -20},
	};
	
	int snakeX = 20;
	int snakeY = 20;
	
	Timer t;
	
	String direction = "right";

	public Game() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		t = new Timer(1000/10, e -> run());
		
		run();
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					direction = "right";
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					direction = "left";
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					direction = "down";
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					direction = "up";
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		t.start();
	}
	
	void run() {
		String newDirection = "right";

		if(bodyHistory[0][0] < foodX) {
			newDirection = "right";
		}
		
		if(bodyHistory[0][0] > foodX) {
			newDirection = "left";
		}
		
		if(bodyHistory[0][1] > foodY) {
			newDirection = "up";
		}
		
		if(bodyHistory[0][1] < foodY) {
			newDirection = "down";
		}

		//Decisions
		switch(direction) {
			case "right":
				if(newDirection != "left")
					direction = newDirection;
				else
					direction = "up";
				break;
			case "left":
				if(newDirection != "right")
					direction = newDirection;
				else
					direction = "up";
				break;
			case "up":
				if(newDirection != "down")
					direction = newDirection;
				else
					direction = "left";
				break;
			case "down":
				if(newDirection != "up")
					direction = newDirection;
				else
					direction = "left";
				break;
		}
		
		moveHead();
		moveBody();
		checkForFood();
		checkIfColided();
		
		setFocusable(true);
		requestFocusInWindow();
		repaint();
	}
	
	void moveHead() {
		if(bodyHistory[0][0] >= 0) {
			switch(direction) {
				case "right":
					bodyHistory[0][0]+=20;
					break;
				case "left":
					bodyHistory[0][0]-=20;
					break;
				case "up":
					bodyHistory[0][1]-=20;
					break;
				case "down":
					bodyHistory[0][1]+=20;
					break;
			}
		}
	}
	
	void moveBody() {
		for(int i = 19; i >= 1; i--) {
			if(bodyHistory[i][0] >= 0) {
				bodyHistory[i][0] = bodyHistory[i-1][0];
				bodyHistory[i][1] = bodyHistory[i-1][1];
			}
		}
	}
	
	void checkForFood() {
		if(bodyHistory[0][0] == foodX && bodyHistory[0][1] == foodY && bodyLength < 20) {
			createFood();
			bodyHistory[bodyLength][0] = bodyHistory[bodyLength-1][0];
			bodyHistory[bodyLength][1] = bodyHistory[bodyLength-1][1];
			bodyLength += 1;
		}
	}
	
	void createFood() {
		foodX = rand.nextInt(25) * 20;
		foodY = rand.nextInt(24) * 20;
	}
	
	void checkIfColided() {
		for(int i = 19; i >= 2; i--) {
			boolean xColided = bodyHistory[0][0] == bodyHistory[i][0];
			boolean yColided = bodyHistory[0][1] == bodyHistory[i][1];
			if(xColided && yColided) {
				t.stop();
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the food
		g.setColor(Color.RED);
		g.fillOval(foodX, foodY, 20, 20);
		
		int counter = 0;
		//Draw the body
		for(int[] xy : bodyHistory) {
			if(xy[0] >= 0) {
				if(counter == 1) {
					g.setColor(Color.GREEN);
					g.fillRect(xy[0],xy[1],20,20);
					g.setColor(Color.WHITE);
					g.drawRect(xy[0],xy[1],20,20);
				}
				else {
					g.setColor(Color.BLACK);
					g.fillRect(xy[0],xy[1],20,20);
					g.setColor(Color.WHITE);
					g.drawRect(xy[0],xy[1],20,20);
				}
				counter+=1;
			}
		}
	}
}
