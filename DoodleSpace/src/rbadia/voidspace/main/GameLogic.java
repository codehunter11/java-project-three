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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.ExtraLife;
import rbadia.voidspace.model.IndestructibleAsteroid;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.model.SplitAsteroid;
import rbadia.voidspace.model.Star;
import rbadia.voidspace.sounds.SoundManager;


/**
* Handles general game logic and status.
*/
public class GameLogic {
        private GameScreen gameScreen;
        private GameStatus status;
        private SoundManager soundMan;

        private Ship ship;
        private Boss boss;
        private Star star;
        private List<EnemyShip> enemyShip;
        private List<Asteroid> asteroid;
        private List<IndestructibleAsteroid> indestructibleAsteroid;
        private List<SplitAsteroid> splitAsteroid;
        private ExtraLife extraLife;
        private List<Bullet> bullets;
        private List<EnemyBullet> enemyBullets;

        /**
         * Create a new game logic handler
         * @param gameScreen the game screen
         */
        public GameLogic(GameScreen gameScreen){
                this.gameScreen = gameScreen;

                // initialize game status information
                status = new GameStatus();
                // initialize the sound manager
                soundMan = new SoundManager();

                // init some variables
                bullets = new ArrayList<Bullet>();
                enemyBullets = new ArrayList<EnemyBullet>();
        }

        /**
         * Returns the game status
         * @return the game status
         */
        public GameStatus getStatus() {
                return status;
        }

        public SoundManager getSoundMan() {
                return soundMan;
        }

        public GameScreen getGameScreen() {
                return gameScreen;
        }

