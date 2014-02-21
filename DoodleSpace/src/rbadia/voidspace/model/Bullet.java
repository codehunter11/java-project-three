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

/**
 * Represents a bullet fired by a ship.
 */
public class Bullet extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private int bulletWidth = 8;
	private int bulletHeight = 8;
	private int speed = 12;

	/**
	 * Creates a new bullet above the ship, centered on it
	 * @param ship
	 */
	public Bullet(Ship ship) {
		this.setLocation(ship.x + ship.width/2 - bulletWidth/2,
				ship.y - bulletHeight);
		this.setSize(bulletWidth, bulletHeight);
	}

	/**
	 * Return the bullet's speed.
	 * @return the bullet's speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set the bullet's speed
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
