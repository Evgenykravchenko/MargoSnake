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

    public GameWindow(Audio audio, JFrame mainMenu) {//Передаем аудио, если решим как добавить паузу, где будет настройка, чтобы менять громкость или выключать
        JFrame frame = new JFrame();
        this.mainMenu = mainMenu;
        frame.setTitle("Snake");
        frame.setBounds(0, 0, 480, 480);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.add(new GameField(frame, audio, mainMenu));
        frame.setVisible(true);
    }
}