        /**
         * Prepare for a new game.
         */
        public void newGame(){
                status.setGameStarting(true);

                // init game variables
                bullets = new ArrayList<Bullet>();
                asteroid = new ArrayList<Asteroid>();
                indestructibleAsteroid = new ArrayList<IndestructibleAsteroid>();
                splitAsteroid = new ArrayList<SplitAsteroid>();
                enemyShip = new ArrayList<EnemyShip>();
                enemyBullets = new ArrayList<EnemyBullet>();

                status.setShipsLeft(3);
                status.setGameOver(false);
                status.setAsteroidsDestroyed(0);
                //status.setNewAsteroid(false);

                // init the ship and the asteroid
                newShip(gameScreen);
                newBoss(gameScreen);
                newAsteroid(gameScreen);
                newIndestructibleAsteroid(gameScreen);
                newSplitAsteroid(gameScreen);
                newExtraLife(gameScreen);
                newEnemyShip(gameScreen);
                newStar(gameScreen);

                // prepare game screen
                gameScreen.doNewGame();

                // delay to display "Get Ready" message for 1.5 seconds
                Timer timer = new Timer(1500, new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                                status.setGameStarting(false);
                                status.setGameStarted(true);
                        }
                });
                timer.setRepeats(false);
                timer.start();
        }

        /**
         * Check game or level ending conditions.
         */
        public void checkConditions(){
                // check game over conditions
                if(!status.isGameOver() && status.isGameStarted()){
                        if(status.getShipsLeft() == 0){
                                gameOver();
                        }
                }
        }

        /**
         * Actions to take when the game is over.
         */
        public void gameOver(){
                status.setGameStarted(false);
                status.setGameOver(true);
                gameScreen.doGameOver();

                // delay to display "Game Over" message for 3 seconds
                Timer timer = new Timer(3000, new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                                status.setGameOver(false);
                        }
                });
                timer.setRepeats(false);
                timer.start();
        }

        /**
         * Fire a bullet from ship.
         */
        public void fireBullet(){
                Bullet bullet = new Bullet(ship);
                bullets.add(bullet);
                soundMan.playBulletSound();
        }
        
        /**
         * Fire enemy bullets from ship.
         */
        public void fireEnemyBullet(){
                EnemyBullet enemyBullet = new EnemyBullet(enemyShip);
                enemyBullets.add(enemyBullet);
                //TODO add enemy bullet sound.
        }
        
        /**
         * Fire enemy Bullets from boss.
         */
        public void fireBossBullet(){
        	EnemyBullet enemyBullet = new EnemyBullet(boss);
        	EnemyBullet enemyBullet2 = new EnemyBullet(boss);
        	enemyBullet2.setLocation(boss.x + 35 - enemyBullet2.width/2, boss.y + enemyBullet2.height + 80);
        	EnemyBullet enemyBullet3 = new EnemyBullet(boss);
        	enemyBullet3.setLocation(boss.x + boss.width - 35 - enemyBullet3.width/2, boss.y + enemyBullet3.height + 80);
        	enemyBullets.add(enemyBullet);
        	enemyBullets.add(enemyBullet2);
        	enemyBullets.add(enemyBullet3);
        }
        
        /**
         * Move an enemy bullet once fired.
         * @param enemy bullet the enemy bullet to move.
         * @return if the enemy bullet should be removed from screen.
         */
        public boolean moveEnemyBullet(EnemyBullet enemyBullet){
                if(enemyBullet.getY() - enemyBullet.getSpeed() >= 0){
                        enemyBullet.translate(0, -enemyBullet.getSpeed());
                        return false;
                }
                else{
                        return true;
                }
        }

        /**
         * Move a bullet once fired.
         * @param bullet the bullet to move
         * @return if the bullet should be removed from screen
         */
        public boolean moveBullet(Bullet bullet){
                if(bullet.getY() - bullet.getSpeed() >= 0){
                        bullet.translate(0, -bullet.getSpeed());
                        return false;
                }
                else{
                        return true;
                }
        }

        /**
         * Create a new ship (and replace current one).
         */
        public Ship newShip(GameScreen screen){
                this.ship = new Ship(screen);
                return ship;
        }
        
        /**
         * Create a new star.
         */
        public Star newStar(GameScreen screen){
        	this.star = new Star(screen);
        	return star;
        }
        
        /**
         * Create a new boss.
         */
        public Boss newBoss(GameScreen screen){
        	this.boss = new Boss(screen);
        	return boss;
        }

        /**
         * Create a new asteroid.
         */        
        public void newAsteroid(GameScreen screen){
                asteroid.add(new Asteroid(screen));
        }
        
        /**
         * Create a new indestructible asteroid.
         */        
        public void newIndestructibleAsteroid(GameScreen screen){
                indestructibleAsteroid.add(new IndestructibleAsteroid(screen));
        }
        
        /**
         * Create a new split asteroid.
         */        
        public void newSplitAsteroid(GameScreen screen){
                splitAsteroid.add(new SplitAsteroid(screen));
        }
        
        /**
         * Create a new enemy ship.
         */
        public void newEnemyShip(GameScreen screen){
                enemyShip.add(new EnemyShip(screen));
        }
        
        /**
         * Create a new life*/
        public ExtraLife newExtraLife(GameScreen screen){
                this.extraLife = new ExtraLife(screen);
                return extraLife;
        }

        /**
         * Returns the ship.
         * @return the ship
         */
        public Ship getShip() {
                return ship;
        }
        
        /**
         * Returns the boss.
         * @return the boss.
         */
        public Boss getBoss(){
        	return boss;
        }
        
        /**
         * Returns the star.
         */
        public Star getStar(){
        	return star;
        }
        
        /**
         * Returns the enemy ship.
         * @return the enemy ship.
         */
        public List<EnemyShip> getEnemyShip(){
                return enemyShip;
        }

        /**
         * Returns the asteroid.
         * @return the asteroid
         */
        public List<Asteroid> getAsteroid() {
                return asteroid;
        }
        
        /**
         * Returns the indestructible asteroid.
         * @return the indestructible asteroid
         */
        public List<IndestructibleAsteroid> getIndestructibleAsteroid() {
                return indestructibleAsteroid;
        }        
        
        /**
         * Returns the split asteroid.
         * @return the split asteroid
         */
        public List<SplitAsteroid> getSplitAsteroid() {
                return splitAsteroid;
        }
        
        
        public ExtraLife getExtraLife(){
                return extraLife;
        }
        
        /**
         * Returns the list of enemy bullets.
         * @return the list of enemy bullets.
         */
        public List<EnemyBullet> getEnemyBullets(){
                return enemyBullets;
        }

        /**
         * Returns the list of bullets.
         * @return the list of bullets
         */
        public List<Bullet> getBullets() {
                return bullets;
        }
}