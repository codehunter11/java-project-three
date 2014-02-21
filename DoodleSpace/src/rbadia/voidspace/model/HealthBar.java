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

public class HealthBar extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int healthBarWidth = 48;
	private int healthBarHeight = 12;

	public HealthBar(EnemyShip enemyShip){
		this.setLocation(enemyShip.x + enemyShip.width/2 - healthBarWidth/2,
				enemyShip.y + healthBarHeight - 25);
		this.setSize(healthBarWidth, healthBarHeight);
	}
	
	public HealthBar(Boss boss){
		this.setLocation(boss.x + boss.width/2 - healthBarWidth/2, boss.y + healthBarHeight - 25);
		this.setSize(healthBarWidth, healthBarHeight);
	}
}

