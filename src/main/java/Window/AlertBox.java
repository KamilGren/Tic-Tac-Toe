package Window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinHeight(250);
        window.setMinWidth(250);
        window.setMaxHeight(400);
        window.setMaxWidth(400);

        window.setTitle(title);

        Label label = new Label();
        label.setText(message);


        Button close = new Button("OK");
        close.setOnAction(e -> window.close());


        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, close);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

    }

}
