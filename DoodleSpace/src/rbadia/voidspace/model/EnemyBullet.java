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
import java.util.List;

public class EnemyBullet extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bulletWidth = 8;
	private int bulletHeight = 8;
	private int speed = -4;

	public EnemyBullet(List<EnemyShip> enemyShips) {
		for (int i = 0; i < enemyShips.size(); i++){
			EnemyShip enemyShip = enemyShips.get(i);
			this.setLocation(enemyShip.x + enemyShip.width/2 - bulletWidth/2,
					enemyShip.y + bulletHeight + 10);
			this.setSize(bulletWidth, bulletHeight);
		}		
	}
	
	public EnemyBullet(Boss boss){
		this.setLocation(boss.x + boss.width/2 - bulletWidth/2, boss.y + bulletHeight + 80);
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
