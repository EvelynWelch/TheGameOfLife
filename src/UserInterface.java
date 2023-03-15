import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;

public class UserInterface {
	
	UserInterface(){
		setUp();
	}
	
	private VBox UIpane = new VBox();
	
	// TODO: look at how to implement this. I would like to use it on both save() and load()
	FileChooser fc = new FileChooser();
	
	Button pausePlayButton = new Button("play");
	
	HBox speedControls = new HBox(10.00);
	Label speedLabel = new Label("1.0");
	Button fasterButton = new Button(">>");
	Button slowerButton = new Button("<<");
	
	
	HBox generationControls = new HBox();
	Label skipGenerationsLabel = new Label("Skip generations: ");
	TextField skipGenerationsTextField = new TextField("25");
	Button skipGenerationsButton = new Button("skip");
	
	HBox loadSaveControls = new HBox(10.00);
	Button saveButton = new Button("save");
	Button loadButton = new Button("load");
	
	HBox boardSizeControls = new HBox();
	Label boardSizeLabel = new Label("Board size: ");
	TextField boardSizeTextField = new TextField("25");
	Button setBoardSizeButton = new Button("size");
	
	
	/** Gets the integer value from a text field*/
	public static int getTextFieldTextAsInt(TextField tf) {
		return Integer.valueOf(tf.getText());
	}
	

	/** Takes a double and sets it's value to speedLabel */
	public void setSpeedLabel(double speed) {
		speedLabel.setText(String.valueOf(speed));
	};
	
	
	
	
	/** get UIpane*/
	public Pane getUIpane() {
		return UIpane;
	}
	
	
	/** 
	 * adds all of the even handlers to and puts all children in UIpane 
	 * NOTE: some of the stuff might not be able to get set up here because it requires info from the BoardGrid
	 * */
	private void setUp() {
		speedControls.setPadding(new Insets(10, 20, 10, 20));
		speedControls.getChildren().addAll(slowerButton, speedLabel, fasterButton);
//		
		generationControls.setPadding(new Insets(10, 20, 10, 20));
		generationControls.getChildren().addAll(skipGenerationsLabel, skipGenerationsTextField, skipGenerationsButton);
		loadSaveControls.setPadding(new Insets(10, 20, 10, 20));
	
		loadSaveControls.getChildren().addAll(pausePlayButton, loadButton, saveButton);
		boardSizeControls.setPadding(new Insets(10, 20, 10, 20));
		boardSizeControls.getChildren().addAll(boardSizeLabel, boardSizeTextField, setBoardSizeButton);
		
		UIpane.getChildren().addAll(loadSaveControls, speedControls, generationControls, boardSizeControls);


	}
	
}
