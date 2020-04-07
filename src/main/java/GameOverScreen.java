import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JFrame {

    Music music = new Music();
    JFrame gameOver = new JFrame();
    JFrame mainMenu;
    public GameOverScreen(int score, JFrame mainMenu, int x, int y) {
        this.mainMenu = mainMenu;

        JButton restartBtn;
        JButton mainMenuBtn;
        JButton exitBtn;
        WorkWithFile workWithFile = new WorkWithFile();
        JLabel gameOverLbl = new JLabel("GAME OVER");
        JLabel yourScoreLbl = new JLabel("You're score is " + score);
        JLabel maxScoreLbl = new JLabel("Champion score is " + workWithFile.getData("src/main/resources/data/score.txt"));

        gameOver.setLayout(null);
        gameOver.setTitle("Snake");
        gameOver.setBounds(x, y, 480, 480);
        gameOver.setBackground(Color.darkGray);
        gameOver.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        restartBtn = new JButton("Restart");
        restartBtn.setLocation(40, 90);
        restartBtn.setSize(400, 50);
        restartBtn.setForeground(Color.pink);
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.click();
                gameOver.dispose();
                new GameWindow(mainMenu, gameOver.getX(), gameOver.getY());
            }
        });

        mainMenuBtn = new JButton("Menu");
        mainMenuBtn.setLocation(40, 150);
        mainMenuBtn.setSize(400, 50);
        mainMenuBtn.setForeground(Color.pink);
        mainMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.click();
                gameOver.dispose();
                mainMenu.setLocation(gameOver.getX(), gameOver.getY());
                mainMenu.setVisible(true);
            }
        });

        exitBtn = new JButton("Exit");
        exitBtn.setLocation(40, 210);
        exitBtn.setSize(400, 50);
        exitBtn.setForeground(Color.pink);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                music.click();
                workWithFile.writeData("false", "src/main/resources/data/isSoundPlay.txt");
                System.exit(0);
            }
        });

        gameOverLbl.setLocation(gameOver.getSize().width / 2 - 30, 0);
        gameOverLbl.setSize(300, 70);
        gameOverLbl.setForeground(Color.RED);

        yourScoreLbl.setLocation(gameOver.getSize().width / 2 - 45, 20);
        yourScoreLbl.setSize(300, 70);
        yourScoreLbl.setForeground(Color.ORANGE);

        maxScoreLbl.setLocation(gameOver.getSize().width / 2 - 55, 40);
        maxScoreLbl.setSize(300, 70);
        maxScoreLbl.setForeground(Color.ORANGE);

        gameOver.getContentPane().add(restartBtn);
        gameOver.getContentPane().add(mainMenuBtn);
        gameOver.getContentPane().add(exitBtn);

        gameOver.getContentPane().add(gameOverLbl);
        gameOver.getContentPane().add(yourScoreLbl);
        gameOver.getContentPane().add(maxScoreLbl);
        gameOver.setVisible(true);

    }
/*
    public void actionRestart(ActionEvent e) {
        music.click();
        gameOver.dispose();
        new GameWindow(mainMenu, gameOver.getX(), gameOver.getY());
    }

    public void actionMainMenu(ActionEvent e) {
        music.click();
        gameOver.dispose();
        mainMenu.setLocation(gameOver.getX(), gameOver.getY());
        mainMenu.setVisible(true);
    }

    public void actionExit(ActionEvent e) {
        music.click();
        workWithFile.writeData("false", "src/main/resources/data/isSoundPlay.txt");
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
*/
}
