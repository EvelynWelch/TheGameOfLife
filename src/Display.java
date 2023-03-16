import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
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
	boolean hasAnimationPlayed = false;
	double currentSpeed = 1.0;

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
		StateManager stateManager = new StateManager();
		BoardDisplay board = new BoardDisplay(stateManager);
		// Draw the initial state of the board
		board.makeBoard();
		// Set up the Animation
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(playSpeed), e -> {
			if(!board.stateManager.stagnant) {
				board.nextGeneration();
			}			
		}));
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
				animation.setRate(currentSpeed);
				animation.play();
				this.isPlaying = true;
			}
		});
		// add speed up and slow down functionality
		ui.fasterButton.setOnAction(e -> {
			
//			double currentRate = animation.getCurrentRate(); // 1.00
			
			double newRate = currentSpeed += .5; // this will make every click go faster and faster
			if(isPlaying) {
				animation.setRate(newRate);
			}
			ui.setSpeedLabel(newRate);

		});
		ui.slowerButton.setOnAction(e -> {
//			double currentRate = animation.getCurrentRate(); // 1.00
			if(currentSpeed > 0) {
				double newRate = currentSpeed -= .5;
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
		
		
		ui.saveButton.setOnAction(e ->{
			if(isPlaying) {
				animation.pause();
			}
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Set save location");
			String file = fileChooser.showSaveDialog(primaryStage).toString();
			DataStore dataStore = new DataStore(file);
			dataStore.create(board.stateManager.board);
		});
		ui.loadButton.setOnAction(e -> {
			if(isPlaying) {
				animation.pause();
			}
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			String file = fileChooser.showOpenDialog(primaryStage).toString();
			DataStore dataStore = new DataStore(file);
			boolean[][] loadedBoard = (boolean[][])dataStore.read();
			StateManager newState = new StateManager(loadedBoard);
			board.stateManager = newState;
			board.makeBoard();
		});
		
		// get board size from boardSizeTextField and either expand or shrink the board
		ui.setBoardSizeButton.setOnAction(e -> {
			
			if(isPlaying) {
				animation.pause();
				isPlaying = false;
				ui.pausePlayButton.setText("play");
			}
			// Update the board size in StateManager
			int boardSize = UserInterface.getTextFieldTextAsInt(ui.boardSizeTextField);
			board.stateManager.changeBoardSize(boardSize);
			// re draw the board.
			board.makeBoard();
			board.redrawBoard();
			board.setMaxSize(750, 750);
//			if(wasPlaying) {
//				animation.play();
//				isPlaying = true;
//			}
		});
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(500, 500);
		scrollPane.setContent(board);
		// Add the ui and board the parent
		wrapper.setRight(ui.getUIpane());
		wrapper.setCenter(scrollPane);


		Scene scene = new Scene(wrapper,1000, 750);

		primaryStage.setTitle("The Game of Life"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
;