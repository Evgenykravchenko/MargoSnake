import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame extends JFrame {
    public static JFrame gameFrame = new JFrame();
    public static Audio audio = new Audio(0.8, "src/main/resources/music/mainMenuSound.wav");
    public static  Audio audioBtn = new Audio(0.8, "src/main/resources/music/btnClickSound.wav");
    public static  Audio playMusic = new Audio(0.8, "src/main/resources/music/playMusic.wav");
    private static WorkWithFile workWithFile = new WorkWithFile();

    public MyFrame() {
        JButton playBtn;
        JButton scoreBtn;
        JButton settingsBtn;
        JButton exitBtn;
        JLabel label;

        gameFrame.setLayout(null);
        gameFrame.setTitle("Snake");
        gameFrame.setBounds(0, 0, 480, 480);
        gameFrame.setBackground(Color.orange);
        gameFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        playBtn = new JButton("Play");
        playBtn.setLocation(40, 90);
        playBtn.setSize(400, 50);
        playBtn.setForeground(Color.pink);
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                gameFrame.dispose();
                new GameWindow(gameFrame, gameFrame.getX(), gameFrame.getY());
            }
        });

        scoreBtn = new JButton("Score");
        scoreBtn.setLocation(40, 150);
        scoreBtn.setSize(400, 50);
        scoreBtn.setForeground(Color.pink);
        scoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createScorePage(gameFrame);
            }
        });

        settingsBtn = new JButton("Settings");
        settingsBtn.setLocation(40, 210);
        settingsBtn.setSize(400, 50);
        settingsBtn.setForeground(Color.pink);
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createSettingsPage(gameFrame);
            }
        });

        exitBtn = new JButton("Exit");
        exitBtn.setLocation(40, 270);
        exitBtn.setSize(400, 50);
        exitBtn.setForeground(Color.pink);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                System.exit(0);
            }
        });

        label = new JLabel("MENU");
        label.setLocation(gameFrame.getSize().width / 2 - 17, 10);
        label.setSize(300, 70);
        label.setForeground(Color.GREEN);

        audio.play();
        audio.setVolume();
        audio.repeat();

        gameFrame.setLocationRelativeTo(null);

        gameFrame.getContentPane().add(playBtn);
        gameFrame.getContentPane().add(scoreBtn);
        gameFrame.getContentPane().add(settingsBtn);
        gameFrame.getContentPane().add(exitBtn);
        gameFrame.getContentPane().add(label);
        gameFrame.setVisible(true);

    }

    /*public void actionPlay(ActionEvent e) {
        audioBtn.sound();
        audioBtn.setVolume();
        gameFrame.dispose();
        new GameWindow(audio, gameFrame, gameFrame.getX(), gameFrame.getY());
    }
    public void actionShowScore(ActionEvent e) {
        audioBtn.sound();
        audioBtn.setVolume();
        createScorePage(gameFrame);
    }

    public void actionSettings(ActionEvent e) {
        audioBtn.sound();
        audioBtn.setVolume();
        gameFrame.dispose();
        new SettingsPage(audio, gameFrame);
    }

    public void actionExit(ActionEvent e) {
        audioBtn.sound();
        audioBtn.setVolume();
        workWithFile.writeData("false", "src/main/resources/data/isSoundPlay.txt");
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    */


    public void createScorePage(JFrame gameFrame) {
        gameFrame.getContentPane().removeAll();

        JButton backBtn = new JButton("Back");
        JLabel scoreTittleLbl = new JLabel("Score");
        JLabel maxScoreLbl = new JLabel("Champion score is " + workWithFile.getData("src/main/resources/data/score.txt"));

        gameFrame.setBackground(Color.darkGray);

        backBtn.setLocation(gameFrame.getSize().width - 120, gameFrame.getSize().height - 100);
        backBtn.setSize(100, 50);
        backBtn.setForeground(Color.pink);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createMainMenu(gameFrame);
            }
        });

        maxScoreLbl.setLocation(gameFrame.getSize().width / 2 - 200, 100);
        maxScoreLbl.setFont(new Font("Serif", Font.PLAIN, 45));
        maxScoreLbl.setSize(400, 200);
        maxScoreLbl.setForeground(Color.PINK);

        scoreTittleLbl.setLocation(gameFrame.getSize().width / 2 - 50, 0);
        scoreTittleLbl.setSize(400, 100);
        scoreTittleLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        scoreTittleLbl.setForeground(Color.RED);

        gameFrame.getContentPane().add(backBtn);
        gameFrame.getContentPane().add(scoreTittleLbl);
        gameFrame.getContentPane().add(maxScoreLbl);

        gameFrame.getContentPane().repaint();
    }

    public void createMainMenu(JFrame gameFrame) {
        gameFrame.getContentPane().removeAll();

        JButton playBtn;
        JButton scoreBtn;
        JButton settingsBtn;
        JButton exitBtn;
        JLabel label;

        gameFrame.setBackground(Color.orange);
        playBtn = new JButton("Play");
        playBtn.setLocation(40, 90);
        playBtn.setSize(400, 50);
        playBtn.setForeground(Color.pink);
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                gameFrame.dispose();
                new GameWindow(gameFrame, gameFrame.getX(), gameFrame.getY());
            }
        });

        scoreBtn = new JButton("Score");
        scoreBtn.setLocation(40, 150);
        scoreBtn.setSize(400, 50);
        scoreBtn.setForeground(Color.pink);
        scoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createScorePage(gameFrame);
            }
        });

        settingsBtn = new JButton("Settings");
        settingsBtn.setLocation(40, 210);
        settingsBtn.setSize(400, 50);
        settingsBtn.setForeground(Color.pink);
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createSettingsPage(gameFrame);
            }
        });

        exitBtn = new JButton("Exit");
        exitBtn.setLocation(40, 270);
        exitBtn.setSize(400, 50);
        exitBtn.setForeground(Color.pink);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                System.exit(0);
            }
        });

        label = new JLabel("MENU");
        label.setLocation(gameFrame.getSize().width / 2 - 17, 10);
        label.setSize(300, 70);
        label.setForeground(Color.GREEN);


        gameFrame.getContentPane().add(playBtn);
        gameFrame.getContentPane().add(scoreBtn);
        gameFrame.getContentPane().add(settingsBtn);
        gameFrame.getContentPane().add(exitBtn);
        gameFrame.getContentPane().add(label);
        gameFrame.getContentPane().repaint();
    }

    public void createSettingsPage(JFrame gameFrame) {
        gameFrame.getContentPane().removeAll();
        JButton backBtn = new JButton("Back");
        JLabel settingsTittleLbl = new JLabel("Settings");
        JLabel volumeLbl = new JLabel("Volume");
        JSlider volumeSl;
        JLabel musicONOFF = new JLabel("Music ON/OFF");
        JButton musicOnOffBtn = new JButton("V");
        JLabel difficultyLvlLbl = new JLabel("Level difficulty");
        JButton difficultyLvlBtn = new JButton(workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt"));
        gameFrame.setBackground(Color.blue);

        settingsTittleLbl.setLocation(gameFrame.getSize().width / 2 - 50, 0);
        settingsTittleLbl.setSize(400, 100);
        settingsTittleLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        settingsTittleLbl.setForeground(Color.RED);

        volumeLbl.setLocation(gameFrame.getSize().width / 2 - 200, 100);
        volumeLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        volumeLbl.setSize(400, 200);
        volumeLbl.setForeground(Color.PINK);

        musicONOFF.setLocation(gameFrame.getSize().width / 2 - 200, 150);
        musicONOFF.setFont(new Font("Serif", Font.PLAIN, 35));
        musicONOFF.setSize(400, 200);
        musicONOFF.setForeground(Color.PINK);

        difficultyLvlLbl.setLocation(gameFrame.getSize().width / 2 - 200, 200);
        difficultyLvlLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        difficultyLvlLbl.setSize(400, 200);
        difficultyLvlLbl.setForeground(Color.PINK);

        musicOnOffBtn.setLocation(gameFrame.getSize().width - 150, 220);
        musicOnOffBtn.setSize(100, 50);
        musicOnOffBtn.setForeground(Color.pink);
        musicOnOffBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (audio.wt == 0) {
                    audio.setWt(0.8);
                }
                else {
                    audio.setWt(0);
                }
                audio.setVolume();
            }
        });

        difficultyLvlBtn.setLocation(gameFrame.getSize().width - 150, 270);
        difficultyLvlBtn.setSize(100, 50);
        if (difficultyLvlBtn.getText().equals("Easy"))
            difficultyLvlBtn.setForeground(Color.green);
        if (difficultyLvlBtn.getText().equals("Medium"))
            difficultyLvlBtn.setForeground(Color.orange);
        if (difficultyLvlBtn.getText().equals("Hard"))
            difficultyLvlBtn.setForeground(Color.red);

        difficultyLvlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                System.out.println(difficultyLvlBtn.getText());
                if (difficultyLvlBtn.getText().equals("Easy")) {
                    difficultyLvlBtn.setText("Medium");
                    difficultyLvlBtn.setForeground(Color.orange);
                }
                else if (difficultyLvlBtn.getText().equals("Medium")) {
                    difficultyLvlBtn.setText("Hard");
                    difficultyLvlBtn.setForeground(Color.red);
                }
                 else if (difficultyLvlBtn.getText().equals("Hard")) {
                    difficultyLvlBtn.setText("Easy");
                    difficultyLvlBtn.setForeground(Color.green);
                }
                workWithFile.writeData(difficultyLvlBtn.getText(), "src/main/resources/data/TypeOfDifficult.txt");
            }
        });

        backBtn.setLocation(gameFrame.getSize().width - 120, gameFrame.getSize().height - 100);
        backBtn.setSize(100, 50);
        backBtn.setForeground(Color.pink);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtn.sound();
                audioBtn.setVolume();
                createMainMenu(gameFrame);
            }
        });

        volumeSl = new JSlider(0, 10, 8);
        volumeSl.setSize(300, 20);
        volumeSl.setLocation(150, gameFrame.getSize().width / 2 - 50);
        volumeSl.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider)e.getSource()).getValue();
                switch (value){
                    case 0:
                        audio.setWt(0);
                        break;
                    case 1:
                        audio.setWt(0.1);
                        break;
                    case 2:
                        audio.setWt(0.2);
                        break;
                    case 3:
                        audio.setWt(0.3);
                        break;
                    case 4:
                        audio.setWt(0.4);
                        break;
                    case 5:
                        audio.setWt(0.5);
                            break;
                    case 6:
                        audio.setWt(0.6);
                        break;
                    case 7:
                        audio.setWt(0.7);
                        break;
                    case 8:
                        audio.setWt(0.8);
                        break;
                    case 9:
                        audio.setWt(0.9);
                        break;
                    case 10:
                        audio.setWt(1);
                        break;
                    default:
                        break;
                }
                audio.setVolume();
            }
        });

        gameFrame.getContentPane().add(backBtn);
        gameFrame.getContentPane().add(settingsTittleLbl);
        gameFrame.getContentPane().add(volumeLbl);
        gameFrame.getContentPane().add(volumeSl);
        gameFrame.getContentPane().add(musicONOFF);
        gameFrame.getContentPane().add(musicOnOffBtn);
        gameFrame.getContentPane().add(difficultyLvlLbl);
        gameFrame.getContentPane().add(difficultyLvlBtn);

        gameFrame.getContentPane().repaint();
    }

}
