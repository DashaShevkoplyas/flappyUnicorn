
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class flappyUnicorn implements ActionListener{

    public static final int WIDTH = 1000, HIGTH = 800;
    public static flappyUnicorn flappyunicorn;
    private static JFrame frame;
    public Random rand;
    public Timer timer;
    public static Visualisation vision;
    public Rectangle unicorn;
    public ArrayList<Rectangle> columns;
    public Image layout;
    public Image background;
    public Image font;
    public int ticks, yMotion;
    private Sound music;
    public Boolean gameOver;
    public Boolean start;
    public int score;


    public flappyUnicorn(){
        rand = new Random();
        timer = new Timer(50, this);

        music = new Sound("src\\sounds\\shooting-stars.wav");
        music.playSound();
        frame.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               makeFly();

           }
       });
        restart();
        timer.start();



    }
    public void restart(){
        start = false;
        gameOver  = false;
        score = 0;

        add();
    }
   public void add(){
       layout = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//down_layout.png"));
       background = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//background_main.jpg"));
       font  = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//font_2.jpg"));

       unicorn = new Rectangle(WIDTH / 2 - 50, HIGTH / 2 - 50, 30, 30);
       columns = new ArrayList<Rectangle>();


       addColumn(true);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);

   }



    public void addColumn(boolean start){
        int space = 400;
        int width = 100;
        int heigth = 40 + rand.nextInt(space);


            if (start) {

                columns.add(new Rectangle(WIDTH + width + columns.size() * 200, WIDTH - heigth - 430, width, heigth));
                columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 200, 0, width, HIGTH - heigth - space));
            } else {
                columns.add(new Rectangle(columns.get(columns.size()-1 ).x + 600, WIDTH - heigth - 430, width, heigth));
                columns.add(new Rectangle(columns.get(columns.size() -1).x, 0, width, HIGTH - heigth - space));

        }
        }

    public void paintColomn(Graphics g, Rectangle column){
        g.setColor(Color.white.darker().darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
    private void makeFly() {
        if(gameOver){

            unicorn = new Rectangle(WIDTH / 2 - 50, HIGTH / 2 - 50, 30, 30);
            columns.clear();


            addColumn(true);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);

            gameOver = false ;
        }
        if(!start){
            start = true;
        }
    }


     @Override
       public void actionPerformed(ActionEvent e) {
         int speed = 10;

         ticks++;
        if(start = true) {
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }


            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;

            }


            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);
                }

            }
            unicorn.y += yMotion;
            for (Rectangle column : columns) {
                if (unicorn.intersects(column)) {
                    gameOver = true;
                } else {
                    getScore(score);
                }
            }

            if (unicorn.y > 430 || unicorn.y < 0) {
                gameOver = true;
            } else {
                getScore(score);
            }


        }

         vision.repaint();

     }

     public int getScore(int score){
        this.score = score;
        if (unicorn.y < 430 || unicorn.y > 0)
        score ++;
        return score;
     }



    public void repaint(Graphics g){

        g.drawImage(background, 0, 0, WIDTH, HIGTH, null);
        g.drawImage(layout, 0, 600, 1000, 200, null);
        g.drawImage(font, 0, 570, 1000, 40, null);
        g.setColor(Color.magenta.brighter().brighter().brighter().brighter());
        g.fillRect(unicorn.x, unicorn.y, unicorn.width, unicorn.height);

        for (Rectangle column: columns){
            paintColomn(g, column);
        }
    }

    public static void main(String[] args) throws IOException {

        frame  = new JFrame("Flappy Unicorn Game");
        vision = new Visualisation();
        flappyunicorn = new flappyUnicorn();
        frame.add(vision);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(WIDTH, HIGTH);
        frame.setResizable(false);
        frame.setVisible(true);
        //frame.addMouseListener(this);


    }


}
