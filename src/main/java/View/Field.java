package View;


import Game.Figure;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Field extends Label
{
    public static BackgroundImage X_FIGURE = new BackgroundImage(new Image("file:src/main/resources/X.png",150,150,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    public static BackgroundImage O_FIGURE = new BackgroundImage(new Image("file:src/main/resources/O.png",150,150,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    public static String name;

    public Field(Board board, int row, int col)
    {
        super();

        setAlignment(Pos.CENTER);
        setMinSize(150, 150);
        setMaxSize(150, 150);
        setStyle("-fx-border-color: black;");

    }


}
