import org.w3c.dom.css.Rect;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Paleta extends Rectangle {

    int selector ; // 0 pt paleta 1 / 1 pt paleta 2
    int moveSpeed = 8;
    int yPosition ;


    public Paleta(int x , int y , int LATIME , int INALTIME , int id)
    {
        super (x,y,LATIME,INALTIME); /// dimensiunile paletelor vor fi trimise direct in superClass
        this.selector = id ;
    }
    public void keyPressed(KeyEvent e)
    {
        if (selector == 1)
        {
            if (e.getKeyCode() == KeyEvent.VK_W){
                setDirectie(-moveSpeed);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_S){
                setDirectie(moveSpeed);
                move();
            }
        }
        else
        {
            if (e.getKeyCode() == KeyEvent.VK_UP){
                setDirectie(-moveSpeed);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                setDirectie(moveSpeed);
                move();
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {
        if (selector == 1)
        {
            if (e.getKeyCode() == KeyEvent.VK_W){
                setDirectie(0);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_S){
                setDirectie(0);
                move();
            }
        }
        else
        {
            if (e.getKeyCode() == KeyEvent.VK_UP){
                setDirectie(0);
                move();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                setDirectie(0);
                move();
            }
        }
    }
    public void setDirectie( int yDir)
    {
        yPosition = yDir;
    }
    public void move()
    {
        y = y + yPosition; /// actualizam pozitia dreptunghilui y->superClass
    }
    public void draw(Graphics g) /// grafica pentru palete
    {
        if (selector == 1) // prima paleta / primul jucator
            g.setColor(Color.red);
        else  // a ld doile jucator paleta
            g.setColor(Color.green);
        g.fillRect(x,y,width,height);
    }

}
