package game;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.Position;

import environment.LocalBoard;
import gui.SnakeGui;
import environment.Cell;
import environment.Board;
import environment.BoardPosition;

/*a subclass of the Snake class and is designed to represent an automatic (non-player controlled) snake in the context of a game. */
public class AutomaticSnake extends Snake {


/*The id parameter is an identifier for the snake, and the board parameter represents the game board (of type LocalBoard) to which this snake belongs. */
	public AutomaticSnake(int id, LocalBoard board) {
		super(id,board);

	}

/*overridden method from the Runnable interface and represents the entry point for the snake's behavior when it's executed as a separate thread*/
	@Override
	public void run() {
		
		doInitialPositioning();//method for setting the initial position of the snake on the game board
		
		System.err.println("initial size:"+cells.size());//indicate the number of cells (or body segments) in the snake.
		
		/*block that attempts to request the last cell occupied by the snake by calling cells.getLast().request(this). If another snake already occupies the last cell, it may throw an InterruptedException*/
		try {
			cells.getLast().request(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO: automatic movement
	}
	

	
}
