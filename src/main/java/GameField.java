import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel implements ActionListener {
    private static int score = 0;
    public boolean inGame = true;      //нахождение в игре
    private final int SIZE = 320;       //размер поля
    private final int SNAKE_SIZE = 16;    //размер пикселя
    private int ALL_SEGMENTS = 0;   //всего единиц на поле
    private Image snake;
    private Image food;
    private int foodX;  //позиции еды по оси Ох
    private int foodY;  //позиции еды по оси Оу
    private int snakeSize;
    private int[] x;    //позиции для змейки по оси Ох
    private int[] y;    //позиции для змейки по оси Оу
    private Timer timer;
    private boolean left = false;
    private boolean right = false;   //первоначальная позиция змейки
    private boolean down = true;
    private boolean up = false;
    private static final Random random = new Random();
    private JTextField textField;
    JFrame gameFrame;
    Music music = new Music();

    private static int i_anim;
    private static ArrayList<Image> bubbles = new ArrayList<>();
    private static String currentAnimation;
    private Audio audio;

    GameField(JFrame jFrame, Audio audio) {
        this.ALL_SEGMENTS = jFrame.getSize().width;
        this.x = new int[this.ALL_SEGMENTS];
        this.y = new int[this.ALL_SEGMENTS];
        this.gameFrame = jFrame;
        this.audio = audio;
        setBackground(Color.pink);
        downloadImage();
        Game();
        addKeyListener(new SnakeKeyListener());
        setFocusable(true); //соединение с полем
    }

    private void downloadImage() {
        ImageIcon snakeIcon = new ImageIcon("src/main/resources/picture/snake.png");
        snake = snakeIcon.getImage();
        ImageIcon foodIcon = new ImageIcon("src/main/resources/picture/bee.png");
        food = foodIcon.getImage();

        for (int i = 0; i < 9; i++) {
            ImageIcon bubbleIcon = new ImageIcon("src/main/resources/picture/bubble" + (i + 1) + ".png");
            bubbles.add(bubbleIcon.getImage());
        }
    }

    private void createFood() {
        foodX = random.nextInt(20) * SNAKE_SIZE;
        foodY = random.nextInt(20) * SNAKE_SIZE;
    }

    private void Game() {
        timer = new Timer(250, this);
        timer.start();
        snakeSize = 3;   //начальная длина змейки
        for (int i = 0; i < snakeSize; i++) {
            x[i] = 48;
            y[i] = 48 - i * SNAKE_SIZE;
        }
        createFood();
    }

    private void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            music.pickUpSmth();
            snakeSize++;
            score++;
            createFood();
        }
    }

    private void move() {
        for (int i = snakeSize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        direction();
    }

    private void direction() {
        if (up) y[0] -= SNAKE_SIZE;
        if (right) x[0] += SNAKE_SIZE;
        if (down) y[0] += SNAKE_SIZE;
        if (left) x[0] -= SNAKE_SIZE;
    }

    private void checkSnakeSize() {
        for (int i = snakeSize; i > 0; i--) if (i > 4 && x[0] == x[i] && y[0] == y[i]) inGame = false;
    }

    private void checkField() {
        if (x[0] <= 0 && left) inGame = false;
        if (x[0] >= ALL_SEGMENTS - 16 && right) inGame = false;
        if (y[0] <= 0 && up) inGame = false;
        if (y[0] >= ALL_SEGMENTS - 32 && down) inGame = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //перерисовка всего поля
        if (inGame) {
            g.drawImage(food, foodX, foodY, this);
            for (int i = 0; i < snakeSize; i++) {
                g.drawImage(snake, x[i], y[i], this);
            }

            Font font = new Font("Mongolian Baiti", Font.BOLD, 10);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("SCORE: " + score, ALL_SEGMENTS - 70, 20);
        } else {
            music.gameOver();
            WorkWithFile workWithFile = new WorkWithFile();
            int maxScore = Integer.parseInt(workWithFile.getData("src/main/resources/data/score.txt"));
            if (maxScore < score) {
                workWithFile.writeData(String.valueOf(score), "src/main/resources/data/score.txt");
            }
            gameFrame.setVisible(false);
            new GameOverScreen(score, audio);
            score = 0;
        }
    }

    /*private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 130, SIZE / 2);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFood();
            checkSnakeSize();
            checkField();
            move();
        }
        repaint();
    }


    class SnakeKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                right = false;
                left = false;
            }

            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                right = false;
                left = false;
            }

            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
        }
    }
}
