package game;

import environment.LocalBoard;

//represent a thread that can move an obstacle within a game
public class ObstacleMover extends Thread {
	private Obstacle obstacle;
	private LocalBoard board;
	
	//declared as a subclass of the Thread class. This indicates that instances of ObstacleMover can be executed as separate threads.
	//used to initialize the ObstacleMover with the obstacle it should move and the game board it operates within.
	public ObstacleMover(Obstacle obstacle, LocalBoard board) {
		super();
		this.obstacle = obstacle;
		this.board = board;
	}

	//represents the entry point for the thread's execution.
	@Override
	public void run() {
		// TODO
	}
}
