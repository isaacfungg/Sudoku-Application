package test;

import computationlogic.GameGenerator;
import computationlogic.GameLogic;
import computationlogic.SudokuSolver;
import org.testng.Assert;
import org.testng.annotations.Test;
import problemdomain.SudokuGame;

public class TestGameGenerator {

    @Test
    public void testGridSize() {
        int[][] newGameGrid = GameGenerator.getNewGameGrid();
        Assert.assertEquals(newGameGrid.length, SudokuGame.GRID_BOUNDARY);

        for (int[] row : newGameGrid) {
            Assert.assertEquals(row.length, SudokuGame.GRID_BOUNDARY);
        }
    }

    @Test
    public void testPuzzleSolvable() {
        int[][] newGameGrid = GameGenerator.getNewGameGrid();
        Assert.assertTrue(SudokuSolver.puzzleIsSolvable(newGameGrid));
    }

    @Test
    public void testNonEmptyCells() {
        int[][] newGameGrid = GameGenerator.getNewGameGrid();
        int nonEmptyCells = 0;

        for (int i = 0; i < SudokuGame.GRID_BOUNDARY; i++) {
            for (int j = 0; j < SudokuGame.GRID_BOUNDARY; j++) {
                if (newGameGrid[i][j] != 0) {
                    nonEmptyCells++;
                }
            }
        }
        System.out.println("test");
        Assert.assertTrue(nonEmptyCells > 0);
    }

    @Test
    public void testValidityOfInitialState() {
        int[][] newGameGrid = GameGenerator.getNewGameGrid();
        Assert.assertFalse(GameLogic.sudokuIsInvalid(newGameGrid));
    }


}
