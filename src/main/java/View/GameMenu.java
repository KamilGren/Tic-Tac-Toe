package View;

import Game.Figure;
import Game.GameState;
import Game.Player;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class GameMenu {


    public static GameState display() {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        GridPane GridPane = new GridPane();
        GridPane.setHgap(10);
        GridPane.setVgap(10);
        GridPane.setPadding(new Insets(10, 10, 10, 10));



        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(250);
        primaryStage.setMaxHeight(650);
        primaryStage.setMaxWidth(650);




        Label player1NameLabel = new Label("Player one name: ");
        GridPane.setConstraints(player1NameLabel, 0, 0);

        ChoiceBox<String> figureBox1 = new ChoiceBox<>();
        figureBox1.getItems().addAll("O", "X");
        figureBox1.setValue("O");
        GridPane.setConstraints(figureBox1, 1,1);

        ChoiceBox<String> figureBox2 = new ChoiceBox<>();
        figureBox2.getItems().addAll("O", "X");
        figureBox2.setValue("X");
        GridPane.setConstraints(figureBox2, 3,1);

        Label player1Figure = new Label("Choose figure: ");
        GridPane.setConstraints(player1Figure, 0, 1);

        Label player2Figure = new Label("Choose figure: ");
        GridPane.setConstraints(player2Figure, 2, 1);

        Label player2NameLabel = new Label("Player two name: ");
        GridPane.setConstraints(player2NameLabel, 2, 0);

        TextField player1TextField = new TextField("name");
        GridPane.setConstraints(player1TextField, 1, 0);

        TextField player2TextField = new TextField("name");
        GridPane.setConstraints(player2TextField, 3, 0);

        figureBox1.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if (newValue.equals("X")) {
                figureBox2.setValue("O");
            } else if (newValue.equals("O")) {
                figureBox2.setValue("X");
            }
        });

        // no wiec pierw wybieramy na jakim obiekcie bazujemy, potem wybieramy zaznaczamy typ selekcji, a potem
        // wlasciwosci przedmiotu i do tego dodajemy nasluchiwacz, ktory przetrzymuje wartosc dotychczasowa
        // i nowa i dajemy instrukcje warunkowe co sie ma dziac

        figureBox2.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if (newValue.equals("O")) {
                figureBox1.setValue("X");
            } else if (newValue.equals("X")) {
                figureBox1.setValue("O");
            }
        });


        Label lPlayer1IsComputer = new Label("Is computer: ");
        GridPane.setConstraints(lPlayer1IsComputer, 0, 4);

        CheckBox cPlayer1IsComputer = new CheckBox();
        GridPane.setConstraints(cPlayer1IsComputer, 1, 4);


        Label lPlayer2IsComputer = new Label("Is computer: ");
        GridPane.setConstraints(lPlayer2IsComputer, 2, 4);

        CheckBox cPlayer2IsComputer = new CheckBox();
        GridPane.setConstraints(cPlayer2IsComputer, 3, 4);

        Label lRoundCount = new Label("Number of rounds: ");
        GridPane.setConstraints(lRoundCount, 1, 6);

        ChoiceBox<Integer> roundBox = new ChoiceBox<>();
        roundBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
        roundBox.setValue(3);
        GridPane.setConstraints(roundBox, 2,6);


        Button bNewGame = new Button("Play game!");
        GridPane.setConstraints(bNewGame, 2, 7, 10, 5);
        bNewGame.setOnAction(event -> primaryStage.close()); // fajne i proste, ustawianie akcji na przycisku
        // tak samo moglbym ustawic jakas inna akcje, np. wypisanie czegos



        GridPane.getChildren().addAll(player1NameLabel, player2NameLabel, player1TextField, player2TextField, figureBox1,
                figureBox2, player1Figure, player2Figure, cPlayer1IsComputer, lPlayer1IsComputer, lPlayer2IsComputer,
                roundBox, cPlayer2IsComputer, lRoundCount, bNewGame);

        Scene scene = new Scene(GridPane);


        primaryStage.setScene(scene);
        primaryStage.setTitle("New Game");
        primaryStage.showAndWait(); // wszystko co sie stanie po tej instrukcji dzieje sie po tym jak ten ekran zniknie



        System.out.println("Is player two is a computer?" + cPlayer2IsComputer.isSelected());



        Player playerOne = new Player(player1TextField.getText(), Figure.O, cPlayer1IsComputer.isSelected(), 0);
        Player playerTwo = new Player(player2TextField.getText(), Figure.X, cPlayer1IsComputer.isSelected(), 0);

        if(figureBox1.getValue().equals('X'))
        {
             playerOne = new Player(player1TextField.getText(), Figure.X, cPlayer1IsComputer.isSelected(), 0);
             playerTwo = new Player(player2TextField.getText(), Figure.O, cPlayer1IsComputer.isSelected(), 0);
        }


        return new GameState(playerOne, playerTwo, roundBox.getValue());


















    }


}

