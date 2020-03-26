import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.plaf.basic.BasicGraphicsUtils.drawString;

public class GameWindow extends JFrame{
    public static void main(String[] args) {
        new MyFrame();
    }
    public GameWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
        frame.setBounds(0, 0, 480, 480);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new GameField(frame));
        frame.setVisible(true);
    }
}

