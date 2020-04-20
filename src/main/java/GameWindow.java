import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.plaf.basic.BasicGraphicsUtils.drawString;

public class GameWindow extends JFrame {
    private JFrame mainMenu;

    public static void main(String[] args) {
        new MyFrame();
    }

    public GameWindow(JFrame mainMenu, int x, int y) {
        JFrame frame = new JFrame();
        this.mainMenu = mainMenu;
        frame.setTitle("Snake");
        frame.setBounds(x, y, 688, 688);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().add(new GameField(frame, mainMenu));
        frame.setVisible(true);
    }
}

