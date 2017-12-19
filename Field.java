package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class Field {

    private Label label;
    private Button buttonstart,buttonexit;
    private Rectangle rectangle;
    private Start start;

    public Field(Pane pane){
        start = new Start();
        buttonexit = new Button("Exit");
        buttonexit.setLayoutY(470);
        buttonexit.setLayoutX(625);
        buttonexit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        buttonstart = new Button("Start");
        buttonstart.setLayoutX(620);
        buttonstart.setLayoutY(420);
        label = new Label("Flappy Unicorn");
        label.setTextFill(Color.GOLD);
        label.setFont(new Font(180));
        label.setTranslateX(20);
        label.setTranslateY(100);
        rectangle = new Rectangle(0,0,1240,680);
        rectangle.setOpacity(0.5);
        buttonstart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rectangle.setOpacity(0);
                pane.getChildren().removeAll(buttonexit,buttonstart,label);
                start.startGame(pane);
            }
        });
        pane.getChildren().addAll(rectangle,buttonexit,buttonstart,label);
    }
}
