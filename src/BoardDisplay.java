import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardDisplay extends GridPane {
	StateManager stateManager;
	boolean mouseDown = false;

	/**
	 * NOTE: the state manager has to be instantiated
	 */
	BoardDisplay(StateManager stateManager) {
		super();
		this.stateManager = stateManager;
		setOnMousePressed(e -> {
//			System.out.println("mouse pressed");
			mouseDown = true;
		});
		setOnMouseReleased(e -> {
//			System.out.println("mouse released");
			mouseDown = false;
		});

	}

	/**
	 * uses StateManager to get the nextGeneration and then runs drawBoard()
	 */
	public void nextGeneration() {
		this.stateManager.nextGeneration();
		makeBoard();
//		redrawBoard();
	}

	/**
	 * Goes through StateManager.boad and makes all of the corresponding cells
	 */
	public void drawBoard() {
		for (int i = 0; i < this.stateManager.board.length; i++) {
			for (int j = 0; j < this.stateManager.board[i].length; j++) {
				add(cellFactory(this.stateManager.board[i][j], i, j), j, i);
			}
		}
	}
	
	
	public void redrawBoard() {
		for (Node cell : getChildren()) {
			Cell c = (Cell)cell;
			System.out.println("drawing board");
			c.draw();
////		System.out.println("Adding mouse event");
//		cell.setOnMousePressed(e -> {
//			System.out.println("mouseDonw");
//			Cell c = (Cell) cell;
		}
	}

	public void makeBoard() {
		for (int i = 0; i < this.stateManager.board.length; i++) {
			for (int j = 0; j < this.stateManager.board[i].length; j++) {
				// get the state of the cell
				boolean state = this.stateManager.getCellState(i, j);
				// make a new cell
				Cell cell = cellFactory(state, i, j);
				// set state togle
				cell.setOnMousePressed(e -> {
					System.out.println("mouseDonw");
					Cell c = (Cell) cell;
					boolean gottenState = stateManager.getCellState(c.getI(), c.getJ());
					boolean newState = !stateManager.getCellState(c.getI(), c.getJ());
					System.out.printf("i: %d, j: %d%n", c.getI(), c.getJ());
					System.out.println("gottenState: " + gottenState);
					System.out.println("newState: " + newState);
					stateManager.setCellState(c.getI(), c.getJ(), newState);
					c.setAlive(newState);
				});
				add(cell, j, i);

			}
		}
	}
//
//	public void addMouseEvents() {
//		for (Node cell : getChildren()) {
////			System.out.println("Adding mouse event");
//			cell.setOnMousePressed(e -> {
//				System.out.println("mouseDonw");
//				Cell c = (Cell) cell;
//				boolean gottenState = stateManager.getCellState(c.getI(), c.getJ());
//				boolean newState = !stateManager.getCellState(c.getI(), c.getJ());
////				System.out.printf("i: %d, j: %d%n", c.getI(), c.getJ());
////				System.out.println("gottenState: " + gottenState);
////				System.out.println("newState: " + newState);
//				stateManager.setCellState(c.getI(), c.getJ(), newState);
//				c.setAlive(newState);
//			});
//		}
//	}

	/**
	 * Creates a rectangle that is either green (alive) or red (dead)
	 */

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
		if (alive) {
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

	Cell(boolean alive, int i, int j) {

		this.alive = alive;
		this.i = i;
		this.j = j;
		setStroke(Color.BLACK);
		draw();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean living) {
		alive = living;
		draw();
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void draw() {
		if (alive) {
			setFill(Color.GREEN);
		} else {
			setFill(Color.RED);
		}
	}
}
