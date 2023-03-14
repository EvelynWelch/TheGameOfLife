import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardDisplay extends GridPane {
	StateManager stateManager;
	
	/** 
	 * NOTE: the state manager has to be instantiated
	 * */
	BoardDisplay(StateManager stateManager){
		super();
		this.stateManager = stateManager;
	}
	
	/** 
	 * uses StateManager to get the nextGeneration and then runs drawBoard()
	 * */
	public void nextGeneration() {
		this.stateManager.nextGeneration();
		drawBoard();
	}
	/** 
	 * Goes through StateManager.boad and makes all of the corresponding cells
	 * */
	public void drawBoard() {
		for(int i = 0; i < this.stateManager.board.length; i++) {
			for(int j = 0; j < this.stateManager.board[i].length; j++) {
				add(cellFactory(this.stateManager.board[i][j]), i, j);
			}
		}
	}
	
	/** 
	 * Creates a rectangle that is either green (alive) or red (dead)
	 * */
	public Rectangle cellFactory(boolean alive) {
		// alive sets it's color
		Rectangle cell = new Rectangle(10, 10);
		cell.setStroke(Color.BLACK);
		if(alive) {
			cell.setFill(Color.GREEN);
		} else {
			cell.setFill(Color.RED);
		}
		return cell;	
	}	
}
