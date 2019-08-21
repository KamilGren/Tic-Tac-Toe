package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class Menu extends HBox {

    public Menu(Board board, Stage primaryStage)
    {
        setPadding(new Insets(15, 12, 10, 12));
        setSpacing(10);
        setStyle("-fx-background-color: orange");


        Button NewGame = new Button("New Game");
        NewGame.setPrefSize(100, 20);
        Button LoadGame = new Button("Load Game");
        LoadGame.setPrefSize(100, 20);
        Button SaveGame = new Button("Save Game");
        SaveGame.setPrefSize(100, 20);

        getChildren().addAll(NewGame, LoadGame, SaveGame);

    }


}
