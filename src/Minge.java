import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Minge extends Rectangle {
    Random random ;
    int pozitieX;
    int pozitieY ;
    int viteza = 2;


    public Minge(int x , int y , int w, int h)
    {
        super(x,y,w,h);
        random = new Random();
        int randomXDir = random.nextInt(2);// 0 ->left 1->right
        if (randomXDir == 0)
        {
            randomXDir-- ;
        }
        setXdirectie(randomXDir*viteza);
        int randomYDir = random.nextInt(2);// 0 ->up 1->down
        if (randomYDir == 0)
        {
            randomYDir-- ;
        }
        setYdirectie(randomYDir*viteza);

    }

    public void setYdirectie( int ranYDir)
    {
        pozitieY  = ranYDir;
    }
    public void setXdirectie( int ranXDir)
    {
        pozitieX  = ranXDir;
    }
    public void move()
    {
        x += pozitieX;
        y += pozitieY;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);// h=w penntru ca e cerc
    }
}
