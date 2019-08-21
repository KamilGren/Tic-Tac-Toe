package View;

import Game.GameState;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends GridPane {

    private GameState gameState;



    public Board()
    {
        createBoard();
    }

    public void createBoard() {


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //add(new Rectangle(160, 160, Color.BLACK), col, row);
                add(new Field(this, col, row), col, row);
                //stanelo na tym aby zrobic tego Fielda w css
            }
        }

        add(ResultDisplay.display(gameState), 3, 0, 1, 2 );


    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;

    }

    public GameState getGameState(GameState gameState)
    {
        return gameState;
    }

}
