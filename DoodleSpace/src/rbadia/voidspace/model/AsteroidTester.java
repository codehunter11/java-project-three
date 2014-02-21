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

import rbadia.voidspace.main.*;

public class AsteroidTester {

	public static void main(String[] args){
		GameScreen screen = new GameScreen();
		EnemyShip asteroid1 = new EnemyShip(screen);
		EnemyShip asteroid2 = new EnemyShip(screen);
		
		System.out.println(asteroid1.getDirection());
		System.out.println(asteroid2.getDirection());
		System.out.println(0%2);
		
	}
	
}
