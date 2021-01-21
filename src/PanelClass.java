import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class PanelClass extends JPanel implements Runnable
{
    static final int GAME_WIDTH = 1000 ; // constanta - sa nu modificam aiurea
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.4999));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int DIAMETER_BALL = 20 ; // pixels
    static final int PALETA_WIDTH = 25 ;
    static final int PALETA_HEIGHT = 100 ;
    public Thread gameThread ;
    public Image image ;
    public Graphics graphics ;
    public Random random ;
    public Paleta paleta1 ;
    public Paleta paleta2;
    public Minge minge ;
    public Scor scor ;


    public PanelClass()
    {
        creazaMinge();
        creazaPaleta();
        scor = new Scor(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this) ;
        gameThread.start();
    }
    public void creazaMinge()
    {
        //random = new Random();
        minge = new Minge((GAME_WIDTH - DIAMETER_BALL)/2 , (GAME_HEIGHT - DIAMETER_BALL)/2, DIAMETER_BALL , DIAMETER_BALL );
    }
    public void creazaPaleta()
    {
        paleta1 = new Paleta(0,(GAME_HEIGHT/2)-(PALETA_HEIGHT/2) ,PALETA_WIDTH, PALETA_HEIGHT,1);//32 55 
        paleta2 = new Paleta(GAME_WIDTH-PALETA_WIDTH,(GAME_HEIGHT/2)-(PALETA_HEIGHT/2) ,PALETA_WIDTH, PALETA_HEIGHT,2);
    }

    public void paint (Graphics g) /// din clasa graphics
    {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        afiseaza(graphics);
        g.drawImage(image , 0 , 0 ,this);

    }
    public void move() // faster and smoothness
    {
        paleta1.move();
        paleta2.move();
        minge  .move();
    }
    public void verificaColiziune()
    {
        // sa nu iasa paletele din box
        if(paleta1.y <=0)
            paleta1.y= 0  ;

        if (paleta1.y >= (GAME_HEIGHT-PALETA_HEIGHT))
            paleta1.y = (GAME_HEIGHT-PALETA_HEIGHT);

        if(paleta2.y <=0)
            paleta2.y= 0  ;

        if (paleta2.y >= (GAME_HEIGHT-PALETA_HEIGHT))
            paleta2.y = (GAME_HEIGHT-PALETA_HEIGHT);

        // conditii pentru minge ca sa nu iasa din box si sa ricoseze
        if(minge.y<=0) /// partea de sus
        {
            minge.setYdirectie(-minge.pozitieY);
        }
        if(minge.y>=(GAME_HEIGHT-DIAMETER_BALL))///partea de jos -> o sa ajunga sa fie  pe - iar apoi da din nou in conditie si iese cu plus
        {
            minge.setYdirectie(-minge.pozitieY);
        }
        if (minge.x <= 0)
        {
            minge.setXdirectie(-minge.pozitieX);
        }
        if (minge.x >= (GAME_WIDTH-DIAMETER_BALL))
        {
            minge.setXdirectie(-minge.pozitieX);
        }
        //System.out.println(minge.x + " " + minge.y);

        // intersectia bila+paleta
        if (minge.intersects(paleta1)){
            minge.pozitieX = Math.abs(minge.pozitieX);
            //minge.pozitieX++;
            if (minge.pozitieY > 0)
                minge.pozitieY++;
            else
                minge.pozitieY--;
            minge.setXdirectie(minge.pozitieX);
            minge.setYdirectie(minge.pozitieY);
        }
        if (minge.intersects(paleta2)){
            minge.pozitieX = Math.abs(minge.pozitieX);
            minge.pozitieX++;
            if (minge.pozitieY > 0)
                minge.pozitieY++;
            else
                minge.pozitieY--;
            minge.setXdirectie(-minge.pozitieX);
            minge.setYdirectie(minge.pozitieY);
        }
        // 1 point per player
        if (minge.x <=0) { // player 2 scored
            scor.player2++;
            creazaPaleta();
            creazaMinge();
            System.out.println("p2 "+scor.player2);
        }
        if (minge.x >=(GAME_WIDTH-DIAMETER_BALL))
        { // player 2 scored
            scor.player1++;
            creazaPaleta();
            creazaMinge();
            System.out.println("p1 "+scor.player1);
        }

    }
    public void afiseaza(Graphics g)
    {
        paleta1.draw(g);
        paleta2.draw(g);
        minge  .draw(g);
        scor.draw(g);
    }
    public void run()//aici avem loop ul ceva basic
    {
        long lastTime = System.nanoTime();
        double nrTicks = 60.0 ;
        double ns = 1000000000/nrTicks;
        double delta = 0 ;

        while (true)
        {
            long now = System.nanoTime();
            delta+=(now - lastTime)/ns;
            lastTime = now ;
            if (delta >= 1)
            {
                move(); /// ca sa fie miscarea mai smooth

                verificaColiziune();
                repaint();

                delta--;
                //System.out.println(delta);
            }
        }
    }
    public class AL extends KeyAdapter
    {

        public void keyPressed(KeyEvent e)
        {
            paleta1.keyPressed(e);
            paleta2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            paleta1.keyReleased(e);
            paleta2.keyReleased(e);
        }
    }
}
