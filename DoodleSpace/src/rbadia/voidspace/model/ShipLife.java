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
 * Represents the life of a ship
 */
public class ShipLife extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private static int shipLifeWidth = 32;
	private static int shipLifeHeight = 32;
	
	/**
	 * Creates a new enemy bullet above the enemy ship, centered on it
	 * @param ship
	 */
	public ShipLife(int x, int y) {
		this.setLocation(x, y);
		this.setSize(shipLifeWidth, shipLifeHeight);
	}
	
	public static int getShipLifeSize() {
		return shipLifeHeight;
	}
}