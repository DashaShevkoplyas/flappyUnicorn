import javax.swing.*;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) throws IOException {

        flappyUnicorn.flappyunicorn = new flappyUnicorn();

        flappyUnicorn.frame  = new JFrame("Flappy Unicorn Game");
        flappyUnicorn.vision = new Visualisation();
        flappyUnicorn.keyboard = new Keyboard();

        flappyUnicorn.frame.add(flappyUnicorn.vision);
        flappyUnicorn.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flappyUnicorn.frame.setSize(flappyUnicorn.WIDTH, flappyUnicorn.HIGTH);

        flappyUnicorn.frame.setResizable(false);
        flappyUnicorn.frame.setVisible(true);



    }
}
