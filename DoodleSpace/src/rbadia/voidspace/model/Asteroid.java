/*

Team Name: SpaceCoders

Team Members/GitHub Username: 
Eliel Ruiz Rodriguez / codehunter11
Amilcar Torres / amilcartorres

Project03: VoidSpace
Course: ICOM4015
Professor: Dr. Bienvenido Velez
Due Date: December 1, 2013

*/

package rbadia.voidspace.model;

import java.awt.Rectangle;
import java.util.Random;

import rbadia.voidspace.main.GameScreen;

public class Asteroid extends Rectangle{
	
	private static Random rand = new Random();
	private static final long serialVersionUID = 1L;	
	public static final int DEFAULT_SPEED = 4;	
	private int asteroidWidth = 32;
	private int asteroidHeight = 32;
	private int speed = rand.nextInt(2) + 1;
	private int direction;
	private int directionValue = 3;
	
	/**
	 * Crates a new asteroid at a random x location at the top of the screen 
	 * @param screen the game screen
	 */
	public Asteroid(GameScreen screen){
		this.setLocation(
        		rand.nextInt(screen.getWidth() - asteroidWidth),
        		0);
		this.setSize(asteroidWidth, asteroidHeight);
		int temp = (rand.nextInt(directionValue));
		if (temp == 0){
			this.direction = temp;
		}
		else if (temp %2 == 0){
			this.direction = rand.nextInt(directionValue) + 1;
		}
		else{
			this.direction = -rand.nextInt(directionValue) + 1;
		}
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getAsteroidWidth() {
		return asteroidWidth;
	}
	
	public int getAsteroidHeight() {
		return asteroidHeight;
	}

	/**
	 * Returns the current asteroid speed
	 * @return the current asteroid speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set the current asteroid speed
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Returns the default asteroid speed.
	 * @return the default asteroid speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
}
