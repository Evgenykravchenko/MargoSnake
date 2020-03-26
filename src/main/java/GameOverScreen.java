import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame implements ActionListener {

    Music music = new Music();
    JFrame gameFrame = new JFrame();
    Audio audio;
    WorkWithFile workWithFile = new WorkWithFile();
    public GameOverScreen(int score, Audio audio) {
        this.audio = audio;
        JButton restartBtn;
        JButton mainMenuBtn;
        JButton exitBtn;
        WorkWithFile workWithFile = new WorkWithFile();
        JLabel gameOverLbl = new JLabel("GAME OVER");
        JLabel yourScoreLbl = new JLabel("You're score is " + score);
        JLabel maxScoreLbl = new JLabel("Champion score is " + workWithFile.getData("src/main/resources/data/score.txt"));

        gameFrame.setLayout(null);
        gameFrame.setTitle("Snake");
        gameFrame.setBounds(0, 0, 480, 480);
        gameFrame.setBackground(Color.darkGray);
        gameFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        restartBtn = new JButton("Restart");
        restartBtn.setLocation(40, 90);
        restartBtn.setSize(400, 50);
        restartBtn.setForeground(Color.pink);
        restartBtn.addActionListener(this::actionRestart);

        mainMenuBtn = new JButton("Menu");
        mainMenuBtn.setLocation(40, 150);
        mainMenuBtn.setSize(400, 50);
        mainMenuBtn.setForeground(Color.pink);
        mainMenuBtn.addActionListener(this::actionMainMenu);

        exitBtn = new JButton("Exit");
        exitBtn.setLocation(40, 210);
        exitBtn.setSize(400, 50);
        exitBtn.setForeground(Color.pink);
        exitBtn.addActionListener(this::actionExit);

        gameOverLbl.setLocation(gameFrame.getSize().width / 2 - 30, 0);
        gameOverLbl.setSize(300, 70);
        gameOverLbl.setForeground(Color.RED);

        yourScoreLbl.setLocation(gameFrame.getSize().width / 2 - 45, 20);
        yourScoreLbl.setSize(300, 70);
        yourScoreLbl.setForeground(Color.ORANGE);

        maxScoreLbl.setLocation(gameFrame.getSize().width / 2 - 55, 40);
        maxScoreLbl.setSize(300, 70);
        maxScoreLbl.setForeground(Color.ORANGE);

        gameFrame.getContentPane().add(restartBtn);
        gameFrame.getContentPane().add(mainMenuBtn);
        gameFrame.getContentPane().add(exitBtn);

        gameFrame.getContentPane().add(gameOverLbl);
        gameFrame.getContentPane().add(yourScoreLbl);
        gameFrame.getContentPane().add(maxScoreLbl);
        gameFrame.setVisible(true);

    }

    public void actionRestart(ActionEvent e) {
        music.click();
        gameFrame.dispose();
        new GameWindow(audio);
    }

    public void actionMainMenu(ActionEvent e) {
        music.click();
        gameFrame.dispose();
        new MyFrame();
    }

    public void actionExit(ActionEvent e) {
        music.click();
        workWithFile.writeData("false", "src/main/resources/data/isSoundPlay.txt");
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
