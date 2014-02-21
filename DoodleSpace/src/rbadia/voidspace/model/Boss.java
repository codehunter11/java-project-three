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

public class Boss extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int bossWidth = 96;
	private int bossHeight = 96;
	private int direction = 0;
	private int health = 100;
	private int speed = 1;
	
	public Boss(GameScreen screen){
		this.setLocation(
				(screen.getWidth() - bossWidth)/2,
        		15);
		this.setSize(bossWidth, bossHeight);		
	}

	public int getBossWidth() {
		return bossWidth;
	}

	public void setBossWidth(int bossWidth) {
		this.bossWidth = bossWidth;
	}

	public int getBossHeight() {
		return bossHeight;
	}

	public void setBossHeight(int bossHeight) {
		this.bossHeight = bossHeight;
	}

	public int getDirection() {
		return direction;
	}

	public void setSpeed(int direction) {
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSpeed() {
		return speed;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int moveRight(){
		return direction;
	}
	
	public int moveLeft(){
		return -direction;
	}
	
}
