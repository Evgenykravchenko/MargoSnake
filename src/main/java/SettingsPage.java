import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage extends JFrame implements ActionListener {
    private static JFrame settingsFrame = new JFrame();
    private static JButton backBtn = new JButton("Back");
    private static WorkWithFile workWithFile = new WorkWithFile();
    private static JLabel settingsTittleLbl = new JLabel("Settings");
    private static JLabel volumeLbl = new JLabel("Volume");
    private static JSlider volumeSl;
    private static JLabel musicONOFF = new JLabel("Music ON/OFF");
    private static JButton musicOnOffBtn = new JButton("V");
    private static Music music = new Music();
    Audio audio;

    public SettingsPage(Audio audio) {
        this.audio = audio;
        settingsFrame.setLayout(null);
        settingsFrame.setTitle("Snake");
        settingsFrame.setBounds(0, 0, 480, 480);
        settingsFrame.setBackground(Color.darkGray);
        settingsFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        settingsTittleLbl.setLocation(settingsFrame.getSize().width / 2 - 50, 0);
        settingsTittleLbl.setSize(400, 100);
        settingsTittleLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        settingsTittleLbl.setForeground(Color.RED);

        volumeLbl.setLocation(settingsFrame.getSize().width / 2 - 200, 100);
        volumeLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        volumeLbl.setSize(400, 200);
        volumeLbl.setForeground(Color.PINK);

        musicONOFF.setLocation(settingsFrame.getSize().width / 2 - 200, 150);
        musicONOFF.setFont(new Font("Serif", Font.PLAIN, 35));
        musicONOFF.setSize(400, 200);
        musicONOFF.setForeground(Color.PINK);

        musicOnOffBtn.setLocation(settingsFrame.getSize().width - 150, 220);
        musicOnOffBtn.setSize(100, 50);
        musicOnOffBtn.setForeground(Color.pink);
        musicOnOffBtn.addActionListener(this::actionMusicOnOff);

        backBtn.setLocation(settingsFrame.getSize().width - 120, settingsFrame.getSize().height - 100);
        backBtn.setSize(100, 50);
        backBtn.setForeground(Color.pink);
        backBtn.addActionListener(this::actionPerformed);

        volumeSl = new JSlider(0, 10, 8);
        volumeSl.setSize(300, 20);
        volumeSl.setLocation(150, settingsFrame.getSize().width / 2 - 50);
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

        settingsFrame.getContentPane().add(backBtn);
        settingsFrame.getContentPane().add(settingsTittleLbl);
        settingsFrame.getContentPane().add(volumeLbl);
        settingsFrame.getContentPane().add(volumeSl);
        settingsFrame.getContentPane().add(musicONOFF);
        settingsFrame.getContentPane().add(musicOnOffBtn);
        settingsFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        music.click();
        settingsFrame.dispose();
        new MyFrame();
    }

    public void actionMusicOnOff (ActionEvent e) {
        if (workWithFile.getData("src/main/resources/data/isSoundPlay.txt").equals("true")) {
            workWithFile.writeData("false1", "src/main/resources/data/isSoundPlay.txt");
            audio.stop();
        }
        else if (workWithFile.getData("src/main/resources/data/isSoundPlay.txt").equals("false") ||
                workWithFile.getData("src/main/resources/data/isSoundPlay.txt").equals("false1")){
            workWithFile.writeData("true", "src/main/resources/data/isSoundPlay.txt");
            audio.play();
            audio.setVolume();
            audio.repeat();
        }
    }
}
