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

import rbadia.voidspace.main.GameScreen;

/**
 * Represents a ship/space craft.
 *
 */
public class Ship extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 5;
	private static final int Y_OFFSET = 5; // initial y distance of the ship from the bottom of the screen 
	
	protected int shipWidth = 25;
	protected int shipHeight = 25;
	protected int speed = DEFAULT_SPEED;
	
	/**
	 * Creates a new ship at the default initial location. 
	 * @param screen the game screen
	 */
	public Ship(GameScreen screen){
		this.setLocation((screen.getWidth() - shipWidth)/2,
				screen.getHeight() - shipHeight - Y_OFFSET);
		this.setSize(shipWidth, shipHeight);
	}
	
	/**
	 * Get the default ship width
	 * @return the default ship width
	 */
	public int getShipWidth() {
		return shipWidth;
	}
	
	/**
	 * Get the default ship height
	 * @return the default ship height
	 */
	public int getShipHeight() {
		return shipHeight;
	}
	
	/**
	 * Returns the current ship speed
	 * @return the current ship speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set the current ship speed
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Returns the default ship speed.
	 * @return the default ship speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
	
}
