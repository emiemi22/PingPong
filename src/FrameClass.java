import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class FrameClass extends JFrame
{
    PanelClass panel;
    public FrameClass()
    {
        panel = new PanelClass();
        this.add(panel);
        this.setTitle("Ping-Pong");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // center

    }
}
