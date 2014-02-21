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

public class EnemyShip extends Rectangle {
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_SPEED = 2;
	private Random rand = new Random();
	private int enemyShipWidth = 48;
	private int enemyShipHeight = 48;
	private int health = 3;
	private int direction;
	private int directionValue = rand.nextInt(4);
	private int speed = DEFAULT_SPEED;
	private boolean shipDestroyed = false;
	
	/**
	 * Creates a new ship at the default initial location. 
	 * @param screen the game screen
	 */
	public EnemyShip(GameScreen screen){
		this.setLocation((screen.getWidth() - enemyShipWidth)/2,
				screen.getHeight() + enemyShipHeight);
		this.setSize(enemyShipWidth, enemyShipHeight);	
		if (directionValue == 0){
			this.direction = directionValue;
		}
		else if (directionValue %2 == 0){
			this.direction = rand.nextInt(2)+1;
		}
		else{
			this.direction = -rand.nextInt(2)+1;
		}
	}
	
	/**
	 * Get the default ship width
	 * @return the default ship width
	 */
	public int getEnemyShipWidth() {
		return enemyShipWidth;
	}
	
	/**
	 * Get the default ship height
	 * @return the default ship height
	 */
	public int getEnemyShipHeight() {
		return enemyShipHeight;
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
	
	/**
	 * Returns the default ship health.
	 * @return the default ship health.
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * Set the enemy ship health.
	 * @param health the health to set.
	 */
	public void setHealth(int health){
		this.health = health;
	}
	
	/**
	 * Returns the ship direction.
	 * @return the default ship direction.
	 */
	public int getDirection(){
		return direction;
	}
	
	/**
	 * Set the enemy ship direction.
	 * @param direction the direction to set.
	 */
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public boolean getShipDestroyed(){
		return shipDestroyed;
	}

	public void setShipDestroyed(boolean shipDestroyed)
	{
		this.shipDestroyed = shipDestroyed;
	}
}
