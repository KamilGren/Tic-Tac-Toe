package View;

import Game.GameState;
import Game.Gaming;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends GridPane {

    private GameState gameState;



    public Board(GameState gameState)
    {
        this.gameState = gameState;
        createBoard();
    }

    public void createBoard() {

        clean();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //add(new Rectangle(160, 160, Color.BLACK), col, row);
                add(new Field(this, col, row), col, row);
            }

        }
        add(ResultDisplay.display(gameState), 3, 0, 1, 2 );
    }

    private void clean() {
        while (getChildren().size() > 0) {
            getChildren().remove(0);
        }
    }
    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;

    }

    public GameState getGameState(GameState gameState)
    {
        return gameState;
    }

    public void checkState(Board board)
    {
        if(this.getGameState().isRoundOver())
        {
            getGameState().getRoundState().newRound();
            if(this.getGameState().isGameOver())
            {
                GameMenu.display();
            }
            createBoard();
            Gaming.setFieldsReady(board); // tutaj dodaje "naladowanie" nowych Fieldow funkcjonalnoscia
        }
    }

    public GameState getGameState() {
        return gameState;
    }




}
