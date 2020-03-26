import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePage extends JFrame implements ActionListener {
    private static JFrame scoreFrame = new JFrame();
    private static JButton backBtn = new JButton("Back");
    private static WorkWithFile workWithFile = new WorkWithFile();
    private static JLabel scoreTittleLbl = new JLabel("Score");
    private static JLabel maxScoreLbl = new JLabel("Champion score is " + workWithFile.getData("src/main/resources/data/score.txt"));
    private static Music music = new Music();

    public ScorePage() {
        scoreFrame.setLayout(null);
        scoreFrame.setTitle("Snake");
        scoreFrame.setBounds(0, 0, 480, 480);
        scoreFrame.setBackground(Color.darkGray);
        scoreFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        scoreTittleLbl.setLocation(scoreFrame.getSize().width / 2 - 50, 0);
        scoreTittleLbl.setSize(400, 100);
        scoreTittleLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        scoreTittleLbl.setForeground(Color.RED);

        maxScoreLbl.setLocation(scoreFrame.getSize().width / 2 - 200, 100);
        maxScoreLbl.setFont(new Font("Serif", Font.PLAIN, 45));
        maxScoreLbl.setSize(400, 200);
        maxScoreLbl.setForeground(Color.PINK);

        backBtn.setLocation(scoreFrame.getSize().width - 120, scoreFrame.getSize().height - 100);
        backBtn.setSize(100, 50);
        backBtn.setForeground(Color.pink);
        backBtn.addActionListener(this);

        scoreFrame.getContentPane().add(backBtn);
        scoreFrame.getContentPane().add(scoreTittleLbl);
        scoreFrame.getContentPane().add(maxScoreLbl);
        scoreFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        music.click();
        scoreFrame.dispose();
        new MyFrame();
    }
}
