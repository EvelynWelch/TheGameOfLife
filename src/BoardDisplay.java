import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardDisplay extends GridPane {
	StateManager stateManager;
	boolean mouseDown = false;

	/**
	 */
	BoardDisplay(StateManager stateManager) {
		super();
		this.stateManager = stateManager;
	
	}

	/**
	 * uses StateManager to get the nextGeneration and then runs drawBoard()
	 */
	public void nextGeneration() {
		this.stateManager.nextGeneration();
//		makeBoard();
		redrawBoard();
	}
	
	public void redrawBoard() {
		ArrayList<Node> deadChildren = new ArrayList<>();
		for(Node cell : getChildren()) {
			Cell c = (Cell)cell;
			boolean inBounds = true;
			if(!(c.getI() > stateManager.board.length -1)) {
				if(!(c.getJ() > stateManager.board[c.getI()].length -1)) {
					c.setAlive(stateManager.getCellState(c.getI(), c.getJ()));
					c.draw();
				} else {
					inBounds = false;
				}
			} else {
				inBounds = false;
			}
			if(!inBounds) {
				deadChildren.add(cell);
			}
		}
		for(Node d : deadChildren) {
			getChildren().remove(d);
		}
		
		
	}
	public void makeBoard() {
		for (int i = 0; i < this.stateManager.board.length; i++) {
			for (int j = 0; j < this.stateManager.board[i].length; j++) {
				// get the state of the cell
				boolean state = this.stateManager.getCellState(i, j);
				// make a new cell
				Cell cell = cellFactory(state, i, j);
				// set state toggle
				cell.setOnMousePressed(e -> {
//					System.out.println("mouseDonw");
					Cell c = cell;
					boolean newState = !stateManager.getCellState(c.getI(), c.getJ());
					stateManager.setCellState(c.getI(), c.getJ(), newState);
					c.setAlive(newState);
				});
				add(cell, j, i);
			}
		}
	}

	/**
	 * Creates a rectangle that is either green (alive) or red (dead)
	 */

	public Cell cellFactory(boolean alive, int i, int j) {
		// alive sets it's color
		Cell cell = new Cell(alive, i, j);
		cell.setHeight(10);
		cell.setWidth(10);
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
