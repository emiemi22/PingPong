import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Scor extends Rectangle
{
    static int GAME_WIDTH ;
    static int GAME_HEIGHT ;
    int player1 ;
    int player2;

    public Scor(int latime , int inaltime){
        Scor.GAME_WIDTH = latime ;
        Scor.GAME_HEIGHT = inaltime ;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.drawLine(GAME_WIDTH/2 , 0 , GAME_WIDTH/2,GAME_HEIGHT);
        g.drawString(String.valueOf(player1) ,(GAME_WIDTH/2)-80 , 50 );
        g.drawString(String.valueOf(player2) ,(GAME_WIDTH/2)+50 , 50 );

    }

}
