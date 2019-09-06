package View;

import Game.GameState;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultDisplay {

    private final static Logger LOG = LogManager.getLogger();
    public static BackgroundImage leftArrowToUndo = new BackgroundImage(new Image("file:src/main/resources/leftarrow.png",98,56,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    public static VBox display(GameState gameState, Board board)
    {
        VBox resultDisplay = new VBox();
        resultDisplay.setAlignment(Pos.CENTER);
        resultDisplay.setPadding(new Insets(10, 10, 10 , 30));

        Label Score = new Label();
        Score.setFont(Font.font("Cambria", 40));
        Score.setTextFill(Color.GREEN);
        Score.setText("Name: ");

        Label Round = new Label();
        Round.setFont(Font.font("Cambria", 24));
        Round.setTextFill(Color.GREEN);
        Round.setText("Round: ");

        Label lPlayer1WonRounds = new Label();
        Round.setFont(Font.font("Cambria", 24));
        Round.setTextFill(Color.GREEN);
        Round.setText(": ");

        Label lPlayer2WonRounds = new Label();
        Round.setFont(Font.font("Cambria", 24));
        Round.setTextFill(Color.GREEN);
        Round.setText("Round: ");

        Label roundsNeededToWin = new Label();
        Round.setFont(Font.font("Cambria", 24));
        Round.setTextFill(Color.GREEN);
        Round.setText("Rounds needed to win: ");


        resultDisplay.getChildren().addAll(Score, Round, lPlayer1WonRounds, lPlayer2WonRounds, roundsNeededToWin);

        if(GameMenu.undoAble == true)
        {
            Label lUndo = new Label();
            lUndo.setAlignment(Pos.CENTER);
            lUndo.setMinSize(98, 55);
            lUndo.setMaxSize(98, 55);

            lUndo.setBackground(new Background(leftArrowToUndo));
            resultDisplay.getChildren().add(lUndo);

            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event)
                {
                    LOG.info("UNDO IS IN ACTION");
                    LOG.info("ilosc wykonanych ruchow: " + board.getGameState().getRoundState().getNumberOfMoves());
                    board.getGameState().getRoundState().undoMove(board, board.getGameState().getRoundState().getField(board.getGameState().getRoundState().getNumberOfMoves()-1, board));
                    //(planszaktorajestzaladowana(PKJZ).undoMove(PKJZ, PKJZ.getField(PKJZ.iloscWykonanychRuchowWTejRundzie,PKJZ). // dobra niby cos jasniej ale jak cos to pytac
                }
            };

            lUndo.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


        }

        return resultDisplay;

    }
}
