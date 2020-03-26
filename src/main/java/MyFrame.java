import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JFrame gameFrame = new JFrame();
    private Music music = new Music();

    public MyFrame() {
        JButton playBtn;
        JButton scoreBtn;
        JButton settingsBtn;
        JButton exitBtn;
        JLabel label;

        gameFrame.setLayout(null);
        gameFrame.setTitle("Snake");
        gameFrame.setBounds(0, 0, 480, 480);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playBtn = new JButton("Play");
        playBtn.setLocation(40, 90);
        playBtn.setSize(400, 50);
        playBtn.setForeground(Color.pink);
        playBtn.addActionListener(this::actionPlay);

        scoreBtn = new JButton("Score");
        scoreBtn.setLocation(40, 150);
        scoreBtn.setSize(400, 50);
        scoreBtn.setForeground(Color.pink);
        scoreBtn.addActionListener(this::actionShowScore);

        settingsBtn = new JButton("Settings");
        settingsBtn.setLocation(40, 210);
        settingsBtn.setSize(400, 50);
        settingsBtn.setForeground(Color.pink);
        settingsBtn.addActionListener(this::actionSettings);

        exitBtn = new JButton("Exit");
        exitBtn.setLocation(40, 270);
        exitBtn.setSize(400, 50);
        exitBtn.setForeground(Color.pink);
        exitBtn.addActionListener(this::actionExit);

        label = new JLabel("MENU");
        label.setLocation(gameFrame.getSize().width / 2 - 17, 10);
        label.setSize(300, 70);
        label.setForeground(Color.GREEN);


        gameFrame.getContentPane().add(playBtn);
        gameFrame.getContentPane().add(scoreBtn);
        gameFrame.getContentPane().add(settingsBtn);
        gameFrame.getContentPane().add(exitBtn);
        gameFrame.getContentPane().add(label);
        gameFrame.getContentPane().setBackground(Color.orange);
        gameFrame.setVisible(true);

    }

    public void actionPlay(ActionEvent e) {
        music.click();
        gameFrame.dispose();
        new GameWindow();
    }

    public void actionShowScore(ActionEvent e) {
        music.click();
        gameFrame.dispose();
        new ScorePage();
    }

    public void actionSettings(ActionEvent e) {
        music.click();
    }

    public void actionExit(ActionEvent e) {
        music.click();
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
