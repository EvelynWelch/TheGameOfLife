import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;



public class BoardDisplay extends GridPane {
	StateManager stateManager;
	
	BoardDisplay(StateManager stateManager){
		super();
		this.stateManager = stateManager;
	}
	
	public void nextGeneration() {
		this.stateManager.nextGeneration();
		drawBoard();
	}
	
	public void drawBoard() {
		for(int i = 0; i < this.stateManager.board.length; i++) {
			for(int j = 0; j < this.stateManager.board[i].length; j++) {
				add(cellFactory(this.stateManager.board[i][j]), i, j);
			}
		}
	}
	
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
