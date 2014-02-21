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

import javax.swing.JFrame;

/**
 * Main game class. Starts the game.
 */
public class VoidSpaceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// init main frame
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// get game screen
        GameScreen gameScreen = frame.getGameScreen();
		
		// init game logic handler
		GameLogic gameLogic = new GameLogic(gameScreen);
		
		// pass some variables to game screen
        gameScreen.setGameLogic(gameLogic);
        
		// init input handler
        InputHandler inputHandler = new InputHandler(gameLogic);
        frame.addKeyListener(inputHandler);
        
        // show main frame
		frame.setVisible(true);
		
		// init main game loop
		new Thread(new GameLoop(gameScreen, gameLogic, inputHandler)).start();
	}


}
