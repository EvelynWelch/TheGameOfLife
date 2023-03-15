import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardDisplay extends GridPane {
	StateManager stateManager;
	boolean mouseDown = false;
	
	
	
	/** 
	 * NOTE: the state manager has to be instantiated
	 * */
	BoardDisplay(StateManager stateManager){
		super();
		this.stateManager = stateManager;
		setOnMousePressed(e -> {
			mouseDown = true;
		});
		setOnMouseReleased(e -> {
			mouseDown = false;
		});
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
				add(cellFactory(this.stateManager.board[i][j], i , j), i, j);
			}
		}
	}
	
	public void addMouseEvents() {
		for(Node cell : getChildren()) {
			cell.setOnMouseEntered(e -> {
//				if(mouseDown) {
					Cell c = (Cell)cell;
					stateManager.board[c.getI()][c.getJ()] = !stateManager.board[c.getI()][c.getJ()];
					c.setAlive(stateManager.board[c.getI()][c.getJ()]);
					c.draw();
//				}
			});
		}
	}
	
	/** 
	 * Creates a rectangle that is either green (alive) or red (dead)
	 * */
	
	
	public Cell cellFactory(boolean alive, int i, int j) {
		// alive sets it's color
		Cell cell = new Cell(alive, i, j);
		cell.setHeight(10);
		cell.setWidth(10);
//		cell.setOnMouseEntered(e -> {
//			if(mouseDown) {
//				
//			}
//		});
		cell.setStroke(Color.BLACK);
		if(alive) {
			cell.setFill(Color.GREEN);
		} else {
			cell.setFill(Color.RED);
		}
		return cell;	
	}	
}

class Cell extends Rectangle {
	private boolean alive;
	private int i;
	private int j;

	Cell(boolean alive, int i, int j){
		
		this.alive = alive;
		this.i = i;
		this.j =j;
		draw();
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean living) {
		alive = living;
	}
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	public void draw() {
		setStroke(Color.BLACK);
		if(alive) {
			setFill(Color.GREEN);
		} else {
			setFill(Color.RED);
		}
	}
}
