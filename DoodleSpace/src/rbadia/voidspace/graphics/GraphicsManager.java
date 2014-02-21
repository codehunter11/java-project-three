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

package rbadia.voidspace.graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.ExtraLife;
import rbadia.voidspace.model.IndestructibleAsteroid;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.model.SplitAsteroid;

/**
 * Manages and draws game graphics and images.
 */
public class GraphicsManager {
	private BufferedImage shipImg;
	private BufferedImage enemyShipImg;
	private BufferedImage bulletImg;
	private BufferedImage asteroidImg;
	private BufferedImage asteroidExplosionImg;
	private BufferedImage shipExplosionImg;
	private BufferedImage extraLifeImg;
	private BufferedImage fullHealthImg;
	private BufferedImage halfHealthImg;
	private BufferedImage lowHealthImg;
	private BufferedImage indestructibleAsteroidImg;
	private BufferedImage splitAsteroidImg;
	private BufferedImage bossManImg;
	private BufferedImage enemyBulletImg;
	
	/**
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
    	// load images
		try {
			this.shipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/ship.png"));
			this.enemyShipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/enemy.png"));
			this.asteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroid.png"));
			this.asteroidExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroidExplosion.png"));
			this.shipExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/shipExplosion.png"));
			this.bulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bullet.png"));
			this.extraLifeImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/oneUp.png"));
			this.enemyBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bullet.png"));
			this.fullHealthImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/FullHealth.png"));
			this.halfHealthImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/HalfHealth.png"));
			this.lowHealthImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/lowHealth.png"));
			this.indestructibleAsteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/indestructibleAsteroid.png"));
			this.splitAsteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/splitAsteroid.png"));
			this.bossManImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bossMan.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"VoidSpace - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Draws the boss to the specified graphics canvas.
	 * @param boss the boss to draw.
	 * @param g2d the graphics canvas.
	 * @param observer object to be notified.
	 */
	public void drawBoss(Boss boss, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(bossManImg, boss.x, boss.y, observer);
	}

	/**
	 * Draws a ship image to the specified graphics canvas.
	 * @param ship the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShip(Ship ship, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipImg, ship.x, ship.y, observer);
	}
	
	/**
	 * Draws a enemy ship image to the specified graphics canvas.
	 * @param enemy ship the enemy ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyShip(EnemyShip enemyShip, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(enemyShipImg, enemyShip.x, enemyShip.y, observer);
	}
	
	/**
	 * Draws an indestructible asteroid image to the specified graphics canvas.
	 * @param indestructibleAsteroid the indestructible asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawIndestructibleAsteroid(IndestructibleAsteroid indestructibleAsteroid, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(indestructibleAsteroidImg, indestructibleAsteroid.x, indestructibleAsteroid.y, observer);
	}
	
	/**
	 * Draws an asteroid that breaks into multiple asteroids to the specified graphics canvas.
	 * @param splitAsteroid the enemy ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawSplitAsteroid(SplitAsteroid splitAsteroid, Graphics2D g2d, ImageObserver observer){
		g2d.drawImage(splitAsteroidImg, splitAsteroid.x, splitAsteroid.y, observer);
	}

	/**
	 * Draws a bullet image to the specified graphics canvas.
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bulletImg, bullet.x, bullet.y, observer);
	}
	
	/**
	 * Draws a enemy bullet image to the specified graphics canvas.
	 * @param enemy bullet the enemy bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyBullet(EnemyBullet enemyBullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bulletImg, enemyBullet.x, enemyBullet.y, observer);
	}
	
	/**
	 * Draws an extra life image to the specified graphics canvas.
	 * @param extra life the extra life to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawExtraLife(ExtraLife extraLife, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(extraLifeImg, extraLife.x, extraLife.y, observer);
	}

	/**
	 * Draws an asteroid image to the specified graphics canvas.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidImg, asteroid.x, asteroid.y, observer);
	}

	/**
	 * Draws a ship explosion image to the specified graphics canvas.
	 * @param shipExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShipExplosion(Rectangle shipExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipExplosionImg, shipExplosion.x, shipExplosion.y, observer);
	}
	
	/**
	 * Draws a enemy ship explosion image to the specified graphics canvas.
	 * @param enemy shipExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyShipExplosion(Rectangle enemyShipExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipExplosionImg, enemyShipExplosion.x, enemyShipExplosion.y, observer);
	}

	/**
	 * Draws an asteroid explosion image to the specified graphics canvas.
	 * @param asteroidExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidExplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}
	
	/**
	 * Draws a full health image to the specified graphics canvas.
	 * @param fullHealthImg the health bar of enemy ship.
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawFullHealthBar(Rectangle fullHealthBar, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(fullHealthImg, fullHealthBar.x, fullHealthBar.y, observer);
	}
	
	/**
	 * Draws a half health image to the specified graphics canvas.
	 * @param halfHealthImg the health bar of enemy ship.
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawHalfHealthBar(Rectangle halfHealthBar, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(halfHealthImg, halfHealthBar.x, halfHealthBar.y, observer);
	}
	
	/**
	 * Draws a low health image to the specified graphics canvas.
	 * @param lowHealthImg the health bar of enemy ship.
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawLowHealthBar(Rectangle lowHealthBar, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(lowHealthImg, lowHealthBar.x, lowHealthBar.y, observer);
	}
	
}
