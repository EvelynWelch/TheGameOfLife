import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

/**
 * This class is what draws the board. It does so with JavaFX
 * 
 */
public class Display extends Application {
	boolean isPlaying = false;
	double playSpeed = 500;

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
		// TODO: Make it so people can draw on the board.

		// Generate a random board
		BorderPane wrapper = new BorderPane();

		Random rand = new Random();
		boolean[][] b = new boolean[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				b[i][j] = rand.nextBoolean();
			}
		}

		UserInterface ui = new UserInterface();

		// create a new StateManager and pass it to BoardDisplay
		StateManager stateManager = new StateManager(b);
		BoardDisplay board = new BoardDisplay(stateManager);
		// Draw the initial state of the board
		board.drawBoard();
		int defaultDuration = 500;
		// Set up the Animation
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(playSpeed), e -> board.nextGeneration()));
		animation.setCycleCount(Timeline.INDEFINITE);
//		animation.play(); // Start animation

		// Set the pause / play functionality to pausePlayButton
		ui.pausePlayButton.setOnAction(e -> {
			if (this.isPlaying) {
				animation.pause();
				ui.pausePlayButton.setText("play");
				this.isPlaying = false;
			} else {
				ui.pausePlayButton.setText("pause");
				animation.play();
				this.isPlaying = true;
			}
		});
		// add speed up and slow down functionality
		ui.fasterButton.setOnAction(e -> {
			double currentRate = animation.getCurrentRate(); // 1.00
			double newRate = currentRate += .5; // this will make every click go faster and faster
			animation.setRate(newRate);
			ui.setSpeedLabel(newRate);

		});
		ui.slowerButton.setOnAction(e -> {
			double currentRate = animation.getCurrentRate(); // 1.00
			if(currentRate > 0) {
				double newRate = currentRate -= .5;
				animation.setRate(newRate);
				ui.setSpeedLabel(newRate);
			}
		});
		// Add skip generations functionality
		ui.skipGenerationsButton.setOnAction(e -> {
			// get the amount to skip
//			System.out.println("starting the skip");
			int skipAmount = UserInterface.getTextFieldTextAsInt(ui.skipGenerationsTextField);
			
			// pause the animation while it figures out the next generation NOTE: this might be a little weird.
			if(isPlaying) {
				animation.pause();
				isPlaying = false;
			}
			for(int i = 0; i < skipAmount; i++) {
				board.stateManager.nextGeneration();
			}
			animation.play();
			isPlaying = true;
//			System.out.println("done skipping");
		});
		
		// Save button
		// game needs to start paused.
		// save needs to accompanied by load.
		// 
		ui.saveButton.setOnAction(e ->{
			System.out.println("save not implimented yet.");
		});
		// set board size
		// set board size needs to be done before it has started playing. so it needs to start paused
		// When it is paused it needs the ability to click on nodes (preferably hold down and drag it around and have it
		// switch them to alive.) 
		// When you set these, and then switch the board size how do you maintain the living squares?
		// This would also let you pause and increase the size. then resume
		// 
		
		// I don't know how to make this update the display. Maybe make a new timeline that updates it 1 time.
		ui.setBoardSizeButton.setOnAction(e -> {
			boolean wasPlaying = isPlaying;
			if(isPlaying) {
				animation.pause();
				isPlaying = false;
				ui.pausePlayButton.setText("play");
			}
			// Update the board size in StateManager
			int boardSize = UserInterface.getTextFieldTextAsInt(ui.boardSizeTextField);
			board.stateManager.changeBoardSize(boardSize);
			// re draw the board.
			board.drawBoard();
//			if(wasPlaying) {
//				animation.play();
//				isPlaying = true;
//			}
		});
		

		
		// Add the ui and board the parent
		wrapper.setCenter(board);
		wrapper.setBottom(ui.getUIpane());
		Scene scene = new Scene(wrapper, 500, 500);

		primaryStage.setTitle("The Game of Life"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
