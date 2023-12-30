package problemdomain.userinterface.logic;

import computationlogic.GameLogic;
import constants.GameState;
import constants.Messages;
import problemdomain.IStorage;
import problemdomain.SudokuGame;
import problemdomain.userinterface.IUserInterfaceContract;

import java.io.IOException;


public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getGridState();
            newGridState[x][y] = input;

            gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);

            storage.updateGameData(gameData);

            view.updateSquare(x, y, input);

            if (gameData.getGameState() == GameState.COMPLETE) {
                view.showDialog(Messages.GAME_COMPLETE);
            }

        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(GameLogic.getNewGame());

            view.updateBoard(storage.getGameData());
        } catch (IOException e) {
            view.showError(Messages.ERROR);
        }
    }
}
