/**
 * This class handles the current state of the board. It also calculates the
 * next generations
 */
public class StateManager {
	// True is alive, false is dead.

	boolean[][] board;
	boolean stagnant = false;

	StateManager() {
		this.board = new boolean[50][50];
	}

	StateManager(boolean[][] board) {
		this.board = board;
	}

	StateManager(int size) {
		this.board = new boolean[size][size];
	}

	/**
	 * Get the cell at board[y][x]
	 */
	public boolean getCellState(int i, int j) {
		return board[i][j];
	}

	/**
	 * Sets the cell state at board[i][j];
	 */
	public void setCellState(int i, int j, boolean state) {
//		System.out.println("setCellState() called");
//		System.out.printf("i: %d, j: %d%n", i, j);
		board[i][j] = state;
	}

	/**
	 * gets the x, y of all neighbors NOTE: for accessing the board its [y][x] NOTE:
	 * This can return negative values or length +1 values if the cell is on the
	 * edge.
	 */
	public int[][] getNeighborLocations(int x, int y) {

		int[] topLeft = { y + 1, x - 1 };
		int[] topCenter = { y + 1, x };
		int[] topRight = { y + 1, x + 1 };
		int[] left = { y, x - 1 };
		int[] right = { y, x + 1 };
		int[] bottomLeft = { y - 1, x - 1 };
		int[] bottomCenter = { y - 1, x };
		int[] bottomRight = { y - 1, x + 1 };

		int[][] neighborLocations = { topLeft, topCenter, topRight, left, right, bottomLeft, bottomCenter,
				bottomRight, };
		return neighborLocations;
	}

	/**
	 * Takes the x, y of a node and counts all of its neighbors and determines the
	 * next state of the cell.
	 */
	public boolean nextState(int y, int x) {
		int livingNeighbors = countNeighbors(y, x);
		boolean cell = board[y][x];
		if (livingNeighbors >= 4 && cell) {
			// If a cell is alive and has 4 or more neighbors it dies
			return false;
		} else if (((livingNeighbors == 2) || (livingNeighbors == 3)) && cell) {
			// if a living cell has 2 or 3 neighbors it lives
			return true;
		} else if ((livingNeighbors == 3 && !cell)) {
			// if cell is dead and has exactly 3 neighbors it becomes alive
			return true;
		} else if (livingNeighbors == 1 && cell) {
			// If a cell has 1 living neighbor it dies of starvation.
			return false;
		} else if ((livingNeighbors == 1 || livingNeighbors == 2) && !cell) {
			return false;
		} else if (livingNeighbors == 0) {
			// no living neighbors dies
			return false;
		}

		return false;
	}

	/**
	 * Counts the number of living neighbors
	 */
	public int countNeighbors(int y, int x) {
		int livingNeighbors = 0;
		int[][] neighbors = getNeighborLocations(x, y);
		for (int i = 0; i < neighbors.length; i++) {
			if ((neighbors[i][0] >= 0) && // if y isn't negative
					(neighbors[i][0] < board.length) && // if y < boards y axis
					(neighbors[i][1] >= 0) && // if x isn't negative
					(neighbors[i][1] < board[neighbors[i][0]].length) // if x < boards x axis length
			) {
				int ny = neighbors[i][0];
				int nx = neighbors[i][1];
				if (board[ny][nx]) {
					livingNeighbors++;
				}
			}
		}
		return livingNeighbors;
	}

	/**
	 * Calculates the next state for each cell.
	 */
	public void nextGeneration() {
		// Assumes all rows have the same length
		stagnant = true;
		boolean[][] nextGeneration = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				boolean next = nextState(i, j);
				if (next != this.getCellState(i, j)) {
					stagnant = false;
				}
				nextGeneration[i][j] = next;
			}
		}
		board = nextGeneration;
	}

	/**
	 * prints out the entire board.
	 */
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * NOTE: it will add or cut it off of the "right" and "bottom".
	 */
	public void changeBoardSize(int targetSize) {
		int currentSize = board.length;
		if (targetSize > currentSize) {
			boolean[][] newBoard = new boolean[targetSize][targetSize];
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					newBoard[i][j] = board[i][j];
				}
			}
			board = newBoard;
		}
		if (targetSize < currentSize) {
			boolean[][] newBoard = new boolean[targetSize][targetSize];
			for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard[i].length; j++) {
					newBoard[i][j] = board[i][j];
				}
			}
			board = newBoard;
		}
	}

}
