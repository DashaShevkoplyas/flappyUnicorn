
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
    public static JFrame frame;
    public Random rand;
    public Timer timer;
    public static Visualisation vision;
    public Rectangle unicorn;
    public static ArrayList<Rectangle>columns;

    public Image layout;
    public Image background;
    public Image font;
    public int ticks, yMotion;
    public Sound music;
    public Boolean gameOver;
    public Boolean start;
    public int score;
    public static Keyboard keyboard;


    public flappyUnicorn(){

        rand = new Random();
     //   timer = new Timer(35, this);
        columns = new ArrayList<Rectangle>();

        music = new Sound("src\\sounds\\shooting-stars.wav");
        music.playSound();
        frame.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               makeFly();

           }
       });
        frame.addKeyListener(keyboard);
        restart();
        add();
       // timer.start();




    }
   public void restart(){
        start = false;
        gameOver  = false;
        score = 0;
        yMotion = 0;


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
   public void add(){
       layout = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//down_layout.png"));
       background = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//background_main.jpg"));
       font  = Toolkit.getDefaultToolkit().createImage(getClass().getResource("pics//font_2.jpg"));

       unicorn = new Rectangle(WIDTH / 2 - 50, HIGTH / 2 - 50, 30, 30);



       addColumn(true);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);
       addColumn(false);

   }



    public void makeFly() {
        if(gameOver){

            unicorn = new Rectangle(WIDTH / 2 - 50, HIGTH / 2 - 50, 30, 30);
            columns.clear();
           // restart();



            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            addColumn(false);
            restart();

            gameOver = false ;
        }
        if(!start){
            start = true;
        }else if(!gameOver){
            if(yMotion> 0){
                yMotion = 0;
            }
            yMotion-=10;
            //music.playSound();

        }

    }


     @Override
       public void actionPerformed(ActionEvent e) {
         int speed = 10;

         ticks++;
        if(start) {
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
                if((column.y == 0 && unicorn.x+unicorn.width/2 > column.x + column.width/2 - 10)&&
                        (unicorn.x+unicorn.width/2 < column.x + column.width/2 + 10)){
                    score++;

                }

                    if (unicorn.intersects(column)) {
                    gameOver = true;
                    unicorn.x = column.x - unicorn.width;
                }
            }

            if (unicorn.y > 550 || unicorn.y < 0) {

                gameOver = true;
            }
            if(gameOver){
                unicorn.y = HIGTH - 550- unicorn.height;


            }


        }

         vision.repaint();

     }
    public void paintColomn(Graphics g, Rectangle column){
        g.setColor(Color.white.darker().darker());
        g.fillRect(column.x, column.y, column.width, column.height);
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




}
