package TicTacToe;


import Game.GameState;
import Game.Gaming;
import View.GameMenu;
import View.Menu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import View.Board;


public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GameState gameState = GameMenu.display();

        Board board =  new Board(gameState); // to co nas czeka czyli stworzenie Boarda, ktory bedzie tworzyl Fieldy (te labele w ktorym beda stawiane figury
        Menu menu = new Menu(board, primaryStage);
        board.setAlignment(Pos.CENTER);
        BorderPane border = new BorderPane();
        border.setCenter(board);
        border.setTop(menu);
        border.setStyle("-fx-border-color: cyan");


        Scene scene = new Scene(border, 1000, 850);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:src/main/resources/icon.jpg"));
        primaryStage.show();
        primaryStage.setTitle("Tic Tac Toe");

        Gaming game = new Gaming(board);





    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
