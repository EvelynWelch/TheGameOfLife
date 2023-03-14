import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class UserInterface {
	Pane UIpane = new Pane();
	
	Button pausePlay = new Button("||");
	
	
	Label speedLabel = new Label();
	Button faster = new Button(">>");
	Button slower = new Button("<<");
	
	
	TextField skipGenerationsVal = new TextField();
	Button skipGenerations = new Button("skip");
	
	Button save = new Button("save");
	
	TextField boardSize = new TextField();
	Button setBoardSize = new Button("size");
	
}
