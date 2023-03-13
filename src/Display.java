import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/** 
 * This class is what draws the board. It does so with JavaFX
 * 
 * */
public class Display extends Application {
	
	public Rectangle makeCell(boolean alive) {
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
	
	public StateManager stateManagerFactory(boolean[][] board) {
		return new StateManager(board);
	}
	
	public StateManager stateManagerFactory(int size) {
		return new StateManager(size);
	}
	
	public void start(Stage primaryStage) {
		// TODO: timer that controls how long it shows each generation
		// TODO: skip x generations button / input
		// TODO: set board size input (this needs to be done before it has started)
		// TODO: export current state (can only happen when paused) on button press pause then export
		// TODO: load state (Take a file string and check if it exists etc)
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, 200, 50);
	    Rectangle cell1 = makeCell(true);
	    Rectangle cell2 = makeCell(false);
		pane.add(cell1, 0, 0);
		pane.add(cell2, 1, 0);
		primaryStage.setTitle("The Game of Life"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show();
	}
	
	
	  public static void main(String[] args) {
		    launch(args);
		  }
}
