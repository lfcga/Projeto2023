package environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import game.GameElement;
import game.Goal;
import game.Obstacle;
import game.Snake;

//abstract class, meaning it cannot be instantiated directly but must be subclassed.
public abstract class Board extends Observable {
	protected Cell[][] cells; // 2D array of Cell objects representing the cells of the game board.
	private BoardPosition goalPosition;//object representing the position of a goal within the game.
	
	//Constants for controlling time intervals.
	public static final long PLAYER_PLAY_INTERVAL = 100;
	public static final long REMOTE_REFRESH_INTERVAL = 200;
	
	//Constants representing the number of columns and rows in the game board.
	public static final int NUM_COLUMNS = 30;
	public static final int NUM_ROWS = 30;
	
	protected LinkedList<Snake> snakes = new LinkedList<Snake>(); //list of Snake objects.
	private LinkedList<Obstacle> obstacles= new LinkedList<Obstacle>(); //list of Obstacle objects.
	protected boolean isFinished;//to determine if the game is finished.

	//constructor
	public Board() {
		cells = new Cell[NUM_COLUMNS][NUM_ROWS];
		for (int x = 0; x < NUM_COLUMNS; x++) {
			for (int y = 0; y < NUM_ROWS; y++) {
				cells[x][y] = new Cell(new BoardPosition(x, y));
			}
		}

	}

	public Cell getCell(BoardPosition cellCoord) {
		return cells[cellCoord.x][cellCoord.y];
	}

	protected BoardPosition getRandomPosition() {
		return new BoardPosition((int) (Math.random() *NUM_ROWS),(int) (Math.random() * NUM_ROWS));
	}

	public BoardPosition getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(BoardPosition goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	//add a GameElement to the board. It randomly places the element on an unoccupied cell, if the element is a goal, it sets the goalPosition
	public void addGameElement(GameElement gameElement) {
		boolean placed=false;
		while(!placed) {
			BoardPosition pos=getRandomPosition();
			if(!getCell(pos).isOcupied() && !getCell(pos).isOcupiedByGoal()) {
				getCell(pos).setGameElement(gameElement);
				if(gameElement instanceof Goal) {
					setGoalPosition(pos);
//					System.out.println("Goal placed at:"+pos);
				}
				placed=true;
			}
		}
	}

	//returns a list of neighboring cell positions for a given cell. It considers positions to the left, right, above, and below the current cell
	public List<BoardPosition> getNeighboringPositions(Cell cell) {
		ArrayList<BoardPosition> possibleCells=new ArrayList<BoardPosition>();
		BoardPosition pos=cell.getPosition();
		if(pos.x>0)
			possibleCells.add(pos.getCellLeft());
		if(pos.x<NUM_COLUMNS-1)
			possibleCells.add(pos.getCellRight());
		if(pos.y>0)
			possibleCells.add(pos.getCellAbove());
		if(pos.y<NUM_ROWS-1)
			possibleCells.add(pos.getCellBelow());
		return possibleCells;
	}

	
	//creates and adds a Goal object to the board using the addGameElement method and returns the created goal.
	protected Goal addGoal() {
		Goal goal=new Goal(this);
		addGameElement( goal);
		return goal;
	}

	//adds a specified number of obstacles to the board. It clears the existing obstacle list and generates new obstacles.
	protected void addObstacles(int numberObstacles) {
		// clear obstacle list , necessary when resetting obstacles.
		getObstacles().clear();
		while(numberObstacles>0) {
			Obstacle obs=new Obstacle(this);
			addGameElement( obs);
			getObstacles().add(obs);
			numberObstacles--;
		}
	}
	
	public LinkedList<Snake> getSnakes() {
		return snakes;
	}


	//notify observers (e.g., game clients) of changes in the board's state
	@Override
	public void setChanged() {
		super.setChanged();
		notifyObservers();
	}

	public LinkedList<Obstacle> getObstacles() {
		return obstacles;
	}

	//Subclasses of Board must implement these methods according to their specific game logic.
	
	/*The init method is used to initialize the game board or set up its initial state. Subclasses of Board are expected to provide their own implementation of this method, tailored to the specific requirements of their games. This method can be used to create and position game elements, set up the board's initial conditions, and prepare the game environment.*/
	public abstract void init(); 
	
	/*responsible for handling user input when a key is pressed on the keyboard. It takes an argument, usually representing the key code or identifier, and is intended to respond to user interactions. Subclasses should implement this method to define how the game should react when specific keys are pressed. For example, in a game where a snake moves, pressing arrow keys might change the direction of the snake's movement.*/
	public abstract void handleKeyPress(int keyCode);

/*handles key release events. When a key is released, it can trigger specific actions in the game. For example, in a platformer game, releasing the jump key may cause the character to stop jumping. Subclasses should implement this method to specify the behavior associated with key releases.*/
	public abstract void handleKeyRelease();
	
	
	

	public void addSnake(Snake snake) {
		snakes.add(snake);
	}


}
