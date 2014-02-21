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

/**
* Container for game flags and/or status variables.
*/
public class GameStatus {
        // game flags
        private boolean gameStarted = false;
        private boolean gameStarting = false;
        private boolean gameOver = false;
        
        // status variables
        private boolean newShip;
        private boolean newBoss;
        private boolean newLevel = false;
        private boolean newAsteroid;
        private boolean newIndestructibleAsteroid = false;
        private boolean newSplitAsteroid = false;
        private boolean newLife;
        private boolean newEnemyShip;
        private boolean playNewLevelSong;
        private long score = 0;
        private int level = 1;
        private long asteroidsDestroyed = 0;
        private int shipsLeft;
        
        /**
         * Indicates if the game has already started or not.
         * @return if the game has already started or not
         */
        public synchronized boolean isGameStarted() {
                return gameStarted;
        }
        
        public synchronized void setGameStarted(boolean gameStarted) {
                this.gameStarted = gameStarted;
        }
        
        public synchronized boolean isPlayNewLevelSong(){
        	return playNewLevelSong;
        }
        
        public synchronized void setPlayNewLevelSong(boolean playNewLevelSong){
        	this.playNewLevelSong = playNewLevelSong;
        }
        
        /**
         * Indicates if the game is starting ("Get Ready" message is displaying) or not.
         * @return if the game is starting or not.
         */
        public synchronized boolean isGameStarting() {
                return gameStarting;
        }
        
        public synchronized void setGameStarting(boolean gameStarting) {
                this.gameStarting = gameStarting;
        }
        
        /**
         * Indicates if the game has ended and the "Game Over" message is displaying.
         * @return if the game has ended and the "Game Over" message is displaying.
         */
        public synchronized boolean isGameOver() {
                return gameOver;
        }
        
        public synchronized void setGameOver(boolean gameOver) {
                this.gameOver = gameOver;
        }
        
        /**
         * Indicates if a new ship should be created/drawn.
         * @return if a new ship should be created/drawn
         */
        public synchronized boolean isNewShip() {
                return newShip;
        }

        public synchronized void setNewShip(boolean newShip) {
                this.newShip = newShip;
        }
        
        /**
         * Indicates if a new boss should be created/drawn.
         * @return if a boss should be created/drawn.
         */
        public synchronized boolean isNewBoss(){
        	return newBoss;
        }
        
        public synchronized void setNewBoss(boolean newBoss){
        	this.newBoss = newBoss;
        }
        
        /**
         * Indicates if a new level should start.
         * @return if a newLevel should start.
         */
        public synchronized boolean isNewLevel(){
        	return newLevel;
        }
        
        public synchronized void setNewLevel(boolean newLevel){
        	this.newLevel = newLevel;
        }
        
        /**
         * Indicates if a new enemy ship should be created/drawn.
         * @return if a new enemy ship should be created/drawn.
         */
        public synchronized boolean isNewEnemyShip(){
                return newEnemyShip;
        }
        
        public synchronized void setNewEnemyShip(boolean newEnemyShip){
                this.newEnemyShip = newEnemyShip;
        }
        
        /**
         * Indicates if a new life should be created/drawn.
         * @return if a new life should be created/drawn
         */
        
        public synchronized boolean isNewLife(){
                return newLife;
        }
        
        public synchronized void setNewLife(boolean newLife){
                this.newLife = newLife;
        }

        /**
         * Indicates if a new asteroid should be created/drawn.
         * @return if a new asteroid should be created/drawn
         */
        public synchronized boolean isNewAsteroid() {
                return newAsteroid;
        }

        public synchronized void setNewAsteroid(boolean newAsteroid) {
                this.newAsteroid = newAsteroid;
        }
        
        /**
         * Indicates if a new indestructible asteroid should be created/drawn.
         * @return if a new indestructible asteroid should be created/drawn
         */
        public synchronized boolean isNewIndestructibleAsteroid() {
                return newIndestructibleAsteroid;
        }

        public synchronized void setNewIndestructibleAsteroid(boolean newIndestructibleAsteroid) {
                this.newIndestructibleAsteroid = newIndestructibleAsteroid;
        }
        
        /**
         * Indicates if a new split asteroid should be created/drawn.
         * @return if a new split asteroid should be created/drawn
         */
        public synchronized boolean isNewSplitAsteroid() {
                return newSplitAsteroid;
        }

        public synchronized void setNewSplitAsteroid(boolean newSplitAsteroid) {
                this.newSplitAsteroid = newSplitAsteroid;
        }

        /**
         * Returns the number of asteroid destroyed.
         * @return the number of asteroid destroyed
         */
        public synchronized long getAsteroidsDestroyed() {
                return asteroidsDestroyed;
        }

        public synchronized void setAsteroidsDestroyed(long asteroidsDestroyed) {
                this.asteroidsDestroyed = asteroidsDestroyed;
        }
        
        /**
         * Returns the score.
         * @param score the score.
         */
        public synchronized void setScore(long score){
                this.score = score;
        }
        
        public synchronized long getScore(){
                return score;
        }
        
        /**
         * Returns the level.
         * @param level the level.
         */
        public synchronized void setLevel(int level){
        	this.level = level;
        }
        
        public synchronized int getLevel(){
        	return level;
        }

        /**
         * Returns the number ships/lives left.
         * @return the number ships left
         */
        public synchronized int getShipsLeft() {
                return shipsLeft;
        }

        public synchronized void setShipsLeft(int shipsLeft) {
                this.shipsLeft = shipsLeft;
        }

}