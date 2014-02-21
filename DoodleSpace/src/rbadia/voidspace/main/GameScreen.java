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

package rbadia.voidspace.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.ExtraLife;
import rbadia.voidspace.model.HealthBar;
import rbadia.voidspace.model.IndestructibleAsteroid;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.model.SplitAsteroid;
import rbadia.voidspace.model.Star;
import rbadia.voidspace.sounds.SoundManager;

/**
 * Main game screen. Handles all game graphics updates and some of the game logic.
 */
public class GameScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage backBuffer;
	private Graphics2D g2d;

	private static final int NEW_SHIP_DELAY = 500;
	private static final int NEW_ASTEROID_DELAY = 500;
	private static final int NEW_INDESTRUCTIBLE_ASTEROID_DELAY = 500;
	private static final int NEW_SPLIT_ASTEROID_DELAY = 500;
	private static final int NEW_ENEMY_SHIP_DELAY = 500;
	private int extraLifeCounter = 0;

	private long lastShipTime;
	private long lastAsteroidTime;
	private long lastIndestructibleAsteroidTime;
	private long lastSplitAsteroidTime;
	private long lastEnemyShipTime;
	private long lastEnemyBulletTime;
	private long asteroidTimer = 1000;
	private long indestructibleAsteroidTimer = 1000;
	private long splitAsteroidTimer = 1000;
	private long enemyShipTimer = 5000;


	private Rectangle asteroidExplosion;
	private Rectangle shipExplosion;
	private Rectangle enemyShipExplosion;

	private JLabel shipsValueLabel;
	private JLabel scoreValueLabel;

	private Random rand;

	private Font originalFont;
	private Font bigFont;
	private Font biggestFont;

	private GameStatus status;
	private SoundManager soundMan;
	private GraphicsManager graphicsMan;
	private GameLogic gameLogic;

	private boolean newEnemyBullet = false;
	private boolean bossMan = false;
	private boolean bossAtX = true;
	private boolean bossAtWidth = false;

	int counter = 0;

	/**
	 * This method initializes
	 *
	 */
	public GameScreen() {
		super();
		// initialize random number generator
		rand = new Random();

		initialize();

		// init graphics manager
		graphicsMan = new GraphicsManager();

		// init back buffer image
		backBuffer = new BufferedImage(510, 440, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
	}

	/**
	 * Initialization method (for VE compatibility).
	 */
	private void initialize() {
		// set panel properties
		this.setSize(new Dimension(510, 440));
		this.setPreferredSize(new Dimension(510, 400));
		this.setBackground(Color.BLACK);
	}

	/**
	 * Update the game screen.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw current backbuffer to the actual game screen
		g.drawImage(backBuffer, 0, 0, this);
	}

	/**
	 * Update the game screen's backbuffer image.
	 */
	public void updateScreen(){
		Ship ship = gameLogic.getShip();
		Boss boss = gameLogic.getBoss();
		List<EnemyShip> enemyShips = gameLogic.getEnemyShip();
		List<Asteroid> asteroids = gameLogic.getAsteroid();
		List<IndestructibleAsteroid> indestructibleAsteroids = gameLogic.getIndestructibleAsteroid();
		List<SplitAsteroid> splitAsteroids = gameLogic.getSplitAsteroid();
		ExtraLife extraLife = gameLogic.getExtraLife();
		List<Bullet> bullets = gameLogic.getBullets();
		List<EnemyBullet> enemyBullets = gameLogic.getEnemyBullets();

		// set original font - for later use
		if(this.originalFont == null){
			this.originalFont = g2d.getFont();
			this.bigFont = originalFont;
		}

		// erase screen
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);

		// draw 10 random stars
		drawStars(50);

		// if the game is starting, draw "Get Ready" message
		if(status.isGameStarting()){
			drawGetReady();		
			status.setPlayNewLevelSong(true);
			return;
		}

		if(status.isPlayNewLevelSong()){
			soundMan.playGameMusicSound();
			status.setPlayNewLevelSong(false);
		}

		// if the game is over, draw the "Game Over" message
		if(status.isGameOver()){
			// draw the message
			drawGameOver();

			status.setLevel(1);
			status.setScore(0);
			this.bossMan = false;
			this.asteroidTimer = 1000;
			this.indestructibleAsteroidTimer = 1000;
			this.splitAsteroidTimer = 1000;
			this.enemyShipTimer = 5000;
			soundMan.stopGameMusicSound();
			long currentTime = System.currentTimeMillis();
			// draw the explosions until their time passes
			if((currentTime - lastAsteroidTime) < NEW_ASTEROID_DELAY){
				//graphicsMan.drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
			if((currentTime - lastShipTime) < NEW_SHIP_DELAY){
				//graphicsMan.drawShipExplosion(shipExplosion, g2d, this);
			}
			if ((currentTime - lastEnemyShipTime) < NEW_ENEMY_SHIP_DELAY){
				graphicsMan.drawShipExplosion(ship, g2d, this);
			}
			return;
		}

		// the game has not started yet
		if(!status.isGameStarted()){
			// draw game title screen
			initialMessage();
			return;
		}

		// draw ship
		drawShip(ship);
		// draw bullets
		drawBullet(bullets);
		//draw enemy bullets
		drawEnemyBullet(enemyBullets);
		//Check enemy bullet ship collisions
		checkEnemyBulletShipCollision(enemyBullets, ship);
		if (status.getLevel() % 7 != 0){
			//draw asteroids
			drawAsteroid(asteroids);
			if(status.getLevel() >= 2){
				drawIndestructibleAsteroid(indestructibleAsteroids);
			}
			if(status.getLevel() >= 3){
				drawSplitAsteroid(splitAsteroids);
			}        

			if(status.getLevel() >= 4){
				//draw enemy ship
				drawEnemyShip(enemyShips);
				if (status.getLevel() > 4){
					addEnemyShip();
				}
			}
			//Check ship extralife collision
			checkShipExtraLifeCollision(extraLife, ship);
			//Check ship enemy ship collision
			checkShipEnemyShipCollision(ship, enemyShips);
			//Check enemy ship bullet collision
			checkEnemyShipBulletCollision(enemyShips, bullets);
			//check bullet-asteroid collisions
			checkBulletAsteroidCollision(bullets, asteroids); 
			checkBulletSplitAsteroidCollision(bullets, splitAsteroids, asteroids);
			checkBulletIndestructibleAsteroidCollision(bullets, indestructibleAsteroids);
			//check ship-asteroid collisions
			checkShipAsteroidCollision(ship, asteroids);
			checkShipSplitAsteroidCollision(ship, splitAsteroids, asteroids);
			checkShipIndestructibleAsteroidCollision(ship, indestructibleAsteroids);			
			//add asteroid
			addAsteroid();

			if(status.isNewAsteroid()){
				gameLogic.newAsteroid(gameLogic.getGameScreen());
				status.setNewAsteroid(false);
			}

			if(status.isNewSplitAsteroid()){
				gameLogic.newSplitAsteroid(gameLogic.getGameScreen());
				status.setNewSplitAsteroid(false);
			}

			if(!status.isNewLife() && status.getScore() != 0 && status.getScore()%(500*Math.pow(2, extraLifeCounter)) == 0){
				status.setNewLife(true);
				setExtraLifeCounter(getExtraLifeCounter()+1);
			}

			if (status.isNewLife()){
				drawExtraLife(extraLife);                         
			}

			if (status.getScore() != 0 && status.getScore() % (1000*Math.pow(2, status.getLevel() - 1)) == 0){
				newLevel();
				if (status.getLevel() == 7){
					soundMan.playFinalLevelSound();
				}
				else{
					soundMan.playNewLevelSound();
				}
			}
		}

		if (status.getLevel() % 7 == 0){
			drawBoss(boss);
			checkBulletBossCollision(bullets, boss, asteroids, indestructibleAsteroids, enemyShips, splitAsteroids);
			checkShipBossCollision(ship, boss);
		}
	}

	/**
	 * Draws the "Game Over" message.
	 */
	private void drawGameOver() {
		String str = "";
		if (status.getShipsLeft() == 0){
			str = "GAME OVER";

		}
		else{
			str = "YOU WIN!";			
		}
		Font currentFont = biggestFont == null? bigFont : biggestFont;
		float fontSize = currentFont.getSize2D();
		bigFont = currentFont.deriveFont(fontSize + 1).deriveFont(Font.BOLD);
		FontMetrics fm = g2d.getFontMetrics(bigFont);
		int strWidth = fm.stringWidth(str);
		if(strWidth > this.getWidth() - 10){
			biggestFont = currentFont;
			bigFont = biggestFont;
			fm = g2d.getFontMetrics(bigFont);
			strWidth = fm.stringWidth(str);
		}
		int ascent = fm.getAscent();
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2;
		g2d.setFont(bigFont);
		g2d.setPaint(Color.RED);
		g2d.drawString(str, strX, strY);
	}

	/**
	 * Draws the initial "Get Ready!" message.
	 */
	private void drawGetReady() {
		String readyStr = "Get Ready!";
		g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 1));
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();
		int strWidth = fm.stringWidth(readyStr);
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2;
		g2d.setPaint(Color.BLACK);
		g2d.drawString(readyStr, strX, strY);
	}

	/**
	 * Starts the next level.
	 */
	private void newLevel(){
		status.setLevel(status.getLevel() + 1);
		setAsteroidTimer(getAsteroidTimer() - (100/(status.getLevel())));
		for (int i = 0; i < status.getLevel() * 5; i++){
			addAsteroid();
		}
		if (status.getLevel() >= 2){
			indestructibleAsteroidTimer -= (400/status.getLevel());
			for (int i = 0; i < status.getLevel(); i++){
				addIndestructibleAsteroid();
			}
		}
		if (status.getLevel() >= 3){
			splitAsteroidTimer -= (200/status.getLevel());
			for (int i = 0; i < status.getLevel() * 2; i++){
				addSplitAsteroid();
			}
		}
		if (status.getLevel() >= 4){
			setEnemyShipTimer(getEnemyShipTimer() - (700/status.getLevel()));
			for (int i = 0; i < status.getLevel() * 3; i++){
				addEnemyShip();
			}
		}
	}

	/**
	 * Draws the specified number of stars randomly on the game screen.
	 * @param numberOfStars the number of stars to draw
	 */
	private void drawStars(int numberOfStars) {
		g2d.setColor(Color.WHITE);
		for(int i=0; i<numberOfStars; i++){
			int x = (int)(Math.random() * this.getWidth());
			int y = (int)(Math.random() * this.getHeight());
			g2d.drawLine(x, y, x, y);
		}
	}

	/**
	 * Display initial game title screen.
	 */
	private void initialMessage() {
		String gameTitleStr = "DoodleSpace";

		Font currentFont = biggestFont == null? bigFont : biggestFont;
		float fontSize = currentFont.getSize2D();
		bigFont = currentFont.deriveFont(fontSize + 1).deriveFont(Font.BOLD).deriveFont(Font.ITALIC);
		FontMetrics fm = g2d.getFontMetrics(bigFont);
		int strWidth = fm.stringWidth(gameTitleStr);
		if(strWidth > this.getWidth() - 10){
			bigFont = currentFont;
			biggestFont = currentFont;
			fm = g2d.getFontMetrics(currentFont);
			strWidth = fm.stringWidth(gameTitleStr);
		}
		g2d.setFont(bigFont);
		int ascent = fm.getAscent();
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2 - ascent;
		g2d.setPaint(Color.ORANGE);
		g2d.drawString(gameTitleStr, strX, strY);

		g2d.setFont(originalFont);
		fm = g2d.getFontMetrics();
		String newGameStr = "Press <Space> to Start a New Game.";
		strWidth = fm.stringWidth(newGameStr);
		strX = (this.getWidth() - strWidth)/2;
		strY = (this.getHeight() + fm.getAscent())/2 + ascent + 16;
		g2d.setPaint(Color.WHITE);
		g2d.drawString(newGameStr, strX, strY);

		fm = g2d.getFontMetrics();
		String exitGameStr = "Press <Esc> to Exit the Game.";
		strWidth = fm.stringWidth(exitGameStr);
		strX = (this.getWidth() - strWidth)/2;
		strY = strY + 16;
		g2d.drawString(exitGameStr, strX, strY);
	}

	/**
	 * Prepare screen for game over.
	 */
	public void doGameOver(){
		shipsValueLabel.setForeground(new Color(128, 0, 0));
	}

	/**
	 * Prepare screen for a new game.
	 */
	public void doNewGame(){                
		lastAsteroidTime = -NEW_ASTEROID_DELAY;
		lastShipTime = -NEW_SHIP_DELAY;
		lastIndestructibleAsteroidTime = -NEW_INDESTRUCTIBLE_ASTEROID_DELAY;
		lastSplitAsteroidTime = -NEW_SPLIT_ASTEROID_DELAY;

		bigFont = originalFont;
		biggestFont = null;

		// set labels' text
		shipsValueLabel.setForeground(Color.BLACK);
		shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		scoreValueLabel.setText(Long.toString(status.getScore()));
	}

	/**
	 * Sets the game graphics manager.
	 * @param graphicsMan the graphics manager
	 */
	public void setGraphicsMan(GraphicsManager graphicsMan) {
		this.graphicsMan = graphicsMan;
	}

	/**
	 * Sets the game logic handler
	 * @param gameLogic the game logic handler
	 */
	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		this.status = gameLogic.getStatus();
		this.soundMan = gameLogic.getSoundMan();
	}

	/**
	 * Sets the label that displays the value for ship (lives) left
	 * @param shipsValueLabel the label to set
	 */
	public void setShipsValueLabel(JLabel shipsValueLabel) {
		this.shipsValueLabel = shipsValueLabel;
	}

	/**
	 * Sets the label that displays the value for the score.
	 * @param scoreValueLabel the label to set
	 */
	public void setScoreValueLabel(JLabel scoreValueLabel){
		this.scoreValueLabel = scoreValueLabel;
	}

	public void drawEnemyShip(List<EnemyShip> enemyShips){
		// draw EnemyShip
		for (int i = 0; i < enemyShips.size(); i++){
			EnemyShip enemyShip = enemyShips.get(i);
			HealthBar fullHealthBar = new HealthBar(enemyShip);
			HealthBar halfHealthBar = new HealthBar(enemyShip);
			HealthBar lowHealthBar = new HealthBar(enemyShip);
			if(enemyShip.getHealth() == 3){
				graphicsMan.drawFullHealthBar(fullHealthBar, g2d, this);
			}
			if(enemyShip.getHealth() == 2){
				graphicsMan.drawHalfHealthBar(halfHealthBar, g2d, this);
			}
			if(enemyShip.getHealth() == 1){
				graphicsMan.drawLowHealthBar(lowHealthBar, g2d, this);
			}
			if(!status.isNewEnemyShip()){
				setNewEnemyBullet(true);
				// draw the enemy ship until it reaches the bottom of the screen
				if(enemyShip.getY() + enemyShip.getSpeed() < this.getHeight()){
					enemyShip.translate(enemyShip.getDirection(), enemyShip.getSpeed());
					graphicsMan.drawEnemyShip(enemyShip, g2d, this);
				}
				else{
					enemyShip.setLocation(rand.nextInt(getWidth() - enemyShip.width), 0);
				}
			}
			else{
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastEnemyShipTime) > NEW_ENEMY_SHIP_DELAY){
					// draw a new enemy ship
					lastEnemyShipTime = currentTime;
					status.setNewEnemyShip(false);
					setNewEnemyBullet(false);
					enemyShip.setLocation(rand.nextInt(getWidth() - enemyShip.width), 0);
				}
				else{
					//graphicsMan.drawEnemyShipExplosion(enemyShipExplosion, g2d, this);
					setNewEnemyBullet(false);
					status.setNewEnemyShip(false);					
				}
			}
		}                
	}

	public void drawAsteroid(List<Asteroid> asteroids){                
		// draw asteroid
		for (int i = 0; i < asteroids.size(); i++){
			Asteroid asteroid = asteroids.get(i);
			if(!status.isNewAsteroid()){
				// draw the asteroid until it reaches the bottom of the screen
				if(asteroid.getY() + asteroid.getSpeed() < this.getHeight()){
					asteroid.translate(asteroid.getDirection(), asteroid.getSpeed());
					graphicsMan.drawAsteroid(asteroid, g2d, this);
				}
				else{
					asteroid.setLocation(rand.nextInt(getWidth() - asteroid.width), 0);
				}
			}
			else{
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
					// draw a new asteroid
					lastAsteroidTime = currentTime;
					status.setNewAsteroid(false);
					asteroid.setLocation(rand.nextInt(getWidth() - asteroid.width), 0);
				}
				else{
					// draw explosion
					//graphicsMan.drawAsteroidExplosion(asteroid, g2d, this);
				}
			}
		}
	}

	public void drawIndestructibleAsteroid(List<IndestructibleAsteroid> indestructibleAsteroids){                
		// draw asteroid
		for (int i = 0; i < indestructibleAsteroids.size(); i++){
			IndestructibleAsteroid indestructibleAsteroid = indestructibleAsteroids.get(i);
			if(!status.isNewIndestructibleAsteroid()){
				// draw the asteroid until it reaches the bottom of the screen
				if(indestructibleAsteroid.getY() + indestructibleAsteroid.getSpeed() < this.getHeight()){
					indestructibleAsteroid.translate(indestructibleAsteroid.getDirection(), indestructibleAsteroid.getSpeed());
					graphicsMan.drawIndestructibleAsteroid(indestructibleAsteroid, g2d, this);
				}
				else{
					indestructibleAsteroid.setLocation(rand.nextInt(getWidth() - indestructibleAsteroid.width), 0);
				}
			}
			else{
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastIndestructibleAsteroidTime) > NEW_INDESTRUCTIBLE_ASTEROID_DELAY){
					// draw a new asteroid
					lastIndestructibleAsteroidTime = currentTime;
					status.setNewIndestructibleAsteroid(false);
					indestructibleAsteroid.setLocation(rand.nextInt(getWidth() - indestructibleAsteroid.width), 0);
				}				
			}
		}
	}

	public void drawSplitAsteroid(List<SplitAsteroid> splitAsteroids){                
		// draw asteroid
		for (int i = 0; i < splitAsteroids.size(); i++){
			SplitAsteroid splitAsteroid = splitAsteroids.get(i);
			if(!status.isNewSplitAsteroid()){
				// draw the asteroid until it reaches the bottom of the screen
				if(splitAsteroid.getY() + splitAsteroid.getSpeed() < this.getHeight()){
					splitAsteroid.translate(splitAsteroid.getDirection(), splitAsteroid.getSpeed());
					graphicsMan.drawSplitAsteroid(splitAsteroid, g2d, this);
				}
				else{
					splitAsteroid.setLocation(rand.nextInt(getWidth() - splitAsteroid.width), 0);
				}
			}
			else{
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastSplitAsteroidTime) > NEW_SPLIT_ASTEROID_DELAY){
					// draw a new asteroid
					lastSplitAsteroidTime = currentTime;
					status.setNewIndestructibleAsteroid(false);
					splitAsteroid.setLocation(rand.nextInt(getWidth() - splitAsteroid.width), 0);
				}
			}
		}
	}

	public void drawBullet(List<Bullet> bullets){
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			graphicsMan.drawBullet(bullet, g2d, this);

			boolean remove = gameLogic.moveBullet(bullet);
			if(remove){
				bullets.remove(i);
				i--;
			}
		}
	}

	public void drawEnemyBullet(List<EnemyBullet> enemyBullets){
		if (newEnemyBullet){
			if (this.bossMan){				
				if((System.currentTimeMillis() - lastEnemyBulletTime) > 300){
					lastEnemyBulletTime = System.currentTimeMillis();
					gameLogic.fireBossBullet();
				}				
			}
			else{
				if((System.currentTimeMillis() - lastEnemyBulletTime) > 1500){
					lastEnemyBulletTime = System.currentTimeMillis();
					gameLogic.fireEnemyBullet();
				}
			}

			for(int i=0; i<enemyBullets.size(); i++){
				EnemyBullet enemyBullet = enemyBullets.get(i);
				graphicsMan.drawEnemyBullet(enemyBullet, g2d, this);

				boolean remove = gameLogic.moveEnemyBullet(enemyBullet);
				if(remove){
					enemyBullets.remove(i);
					i--;
				}
			}
			setNewEnemyBullet(false);
		}
	}

	public void drawExtraLife(ExtraLife extraLife){

		if(extraLife.getY() + extraLife.getSpeed() < this.getHeight()){
			extraLife.translate(0, extraLife.getSpeed());
			graphicsMan.drawExtraLife(extraLife, g2d, this);
		}
		else{
			status.setNewLife(false);
			gameLogic.newExtraLife(this);
		}

	}

	public void drawShip(Ship ship){
		if(!status.isNewShip()){
			// draw it in its current location
			graphicsMan.drawShip(ship, g2d, this);
		}
		else{
			// draw a new one
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastShipTime) > NEW_SHIP_DELAY){
				lastShipTime = currentTime;
				status.setNewShip(false);
				ship = gameLogic.newShip(this);
			}
			else{
				// draw explosion
				graphicsMan.drawShipExplosion(shipExplosion, g2d, this);
			}
		}
	}

	public void drawBoss(Boss boss){

		HealthBar fullHealthBar = new HealthBar(boss);
		HealthBar halfHealthBar = new HealthBar(boss);
		HealthBar lowHealthBar = new HealthBar(boss);
		if(boss.getHealth() >= 50){
			graphicsMan.drawFullHealthBar(fullHealthBar, g2d, this);
		}
		if(boss.getHealth() >= 20 && boss.getHealth() < 50){
			graphicsMan.drawHalfHealthBar(halfHealthBar, g2d, this);
		}
		if(boss.getHealth() > 0 && boss.getHealth() < 20){
			graphicsMan.drawLowHealthBar(lowHealthBar, g2d, this);
		}
		if(!status.isNewBoss()){
			setNewEnemyBullet(true);
			this.bossMan = true;
			// draw the enemy ship until it reaches the bottom of the screen
			if(boss.getX() + boss.getWidth() == this.getWidth()){
				//boss.translate(1, 0);
				this.bossAtWidth = true;
				this.bossAtX = false;
			}
			if (boss.getX() == 0){
				this.bossAtX = true;
				this.bossAtWidth = false;
			}
			if(bossAtX){
				boss.translate(1, 0);
				graphicsMan.drawBoss(boss, g2d, this);
			}
			if(bossAtWidth){
				boss.translate(-1, 0);
				graphicsMan.drawBoss(boss, g2d, this);
			}
		}
		else{
			boss.setLocation(rand.nextInt(getWidth() - boss.width), 0);
		}
	}

	public void checkShipBossCollision(Ship ship, Boss boss){

		// check ship-asteroid collisions
		if(boss.intersects(ship)){
			// decrease number of ships left
			status.setShipsLeft(status.getShipsLeft() - 1);
			// "remove" ship
			shipExplosion = new Rectangle(
					ship.x,
					ship.y,
					ship.width,
					ship.height);
			ship.setLocation(this.getWidth() + ship.width, -ship.height);
			status.setNewShip(true);
			lastShipTime = System.currentTimeMillis();

			// play ship explosion sound
			soundMan.playShipExplosionSound();
			// play asteroid explosion sound
			soundMan.playAsteroidExplosionSound();
		}

		// update ships left label
		shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));              	
	}

	public void checkShipExtraLifeCollision(ExtraLife extraLife, Ship ship){
		//check ship extra life collision
		if (extraLife.intersects(ship)){
			int temp = status.getShipsLeft();
			temp++;
			status.setShipsLeft(temp);
			extraLife.setLocation(-extraLife.width, -extraLife.height);
			//play extra life sound
			soundMan.playExtraLifeSound();
			status.setNewLife(false);
			gameLogic.newExtraLife(this);
		}                
	}

	public void checkEnemyBulletShipCollision(List<EnemyBullet> enemyBullets, Ship ship){
		for (int i = 0; i < enemyBullets.size(); i++){
			EnemyBullet enemyBullet = enemyBullets.get(i);
			if(ship.intersects(enemyBullet)){
				status.setShipsLeft(status.getShipsLeft()-1);

				// "remove" ship
				shipExplosion = new Rectangle(
						ship.x,
						ship.y,
						ship.width,
						ship.height);
				ship.setLocation(this.getWidth() + ship.width, -ship.height);
				status.setNewShip(true);
				lastShipTime = System.currentTimeMillis();

				// play ship explosion sound
				soundMan.playShipExplosionSound();

				//remove enemy ship
				enemyBullets.remove(i);
			}
			// update ships left label
			shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		}
	}

	public void checkBulletAsteroidCollision(List<Bullet> bullets, List<Asteroid> asteroids){
		for(int i=0; i<asteroids.size(); i++){
			for (int j = 0; j < bullets.size(); j++){                                
				Bullet bullet = bullets.get(j);
				Asteroid asteroid = asteroids.get(i);

				if(asteroid.intersects(bullet)){
					// increase the score
					status.setScore(status.getScore() + 100);

					// "remove" asteroid
					asteroidExplosion = new Rectangle(
							asteroid.x,
							asteroid.y,
							asteroid.width,
							asteroid.height);
					asteroid.setLocation(-asteroid.width, -asteroid.height);
					status.setNewAsteroid(true);
					lastAsteroidTime = System.currentTimeMillis();


					// play asteroid explosion sound
					soundMan.playAsteroidExplosionSound();

					// remove bullet
					asteroids.remove(i);
					bullets.remove(j);                                        
					break;
				}                                
			}                        
		}
	}

	public void checkBulletBossCollision(List<Bullet> bullets, Boss boss, List<Asteroid> asteroids, 
			List<IndestructibleAsteroid> indestructibleAsteroids, List<EnemyShip> enemyShips, 
			List<SplitAsteroid> splitAsteroids){
		for (int j = 0; j < bullets.size(); j++){
			Bullet bullet = bullets.get(j);
			if(boss.intersects(bullet)){
				if (boss.getHealth() == 1){
					//increase score
					status.setScore(status.getScore() + 400);
					// "remove" enemy ship
					asteroidExplosion = new Rectangle(
							boss.x,
							boss.y,
							boss.width,
							boss.height);
					boss.setLocation(-boss.width, -boss.height);
					//remove enemy ship and bullets
					bullets.remove(j);
					status.setNewBoss(true);
					asteroids.clear();
					enemyShips.clear();
					indestructibleAsteroids.clear();
					splitAsteroids.clear();
					addAsteroid();
					addEnemyShip();
					addIndestructibleAsteroid();
					addSplitAsteroid();
					newLevel();
				}
				else{
					boss.setHealth(boss.getHealth()-1);
					bullets.remove(j);
				}
			}
		}
	}

	public void checkBulletIndestructibleAsteroidCollision(List<Bullet> bullets, List<IndestructibleAsteroid> indestructibleAsteroids){
		for(int i=0; i<indestructibleAsteroids.size(); i++){
			for (int j = 0; j < bullets.size(); j++){                                
				Bullet bullet = bullets.get(j);
				IndestructibleAsteroid indestructibleAsteroid = indestructibleAsteroids.get(i);

				if(indestructibleAsteroid.intersects(bullet)){
					bullets.remove(j);                                        
					break;
				}                                
			}                        
		}
	}

	public void checkBulletSplitAsteroidCollision(List<Bullet> bullets, List<SplitAsteroid> splitAsteroids, List<Asteroid> asteroids){
		for(int i=0; i<splitAsteroids.size(); i++){
			for (int j = 0; j < bullets.size(); j++){ 
				Bullet bullet = bullets.get(j);
				SplitAsteroid splitAsteroid = splitAsteroids.get(i);
				Asteroid asteroid = asteroids.get(i);

				if(splitAsteroid.intersects(bullet)){
					// increase the score
					status.setScore(status.getScore() + 100);

					// "remove" asteroid
					asteroidExplosion = new Rectangle(
							splitAsteroid.x,
							splitAsteroid.y,
							splitAsteroid.width,
							splitAsteroid.height);
					asteroid.setLocation(splitAsteroid.x, splitAsteroid.y);
					asteroid.setDirection(asteroid.getDirection());
					splitAsteroid.setLocation(-splitAsteroid.width, -splitAsteroid.height);
					status.setNewSplitAsteroid(true);
					lastSplitAsteroidTime = System.currentTimeMillis();

					// play asteroid explosion sound
					soundMan.playAsteroidExplosionSound();

					// remove bullet
					splitAsteroids.remove(i);
					bullets.remove(j);                                        
					break;
				}                                
			}  
		}                   
	}

	public void checkEnemyShipBulletCollision(List<EnemyShip> enemyShips, List<Bullet> bullets){
		for (int i = 0; i < enemyShips.size(); i++){
			for (int j = 0; j < bullets.size(); j++){
				Bullet bullet = bullets.get(j);
				EnemyShip enemyShip = enemyShips.get(i);

				if(enemyShip.intersects(bullet)){
					if (enemyShip.getHealth() == 1){
						//increase score
						status.setScore(status.getScore() + 200);
						// "remove" enemy ship
						enemyShipExplosion = new Rectangle(
								enemyShip.x,
								enemyShip.y,
								enemyShip.width,
								enemyShip.height);
						enemyShip.setLocation(-enemyShip.width, -enemyShip.height);
						lastEnemyShipTime = System.currentTimeMillis();
						//remove enemy ship and bullets
						enemyShip.setShipDestroyed(true);
						enemyShips.remove(i);
						bullets.remove(j);
						break;                                                
					}
					else{
						enemyShip.setHealth(enemyShip.getHealth()-1);
						bullets.remove(j);
					}
				}
			}
		}
	}

	public void checkShipEnemyShipCollision(Ship ship, List<EnemyShip> enemyShips){
		for (int i = 0; i < enemyShips.size(); i++){
			EnemyShip enemyShip = enemyShips.get(i);
			if(ship.intersects(enemyShip)){
				status.setShipsLeft(status.getShipsLeft()-1);

				//"remove" Enemy Ship
				enemyShipExplosion = new Rectangle(enemyShip.x, enemyShip.y, enemyShip.width, enemyShip.height);
				enemyShip.setLocation(-enemyShip.width, -enemyShip.height);
				status.setNewEnemyShip(true);
				lastEnemyShipTime = System.currentTimeMillis();

				// "remove" ship
				shipExplosion = new Rectangle(
						ship.x,
						ship.y,
						ship.width,
						ship.height);
				ship.setLocation(this.getWidth() + ship.width, -ship.height);
				status.setNewShip(true);
				lastShipTime = System.currentTimeMillis();

				// play ship explosion sound
				soundMan.playShipExplosionSound();

				//remove enemy ship
				enemyShips.remove(i);
			}

			// update ships left label
			shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));

		}
	}

	public void checkShipAsteroidCollision(Ship ship, List<Asteroid> asteroids){                
		for (int i = 0; i < asteroids.size(); i++){
			Asteroid asteroid = asteroids.get(i);
			// check ship-asteroid collisions
			if(asteroid.intersects(ship)){
				// decrease number of ships left
				status.setShipsLeft(status.getShipsLeft() - 1);

				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 1);

				// "remove" asteroid
				asteroidExplosion = new Rectangle(
						asteroid.x,
						asteroid.y,
						asteroid.width,
						asteroid.height);
				asteroid.setLocation(-asteroid.width, -asteroid.height);
				status.setNewAsteroid(true);
				lastAsteroidTime = System.currentTimeMillis();

				// "remove" ship
				shipExplosion = new Rectangle(
						ship.x,
						ship.y,
						ship.width,
						ship.height);
				ship.setLocation(this.getWidth() + ship.width, -ship.height);
				status.setNewShip(true);
				lastShipTime = System.currentTimeMillis();

				// play ship explosion sound
				soundMan.playShipExplosionSound();
				// play asteroid explosion sound
				soundMan.playAsteroidExplosionSound();
				//remove asteroid
				asteroids.remove(i);
			}

			// update asteroids destroyed label
			scoreValueLabel.setText(Long.toString(status.getScore()));

			// update ships left label
			shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		}                
	}

	public void checkShipIndestructibleAsteroidCollision(Ship ship, List<IndestructibleAsteroid> indestructibleAsteroids){                
		for (int i = 0; i < indestructibleAsteroids.size(); i++){
			IndestructibleAsteroid indestructibleAsteroid = indestructibleAsteroids.get(i);
			// check ship-asteroid collisions
			if(indestructibleAsteroid.intersects(ship)){
				// decrease number of ships left
				status.setShipsLeft(status.getShipsLeft() - 1);
				lastIndestructibleAsteroidTime = System.currentTimeMillis();

				// "remove" ship
				shipExplosion = new Rectangle(
						ship.x,
						ship.y,
						ship.width,
						ship.height);
				ship.setLocation(this.getWidth() + ship.width, -ship.height);
				status.setNewShip(true);
				lastShipTime = System.currentTimeMillis();

				// play ship explosion sound
				soundMan.playShipExplosionSound();
				// play asteroid explosion sound
				soundMan.playAsteroidExplosionSound();
			}

			// update ships left label
			shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		}                
	}

	public void checkShipSplitAsteroidCollision(Ship ship, List<SplitAsteroid> splitAsteroids, List<Asteroid> asteroids){                
		for (int i = 0; i < splitAsteroids.size(); i++){
			SplitAsteroid splitAsteroid = splitAsteroids.get(i);
			Asteroid asteroid = asteroids.get(i);
			// check ship-asteroid collisions
			if(splitAsteroid.intersects(ship)){
				// decrease number of ships left
				status.setShipsLeft(status.getShipsLeft() - 1);

				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 1);

				// "remove" asteroid
				asteroidExplosion = new Rectangle(
						splitAsteroid.x,
						splitAsteroid.y,
						splitAsteroid.width,
						splitAsteroid.height);
				asteroid.setLocation(splitAsteroid.x, splitAsteroid.y);
				asteroid.setDirection(asteroid.getDirection());
				splitAsteroid.setLocation(-splitAsteroid.width, -splitAsteroid.height);
				status.setNewAsteroid(true);
				lastSplitAsteroidTime = System.currentTimeMillis();

				// "remove" ship
				shipExplosion = new Rectangle(
						ship.x,
						ship.y,
						ship.width,
						ship.height);
				ship.setLocation(this.getWidth() + ship.width, -ship.height);
				status.setNewShip(true);
				lastShipTime = System.currentTimeMillis();

				// play ship explosion sound
				soundMan.playShipExplosionSound();
				// play asteroid explosion sound
				soundMan.playAsteroidExplosionSound();
				//remove asteroid
				asteroids.remove(i);
			}

			// update asteroids destroyed label
			scoreValueLabel.setText(Long.toString(status.getScore()));

			// update ships left label
			shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		}                
	}

	public void addAsteroid(){
		if (System.currentTimeMillis() - lastAsteroidTime >= asteroidTimer){
			lastAsteroidTime = System.currentTimeMillis();
			gameLogic.newAsteroid(gameLogic.getGameScreen());
		}
	}

	public void addEnemyShip(){
		if (System.currentTimeMillis() - lastEnemyShipTime >= enemyShipTimer){
			lastEnemyShipTime = System.currentTimeMillis();
			gameLogic.newEnemyShip(gameLogic.getGameScreen());
		}
	}
	
	public void addSplitAsteroid(){
		if (System.currentTimeMillis() - lastSplitAsteroidTime >= splitAsteroidTimer){
			lastSplitAsteroidTime = System.currentTimeMillis();
			gameLogic.newSplitAsteroid(gameLogic.getGameScreen());
		}
	}
	
	public void addIndestructibleAsteroid(){
		if (System.currentTimeMillis() - lastIndestructibleAsteroidTime >= indestructibleAsteroidTimer){
			lastIndestructibleAsteroidTime = System.currentTimeMillis();
			gameLogic.newIndestructibleAsteroid(gameLogic.getGameScreen());
		}
	}

	public void setNewEnemyBullet(boolean newEnemyBullet){
		this.newEnemyBullet = newEnemyBullet;
	}

	/**
	 * The value for the enemy ship timer.
	 * Getter and setter.
	 * @param enemyShipTimer
	 */

	public void setEnemyShipTimer(long enemyShipTimer){
		this.enemyShipTimer = enemyShipTimer;
	}

	public long getEnemyShipTimer(){
		return enemyShipTimer;
	}

	/**
	 * The value for the asteroid timer.
	 * Getter and setter.
	 * @param asteroidTimer
	 */

	public void setAsteroidTimer(long asteroidTimer){
		this.asteroidTimer = asteroidTimer;
	}

	public long getAsteroidTimer(){
		return asteroidTimer;
	}

	/**
	 * The value for the extra life counter.
	 * Getter and setter.
	 * @return extraLifeCounter
	 */

	public int getExtraLifeCounter(){
		return extraLifeCounter;
	}

	public void setExtraLifeCounter(int extraLifeCounter){
		this.extraLifeCounter = extraLifeCounter;
	}
}