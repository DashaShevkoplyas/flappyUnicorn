package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private Stage stage;
    private Scene scene;
    private Pane pane;
    private Field field;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        pane = new Pane();
        pane.setBackground(new Background(new BackgroundImage(new Image("background_main.jpg"),null,null, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        field = new Field(pane);
        scene = new Scene(pane,1240,680);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.getIcons().add(new Image("unicorn_outilne_1x.jpg"));
        stage.setResizable(false);
        stage.setTitle("FlappyUnicorn");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
