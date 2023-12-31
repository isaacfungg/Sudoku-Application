package test;

import computationlogic.GameGenerator;
import computationlogic.GameLogic;
import constants.GameState;
import org.testng.Assert;
import org.testng.annotations.Test;
import problemdomain.SudokuGame;

public class TestGameLogic {

    @Test
    public void testGetNewGameNotNull() {
        SudokuGame newGame = GameLogic.getNewGame();
        Assert.assertNotNull(newGame);
    }

    @Test
    public void testNewGameState() {
        SudokuGame newGame = GameLogic.getNewGame();
        Assert.assertEquals(newGame.getGameState(), GameState.NEW);
    }

    @Test
    public void testNewGameGridNotNull() {
        SudokuGame newGame = GameLogic.getNewGame();
        Assert.assertNotNull(newGame.getGridState());
    }

    @Test
    public void testCheckForCompletionComplete() {
        int[][] completedGrid = GameGenerator.getSolvedGame();
        Assert.assertEquals(GameLogic.checkForCompletion(completedGrid), GameState.COMPLETE);
    }

    @Test
    public void testCheckForCompletionActive() {
        int[][] activeGrid = GameGenerator.getNewGameGrid();
        Assert.assertEquals(GameLogic.checkForCompletion(activeGrid), GameState.ACTIVE);
    }

    @Test
    public void testSudokuIsValid() {
        int[][] validGrid = GameGenerator.getSolvedGame();
        Assert.assertFalse(GameLogic.sudokuIsInvalid(validGrid));
    }

    @Test
    public void testSudokuIsInvalid() {
        int[][] invalidGrid = GameGenerator.getNewGameGrid();
        // Making a deliberate invalid change to the grid
        invalidGrid[0][0] = invalidGrid[1][0];
        Assert.assertTrue(GameLogic.sudokuIsInvalid(invalidGrid));
    }

    @Test
    public void testTilesFilled() {
        int[][] filledGrid = GameGenerator.getSolvedGame();
        Assert.assertFalse(GameLogic.tilesAreNotFilled(filledGrid));
    }

    @Test
    public void testTilesNotFilled() {
        int[][] unfilledGrid = GameGenerator.getNewGameGrid();
        Assert.assertTrue(GameLogic.tilesAreNotFilled(unfilledGrid));
    }

    @Test
    public void testCompleteGameCheck() {
        int[][] completedGrid = GameGenerator.getSolvedGame();
        Assert.assertEquals(GameLogic.checkForCompletion(completedGrid), GameState.COMPLETE);
    }

    @Test
    public void testActiveGameCheck() {
        int[][] activeGrid = GameGenerator.getNewGameGrid();
        Assert.assertEquals(GameLogic.checkForCompletion(activeGrid), GameState.ACTIVE);
    }

    @Test
    public void testInvalidGameDueToRow() {
        int[][] grid = GameGenerator.getSolvedGame();
        grid[0][0] = grid[1][0];
        Assert.assertTrue(GameLogic.sudokuIsInvalid(grid));
    }

    @Test
    public void testInvalidGameDueToColumn() {
        int[][] grid = GameGenerator.getSolvedGame();
        grid[0][0] = grid[0][1];
        Assert.assertTrue(GameLogic.sudokuIsInvalid(grid));
    }

    @Test
    public void testInvalidGameDueToSquare() {
        int[][] grid = GameGenerator.getSolvedGame();
        grid[1][1] = grid[0][0];
        Assert.assertTrue(GameLogic.sudokuIsInvalid(grid));
    }

    @Test
    public void testGridWithEmptyCells() {
        int[][] grid = GameGenerator.getNewGameGrid();
        boolean emptyFound = false;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 0) {
                    emptyFound = true;
                    break;
                }
            }
            if (emptyFound) {
                break;
            }
        }
        Assert.assertTrue(emptyFound);
    }

    @Test
    public void testGridWithoutEmptyCells() {
        int[][] grid = GameGenerator.getSolvedGame();
        boolean emptyFound = false;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == 0) {
                    emptyFound = true;
                    break;
                }
            }
            if (emptyFound) {
                break;
            }
        }
        Assert.assertFalse(emptyFound);
    }

    @Test
    public void testInvalidNumberPlacement() {
        int[][] grid = GameGenerator.getSolvedGame();
        grid[0][0] = 10;
        Assert.assertTrue(GameLogic.sudokuIsInvalid(grid));
    }

    @Test
    public void testValidNumberPlacement() {
        int[][] grid = GameGenerator.getSolvedGame();
        int original = grid[0][0];
        grid[0][0] = (original % 9) + 1;
        Assert.assertFalse(GameLogic.sudokuIsInvalid(grid));
    }

    @Test
    public void testGameStateTransition() {
        SudokuGame game = GameLogic.getNewGame();
        Assert.assertEquals(game.getGameState(), GameState.NEW);
        game.setGameState(GameState.ACTIVE);
        Assert.assertEquals(game.getGameState(), GameState.ACTIVE);
    }


}
