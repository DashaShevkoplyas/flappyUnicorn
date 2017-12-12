import javax.swing.*;
import java.awt.*;

public class Visualisation extends JPanel {
    private static long serialVisionUID = 1L;
    private flappyUnicorn flappyunicorn;


   public Visualisation(){
        flappyunicorn = new flappyUnicorn();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
       flappyunicorn.repaint(g);
       // Graphics2D g2D = (Graphics2D) g;

        g.setColor(Color.WHITE);

        if (!flappyunicorn.start) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 150));
            g.setColor(Color.WHITE);
            g.drawString("Start game", 125, 350);
        } else {
            g.setFont(new Font("TimesRoman", Font.BOLD, 24));
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(flappyunicorn.score), 10, 630);
        }

        if (flappyunicorn.gameOver) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 150));
            g.setColor(Color.WHITE);

            g.drawString("Game Over", 125, 350);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString( "Your score is: ", 450, 400);
            g.drawString( Integer.toString(flappyunicorn.score),590, 400);

        }
    }
}
