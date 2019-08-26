package View;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Field extends Label
{
    public static final Image X_FIGURE = new Image("file:src/main/resources/X.png");
    public static final Image O_FIGURE = new Image("file:src/maim/resources/O.png");
    public static String name;

    public Field(Board board, int row, int col, String name)
    {
        super();
        this.name = name;

        setAlignment(Pos.CENTER);
        setMinSize(150, 150);
        setMaxSize(150, 150);
        setStyle("-fx-border-color: black;");
        getStylesheets().add("/View.css");






    }

}
