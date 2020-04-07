import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pause  implements ActionListener {
    private static JFrame pauseFrame;
    private static JButton continueBtn;
    private JFrame gameFrame;
    private static JLabel pauseTittleLbl = new JLabel("Pause");
    private static WorkWithFile workWithFile = new WorkWithFile();
    private int count = 3;
    private Timer timer;
    public Pause(JFrame gameFrame) {

        this.gameFrame = gameFrame;
        gameFrame.dispose();

        pauseFrame = new JFrame();
        pauseFrame.setLayout(null);
        pauseFrame.setTitle("Snake");
        pauseFrame.setBounds(gameFrame.getX(), gameFrame.getY(), 480, 480);
        pauseFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        pauseTittleLbl.setLocation(pauseFrame.getSize().width / 2 - 50, 0);
        pauseTittleLbl.setSize(400, 100);
        pauseTittleLbl.setFont(new Font("Serif", Font.PLAIN, 35));
        pauseTittleLbl.setForeground(Color.RED);

        continueBtn = new JButton("Continue");
        continueBtn.setLocation(40, 90);
        continueBtn.setSize(400, 50);
        continueBtn.setForeground(Color.pink);
        continueBtn.addActionListener(this);

        pauseFrame.getContentPane().add(pauseTittleLbl);
        pauseFrame.getContentPane().add(continueBtn);
        pauseFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameFrame.setLocation(pauseFrame.getX(), pauseFrame.getY());
        gameFrame.setVisible(true);
        pauseFrame.dispose();
        workWithFile.writeData("false", "src/main/resources/data/isPause.txt");
    }

}
