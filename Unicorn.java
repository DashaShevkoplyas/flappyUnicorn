package sample;

import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Unicorn {

    private KeyFrame unicornFly,createtrumpet,deletetrumpet;
    private Rectangle unicorn;
    private TranslateTransition translateTransition,translateTransition2,translateTransition3;
    private List<Rectangle> list,bottomlist;
    private long timestart;
    private Random r;
    private volatile int i = 0;
    private Label label;

    public Unicorn(Pane pane){
        list = new ArrayList();
        label = new Label("Score  0");
        label.setFont(new Font(30));
        label.setTranslateY(620);
        label.setTextFill(Color.BLACK);
        bottomlist = new ArrayList();
        r = new Random();
        timestart = System.currentTimeMillis();
        unicorn = new Rectangle(620,340,40,40);
        unicorn.setFill(new ImagePattern(new Image("unicorn-dab.png")));
        translateTransition = new TranslateTransition(Duration.seconds(0.2),unicorn);
        pane.getChildren().addAll(unicorn,label);
        unicornFly = new KeyFrame(Duration.seconds(0.0167), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    if (unicorn.getTranslateY()>=293)
                        unicorn.setTranslateY(293);
                    unicorn.setTranslateY(unicorn.getTranslateY() + 7);
                pane.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if ((unicorn.getTranslateY()-100)>-340){
                            translateTransition.setToY(unicorn.getTranslateY()-100);
                            translateTransition.play();
                        }else{
                            translateTransition.setToY(unicorn.getTranslateY() - (340+unicorn.getTranslateY()));
                            translateTransition.play();
                        }
                    }
                });
                if ((!list.isEmpty())&&(!bottomlist.isEmpty()))
                    for (Rectangle list:list)
                        if ((list.intersects(unicorn.getBoundsInLocal())))
                            System.out.print("Yes");
            }
        });
        createtrumpet = new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    list.add(new Rectangle(1240,0,80,r.nextInt(500)));
                    list.get(list.size()-1).setFill(new ImagePattern(new Image("pipe-south.png")));
                    pane.getChildren().add(list.get(list.size()-1));
                    bottomlist.add(new Rectangle(1240,list.get(list.size()-1).getHeight()+200,80,680));
                    bottomlist.get(list.size()-1).setFill(new ImagePattern(new Image("pipe-north.png")));
                    pane.getChildren().add(bottomlist.get(bottomlist.size()-1));
                    translateTransition2 = new TranslateTransition(Duration.seconds(8),list.get(list.size()-1));
                    translateTransition2.setToX(-1320);
                    translateTransition2.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            pane.getChildren().remove(list.get(0));
                            list.remove(0);
                        }
                    });
                    translateTransition2.play();
                    translateTransition3 = new TranslateTransition(Duration.seconds(8),bottomlist.get(bottomlist.size()-1));
                    translateTransition3.setToX(-1320);
                    translateTransition3.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            pane.getChildren().remove(bottomlist.get(0));
                            bottomlist.remove(0);
                        }
                    });
                    translateTransition3.play();
            }
        });
        deletetrumpet = new KeyFrame(Duration.seconds(0.0167), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int z = 0;z<list.size();z++){
                    list.get(z).setTranslateX(list.get(z).getTranslateY()-14);
                    if (list.get(z).getTranslateX() == -1320)
                        list.remove(z);
                }
                for (int z = 0;z<bottomlist.size();z++){
                    bottomlist.get(z).setTranslateX(bottomlist.get(z).getTranslateY()-14);
                    if (bottomlist.get(z).getTranslateX() == -1320)
                        bottomlist.remove(z);
                }
            }
        });
    }

    public KeyFrame getDeletetrumpet() {
        return deletetrumpet;
    }

    public KeyFrame getUnicornFly(){return unicornFly;}

    public KeyFrame getCreatetrumpet() {
        return createtrumpet;
    }
}
