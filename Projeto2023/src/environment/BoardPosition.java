package environment;

/** Classe representing a position on the board, with some utilities
 * 
 * @author luismota
 *
 */

//represent a position on the board. This class is used to store the x and y coordinates of a position.
public class BoardPosition {
	public final int x;
	public final int y;


	public BoardPosition(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	//provide a custom string representation of a BoardPosition object. It returns a string in the format "(x, y)" 
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	/*compare two BoardPosition objects for equality. It takes an Object as a parameter and checks if it's a BoardPosition object with the same x and y coordinates. If the coordinates match, the two positions are considered equal.*/
	@Override
	public boolean equals(Object obj) {
		BoardPosition other = (BoardPosition) obj;
		return other.x==x && other.y == y;
	}
	
	/*calculates the Euclidean distance between the current BoardPosition and another BoardPosition object passed as an argument.*/
	public double distanceTo(BoardPosition other) {
		double delta_x = y - other.y;
		double delta_y = x - other.x;
		return Math.sqrt(delta_x * delta_x + delta_y * delta_y);
	}


	/*provide convenient ways to get new BoardPosition objects representing positions above, below, to the left, and to the right of the current position,*/
	public BoardPosition getCellAbove() {
		return new BoardPosition(x, y-1);
	}
	public BoardPosition getCellBelow() {
		return new BoardPosition(x, y+1);
	}
	public BoardPosition getCellLeft() {
		return new BoardPosition(x-1, y);
	}
	public BoardPosition getCellRight() {
		return new BoardPosition(x+1, y);
	}
}
