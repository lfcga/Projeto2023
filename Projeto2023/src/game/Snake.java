/*serves as the base class for representing different types of snakes in a game. It provides common methods and properties that are shared by both human-controlled (HumanSnake) and automatic (AutomaticSnake) snakes. */

package game;

import java.io.Serializable;
import java.util.LinkedList;

import environment.LocalBoard;
import gui.SnakeGui;
import environment.Board;
import environment.BoardPosition;
import environment.Cell;
/** Base class for representing Snakes.
 * Will be extended by HumanSnake and AutomaticSnake.
 * Common methods will be defined here.
 * @author luismota
 *
 */
 //declared as an abstract class, indicating that it cannot be instantiated directly but must be subclassed. 
public abstract class Snake extends Thread implements Serializable{

//  eliminado por email prof 07nov
//	private static final int DELTA_SIZE = 10;
	protected LinkedList<Cell> cells = new LinkedList<Cell>();
	protected int size = 5;
	private int id;
	private Board board;
	
	public Snake(int id,Board board) {
		this.id = id;
		this.board=board;
	}

	public int getSize() {//the size of the snake.
		return size;
	}

	public int getIdentification() {//snake's identification (ID).
		return id;
	}

	public int getLength() {//number of cells in the snake's body.
		return cells.size();
	}
	
	public LinkedList<Cell> getCells() {//linked list of cells representing the snake's body.
		return cells;
	}
	
	/*This method is intended to move the snake to a new cell on the game board. It may involve coordination and mutual exclusion to ensure that multiple snakes do not move to the same cell simultaneously.*/
	protected void move(Cell cell) throws InterruptedException {
		// TODO
	}
	
	
	/*method returns a linked list of BoardPosition objects representing the path of the snake. It iterates through the snake's cells and retrieves their positions.*/
	public LinkedList<BoardPosition> getPath() {
		LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
		for (Cell cell : cells) {
			coordinates.add(cell.getPosition());
		}

		return coordinates;
	}
	
	/*method is responsible for positioning the snake on the game board at the start of the game. It selects a random position on the first column of the board and requests the cell at that position for the snake. It adds the cell to the snake's list of cells and prints a message indicating the snake's starting position.*/	
	protected void doInitialPositioning() {
		// Random position on the first column. 
		// At startup, snake occupies a single cell
		int posX = 0;
		int posY = (int) (Math.random() * Board.NUM_ROWS);
		BoardPosition at = new BoardPosition(posX, posY);
		
		try {
			board.getCell(at).request(this);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cells.add(board.getCell(at));
		System.err.println("Snake "+getIdentification()+" starting at:"+getCells().getLast());		
	}
	
	//returns the game board associated with the snake.
	public Board getBoard() {
		return board;
	}
	
	
}
