import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

/**
 * This class is what draws the board. It does so with JavaFX
 * 
 */
public class Display extends Application {

	public Rectangle makeCell(boolean alive) {
		// alive sets it's color
		Rectangle cell = new Rectangle(10, 10);
		cell.setStroke(Color.BLACK);
		if (alive) {
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

	@Override
	public void start(Stage primaryStage) {
		// TODO: timer that controls how long it shows each generation
		// TODO: skip x generations button / input
		// TODO: set board size input (this needs to be done before it has started)
		// TODO: export current state (can only happen when paused) on button press
		// pause then export
		// TODO: load state (Take a file string and check if it exists etc)

		// Generate a random board

		Random rand = new Random();
		boolean[][] b = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				b[i][j] = rand.nextBoolean();
			}
		}
		StateManager board = new StateManager(b);

		// add all the cells to the GridPane
		BoardDisplay pane = new BoardDisplay(board);
		pane.drawBoard();

		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> pane.nextGeneration()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation

		Scene scene = new Scene(pane, 200, 200);

		primaryStage.setTitle("The Game of Life"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}