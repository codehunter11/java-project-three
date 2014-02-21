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

package rbadia.voidspace.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

import rbadia.voidspace.main.GameScreen;

/**
 * Manages and plays the game's sounds.
 */
public class SoundManager {
	private static boolean SOUND_ON = true;

    private AudioClip shipExplosionSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/boomTrue.wav"));
    private AudioClip bulletSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/laser.wav"));
    private AudioClip extraLifeSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/oneUpTrue.wav"));
    private AudioClip gameOverSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/gameOver.wav"));
    private AudioClip newLevelSound = Applet.newAudioClip(GameScreen.class.getResource(
    		"/rbadia/voidspace/sounds/newLevelTrue.wav"));
    private AudioClip bossLevelSound = Applet.newAudioClip(GameScreen.class.getResource(
    		"/rbadia/voidspace/sounds/bossLevelTrue.wav"));
    private AudioClip gameMusicSound = Applet.newAudioClip(GameScreen.class.getResource(
    		"/rbadia/voidspace/sounds/GameMusic.wav"));
    
    /**
     * Plays sound for bullets fired by the ship.
     */
    public void playBulletSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				bulletSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for game music.
     */
    public void playGameMusicSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				gameMusicSound.loop();

    			}
    		}).start();
    	}
    }
    
    /**
     * Stop sound for game music.
     */
    public void stopGameMusicSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run(){
    				gameMusicSound.stop();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays the final level audio.
     */
    public void playFinalLevelSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				bossLevelSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Mutes sound for the game.
     */
    
    public void muteSound(boolean sound){
    	SOUND_ON = sound;
    }
    
    /**
     * Plays sound for game over.
     */
    public void playGameOverSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				gameOverSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for one ups achieved.
     */
    public void playExtraLifeSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run(){
    				extraLifeSound.play();
    			}
    		}).start();
    	}
    }
    /**
     * Plays sound for a new level.
     */
    public void playNewLevelSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run(){
    				newLevelSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for ship explosions.
     */
    public void playShipExplosionSound(){
    	if(SOUND_ON){
    		new Thread(new Runnable(){
    			public void run() {
    				shipExplosionSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for asteroid explosions.
     */
    public void playAsteroidExplosionSound(){
		// play sound for asteroid explosions
    	if(SOUND_ON){
    		
    	}
    }
}
