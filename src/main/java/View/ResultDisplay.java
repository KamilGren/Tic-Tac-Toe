package View;

import Game.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ResultDisplay {

    public static VBox display(GameState gameState)
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


        //System.out.println(gameState.getPlayerOne().getPlayerName());

        resultDisplay.getChildren().addAll(Score, Round, lPlayer1WonRounds, lPlayer2WonRounds, roundsNeededToWin);



        return resultDisplay;

    }
}
