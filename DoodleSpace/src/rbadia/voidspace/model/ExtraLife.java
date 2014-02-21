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

public class ExtraLife extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	private int extraLifeWidth = 32;
	private int extraLifeHeight= 32;
	private int speed = DEFAULT_SPEED;
	private static final int DEFAULT_SPEED = 2;

	public ExtraLife(GameScreen screen){
		this.setLocation(rand.nextInt(screen.getWidth() - extraLifeWidth), 0);
		this.setSize(extraLifeWidth, extraLifeHeight);
	}
	
	public int getExtraLifeWidth() {
		return extraLifeWidth;
	}
	
	public int getExtraLifeHeight() {
		return extraLifeHeight;
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