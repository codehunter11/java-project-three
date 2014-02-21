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

public class Star extends Rectangle {
	
	private int asteroidWidth = 32;
	private int asteroidHeight = 32;
	private Random rand = new Random();

	public Star(GameScreen screen) {
		this.setLocation(
        		rand.nextInt(screen.getWidth() - asteroidWidth),
        		rand.nextInt(screen.getHeight() - asteroidHeight));
		this.setSize(asteroidWidth, asteroidHeight);
	}

}
