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

public class UserInterface {
	
	UserInterface(){
		setUp();
	}
	
	private VBox UIpane = new VBox();
	
	// TODO: look at how to implement this. I would like to use it on both save() and load()
	FileChooser fc = new FileChooser();
	
	Button pausePlayButton = new Button("play");
	Label speedLabel = new Label("1.0");
	Button fasterButton = new Button(">>");
	Button slowerButton = new Button("<<");
	
	
	Label skipGenerationsLabel = new Label("Skip generations: ");
	TextField skipGenerationsTextField = new TextField("25");
	Button skipGenerationsButton = new Button("skip");
	
	Button saveButton = new Button("save");
	Button loadButton = new Button("load");
	
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
		UIpane.getChildren().addAll(pausePlayButton, slowerButton, speedLabel, fasterButton, 
				skipGenerationsLabel, skipGenerationsTextField, skipGenerationsButton, 
				loadButton, saveButton, boardSizeLabel, boardSizeTextField, setBoardSizeButton);
//		fasterButton.setOnAction(e -> {
//		      System.out.println("fasterButton pressed!");
//	    });
//		slowerButton.setOnAction(e -> {
//		      System.out.println("slowerButton pressed!");
//	    });

	}
	
}
