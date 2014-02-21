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

public class SplitAsteroid extends Rectangle {

	private int asteroidWidth = 64;
	private int asteroidHeight = 64;
	private static Random rand = new Random();
	private static final long serialVersionUID = 1L;	
	public static final int DEFAULT_SPEED = 4;	
	private int speed = rand.nextInt(2) + 1;
	private int direction;
	private int directionValue = 3;
	
	public SplitAsteroid(GameScreen screen) {
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

	public int getAsteroidWidth() {
		return asteroidWidth;
	}

	public void setAsteroidWidth(int asteroidWidth) {
		this.asteroidWidth = asteroidWidth;
	}

	public int getAsteroidHeight() {
		return asteroidHeight;
	}

	public void setAsteroidHeight(int asteroidHeight) {
		this.asteroidHeight = asteroidHeight;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
