package environment;

import java.io.Serializable;

import javax.sound.midi.SysexMessage;

import game.GameElement;
import game.Goal;
import game.Obstacle;
import game.Snake;
import game.AutomaticSnake;
/** Main class for game representation. 
 * 
 * @author luismota
 *
 */
 
 /*represents a cell or position in the context of a game. Each Cell object holds information about its state, including its position, any occupying snake, and a game element (which could be a goal or an obstacle). */
public class Cell {
	private BoardPosition position;
	private Snake ocuppyingSnake = null;
	private GameElement gameElement=null;
	
	
	public Cell(BoardPosition position) {
		super();
		this.position = position;
	}


// for coordinating and ensuring mutual exclusion among snakes trying to occupy the same cell
	public void request(Snake snake)throws InterruptedException {
		//TODO coordination and mutual exclusion
		ocuppyingSnake=snake;
	}


//for releasing the cell once a snake leaves. 
	public void release() {
		//TODO
	}

//setting a GameElement (goal or obstacle) in the cell. 
	public  void setGameElement(GameElement element) {
		// TODO coordination and mutual exclusion
		gameElement=element;
	}
	
//removing a goal from the cell
	public  Goal removeGoal() {
		// TODO
		return null;
	}

//removing an obstacle from the cell	
	public void removeObstacle() {
	//TODO
	}

//Checks if the cell is occupied by a snake.
	public boolean isOcupiedBySnake() {
		return ocuppyingSnake!=null;
	}

//Checks if the cell is occupied either by a snake or by an obstacle
public boolean isOcupied() {
		return isOcupiedBySnake() || (gameElement!=null && gameElement instanceof Obstacle);
	}
	
//Checks if the cell is occupied by a goal.
	public boolean isOcupiedByGoal() {
		return (gameElement!=null && gameElement instanceof Goal);
	}

	//getters & Setters
	public GameElement getGameElement() {
		return gameElement;
	}

	public Goal getGoal() {
		return (Goal)gameElement;
	}

	public Snake getOcuppyingSnake() {
		return ocuppyingSnake;
	}
	
	public BoardPosition getPosition() {
		return position;
	}
}
