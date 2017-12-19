package sample;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class Start {

    private Timeline timeline,timeline2;
    private Unicorn keyFrames;

    public void startGame(Pane pane){
        timeline = new Timeline();
        timeline2 = new Timeline();
        keyFrames = new Unicorn(pane);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.getKeyFrames().add(keyFrames.getCreatetrumpet());
        timeline.getKeyFrames().addAll(keyFrames.getUnicornFly(),keyFrames.getDeletetrumpet());
        timeline.play();
        timeline2.play();
    }
}
