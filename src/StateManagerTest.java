import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateManagerTest {

	@Test
	void getNeighboreLocationsTest() {
		boolean[][] board = {
				{true, true, true},
				{true, false, true},
				{true, true, true}
		};
		StateManager testStateManager = new StateManager(board);
		int[][] neighbors = testStateManager.getNeighborLocations(1, 1);
		for(int i = 0; i < neighbors.length; i++) {
			// y values of 2 should be false, true, false
			int y = neighbors[i][0];
			int x = neighbors[i][1];
			assertEquals(true, testStateManager.board[y][x]);
			assertEquals(neighbors[i].length, 2);
		}
		assertEquals(neighbors.length, 8);
	
	}
	
	@Test
	void nextStateTest() {
		boolean[][] board = {
				// if cell is dead and has exactly 3 neighbors it becomes alive
				// next state for [1][1] should be true
				{false, false, false},
				{true, false, false},
				{true, true, false}
		};
		assertEquals(false, board[0][0]);
		StateManager testStateManager = new StateManager(board);
		assertEquals(true, testStateManager.nextState(1, 1));
			
		boolean[][] board1 = { 
				// If a cell is alive and has 4 or more neighbors it dies
				// next state for [1][1] should be false
				{false, false, false},
				{true, true, true},
				{true, true, false}
		};
		assertEquals(true, board1[1][1]);
		StateManager testStateManager1 = new StateManager(board1);
		assertEquals(false, testStateManager1.nextState(1, 1));
		
		boolean[][] board2 = { 
				// If a cell has 1 living neighbor it dies of starvation.
				// next state for [1][1] should be false
				{false, false, false},
				{false, true, false},
				{false, true, false}
		};
		assertEquals(true, board2[1][1]);
		StateManager testStateManager2 = new StateManager(board2);
		assertEquals(false, testStateManager2.nextState(1, 1));
		
		boolean[][] board3 = {
				// if a living cell has 2 neighbors it lives
				// next state for [1][1] should be true
				{false, false, false},
				{true, true, false},
				{true, false, false}
		};
		assertEquals(true, board3[1][1]);
		StateManager testStateManager3 = new StateManager(board3);
		assertEquals(true, testStateManager3.nextState(1, 1));
		
		boolean[][] board4 = {
				// if a living cell has 3 neighbors it lives
				// next state for [1][1] should be true
				{false, false, false},
				{true, true, false},
				{true, true, false}
		};
		assertEquals(true, board4[1][1]);
		StateManager testStateManager4 = new StateManager(board4);
		assertEquals(true, testStateManager4.nextState(1, 1));
		
		
		// Test the borders
		// all tests use 1, 0
		boolean[][] board5 = {
				// if a living cell has 3 neighbors it lives
				// next state for [1][0] should be true
				{true, false, false},
				{true, true, false},
				{true, false, false}
		};
		assertEquals(true, board5[1][0]);
		StateManager testStateManager5 = new StateManager(board5);
		assertEquals(true, testStateManager5.nextState(1, 0));

		
		boolean[][] board6 = {
				// if cell is dead and ! 3 neighbors it stays dead
				// next state for [1][0] should be true
				{false, false, false},
				{false, false, false},
				{true, true, false}
		};
		assertEquals(false, board6[1][0]);	
		StateManager testStateManager6 = new StateManager(board6);
		int nc = testStateManager6.countNeighbors(1, 0);
		assertEquals(2, nc); // 1, 0 has 2 living neighbors
		assertEquals(false, testStateManager6.nextState(1, 0));

		
		boolean[][] board7 = { 
				// If a cell is alive and has 4 or more neighbors it dies
				// next state for [1][0] should be false
				{true, false, false},
				{true, true, false},
				{true, true, false}
		};
		assertEquals(true, board7[1][0]);
		StateManager testStateManager7 = new StateManager(board7);
		// TODO: figure out  why this doesn't work
		assertEquals(false, testStateManager7.nextState(1, 0));
		
		boolean[][] board8 = { 
				// If a cell has 1 living neighbor it dies of starvation.
				// next state for [1][0] should be false
				{false, false, false},
				{true, true, false},
				{false, false, false}
		};
		assertEquals(true, board8[1][0]);
		StateManager testStateManager8 = new StateManager(board8);
		// TODO: figure out  why this doesn't work
		assertEquals(false, testStateManager8.nextState(1, 0));
		
		boolean[][] board9 = {
				// if a living cell has 2 neighbors it lives
				// next state for [1][0] should be true
				{false, false, false},
				{true, true, false},
				{true, false, false}
		};
		assertEquals(true, board9[1][0]);
		StateManager testStateManager9 = new StateManager(board9);
		assertEquals(true, testStateManager9.nextState(1, 0));
		
		
		boolean[][] board10 = {
				// if a dead cell has 3 living neighbors it becomes live
				// next state for [1][0] should be true
				{true, false, false},
				{false, true, false},
				{true, false, false}
		};
		assertEquals(false, board10[1][0]);
		StateManager testStateManager10 = new StateManager(board10);
		assertEquals(true, testStateManager9.nextState(1, 0));		
	
	}
	
	@Test
	void nextGenerationTest() {
		
	}

}
