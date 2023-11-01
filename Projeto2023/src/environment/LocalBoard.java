package environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutorService;//threads pool
import java.util.concurrent.Executors;//threads pool

import game.GameElement;
import game.Goal;
import game.Obstacle;
import game.Server;
import game.Snake;
import game.AutomaticSnake;

/** Class representing the state of a game running locally
 * 
 * @author luismota
 *
 */
 /* represents the state of a game running locally. It is a subclass of the Board class, which provides a foundation for game boards in a larger game or simulation framework.*/
 /*subclass of the Board class, meaning it inherits the properties and methods of the Board class while adding its specific functionality for a local game.*/
public class LocalBoard extends Board{
	
	private static final int NUM_SNAKES = 2;
	private static final int NUM_OBSTACLES = 25;
	private static final int NUM_SIMULTANEOUS_MOVING_OBSTACLES = 3;

	
/*initializes the game. In this constructor:Two AutomaticSnake objects are created. A number of obstacles specified by NUM_OBSTACLES are added to the board. A goal is added to the board using the addGoal method.*/
	public LocalBoard() {
		
		for (int i = 0; i < NUM_SNAKES; i++) {
			AutomaticSnake snake = new AutomaticSnake(i, this);
			snakes.add(snake);
		}

		addObstacles( NUM_OBSTACLES);
		
		Goal goal=addGoal();
//		System.err.println("All elements placed");
	}



/*initialize the game board and start the game threads. In this method:The start method is called for each snake in the snakes list, which starts the threads responsible for controlling the snakes. The setChanged method is called, notifying observers of changes in the game board's state.*/
	public void init() {
		for(Snake s:snakes)
			s.start();
		// TODO: launch other threads ->> ObstacleMover
		setChanged();
	}

	
//Para os jogadores humanos
	@Override
	public void handleKeyPress(int keyCode) {
		// do nothing... No keys relevant in local game
	}

	@Override
	public void handleKeyRelease() {
		// do nothing... No keys relevant in local game
	}





}
