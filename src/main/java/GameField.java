import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel implements ActionListener {
    public static int score = 0;
    public boolean inGame = true;      //нахождение в игре
    private final int SIZE = 320;       //размер поля
    private final int SNAKE_SIZE = 16;    //размер пикселя
    private int ALL_SEGMENTS = 0;   //всего единиц на поле
    private Image snake;
    private Image headLeft;
    private Image headUp;
    private Image headRight;
    private Image headDown;
    private Image body;
    private Image food;
    private Image grass;
    private Image wall;
    private ArrayList<Integer> wallX;
    private ArrayList<Integer> wallY;
    private int foodX;  //позиции еды по оси Ох
    private int foodY;  //позиции еды по оси Оу
    private int bonusX;
    private int bonusY;
    private Image portalIn;
    private Image portalOut;
    private int snakeSize;
    private int[] x;    //позиции для змейки по оси Ох
    private int[] y;    //позиции для змейки по оси Оу
    private Timer timer;
    private Date date1;
    private Date date2;
    private boolean isDateSet = true;
    private boolean left = false;
    private boolean right = false;   //первоначальная позиция змейки
    private boolean down = true;
    private boolean up = false;
    private static final Random random = new Random();
    private JTextField textField;
    JFrame gameFrame;
    Music music = new Music();
    WorkWithFile workWithFile = new WorkWithFile();
    public JFrame mainMenu;
    private Audio audioBtnClick = new Audio(0.8, "src/main/resources/music/btnClickSound.wav");
    private Audio audioPickUpSmth = new Audio(0.8, "src/main/resources/music/pickUpSmthSound.wav");
    private static int delay;

    GameField(JFrame jFrame, JFrame mainMenu) {
        wallX = new ArrayList<>();
        wallY = new ArrayList<>();

        delay = 250;
        this.mainMenu = mainMenu;
        workWithFile.writeData("false", "src/main/resources/data/isPause.txt");
        this.ALL_SEGMENTS = jFrame.getSize().width;
        this.x = new int[this.ALL_SEGMENTS];
        this.y = new int[this.ALL_SEGMENTS];
        this.gameFrame = jFrame;

        if (workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt").equals("Hard")) {
            for (int i = 16; i < gameFrame.getHeight(); i = i + 32) {
                for (int j = 16; j < gameFrame.getWidth(); j = j + 32) {
                    wallX.add(j);
                    wallY.add(i);
                }
            }
        }
        setBackground(Color.red);
        downloadImage();
        Game();
        addKeyListener(new SnakeKeyListener());
        setFocusable(true); //соединение с полем
    }

    private void downloadImage() {
        ImageIcon snakeIcon = new ImageIcon("src/main/resources/picture/snake.png");
        portalIn = snakeIcon.getImage();
        portalOut = snakeIcon.getImage();

        ImageIcon headIcon = new ImageIcon("src/main/resources/picture/headLeft.png");
        headLeft = headIcon.getImage();
        headIcon = new ImageIcon("src/main/resources/picture/headUp.png");
        headUp = headIcon.getImage();
        headIcon = new ImageIcon("src/main/resources/picture/headRight.png");
        headRight = headIcon.getImage();
        headIcon = new ImageIcon("src/main/resources/picture/headDown.png");
        headDown = headIcon.getImage();
        ImageIcon bodyIcon = new ImageIcon("src/main/resources/picture/body.png");
        body = bodyIcon.getImage();

        ImageIcon foodIcon = new ImageIcon("src/main/resources/picture/bee.png");
        food = foodIcon.getImage();
        ImageIcon grassIcon = new ImageIcon("src/main/resources/picture/grass.png");
        grass = grassIcon.getImage();

        ImageIcon wallImg = new ImageIcon("src/main/resources/picture/wall/wall.png");
        wall = wallImg.getImage();


    }

    private void createFood() {
        foodX = random.nextInt(gameFrame.getWidth() / SNAKE_SIZE - 16) * SNAKE_SIZE;
        foodY = random.nextInt(gameFrame.getHeight() / SNAKE_SIZE - 16) * SNAKE_SIZE;
        for (int i = 0; i < wallX.size(); i++) {
            while (foodX == wallX.get(i) && foodY == wallY.get(i)) {
                foodX = random.nextInt(gameFrame.getWidth() / SNAKE_SIZE) * SNAKE_SIZE;
                foodY = random.nextInt(gameFrame.getHeight() / SNAKE_SIZE) * SNAKE_SIZE;
            }
        }

    }

    private void Game() {
        timer = new Timer(delay, this);
        timer.start();
        snakeSize = 3;   //начальная длина змейки
        for (int i = 0; i < snakeSize; i++) {
            x[i] = 0;
            y[i] = i * SNAKE_SIZE;
        }
        createFood();
    }

    private void checkFood() {
        if (workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt").equals("Medium")) {
            if (x[0] == 512 && y[0] == 512) {
                x[0] = 128;
                y[0] = 128;
            }
        }
        if (x[0] == foodX && y[0] == foodY) {
            audioPickUpSmth.sound();
            audioPickUpSmth.setVolume();
            snakeSize++;
            score++;
            timer.stop();
            if (workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt").equals("Medium")) {
                delay -= 10;
            }
            timer = new Timer(delay, this);
            timer.start();
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
        for (int i = snakeSize; i > 0; i--)
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) inGame = false;
    }

    private void checkField() {
        for (int i = 0; i < wallX.size(); i++) {
            if (wallX.get(i) - x[0] == 16 && y[0] == wallY.get(i) && right) inGame = false;
            if (x[0] - wallX.get(i) == 16 && y[0] == 256 && left) inGame = false;
            if (wallY.get(i) - y[0] == 16 && x[0] == wallX.get(i) && down) inGame = false;
            if (y[0] - wallY.get(i) == 16 && x[0] == wallX.get(i) && up) inGame = false;
        }
        if (x[0] <= 0 && left) inGame = false;
        if (x[0] >= gameFrame.getWidth() - 16 && right) inGame = false;
        if (y[0] <= 0 && up) inGame = false;
        if (y[0] >= gameFrame.getHeight() - 32 && down) inGame = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //перерисовка всего поля
        if (inGame) {
            for (int i = 0; i <= gameFrame.getHeight() / 32; i++) {
                for (int j = 0; j <= gameFrame.getWidth() / 32; j++) {
                    g.drawImage(grass, j * 32, i * 32, this);
                }
            }
            if (workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt").equals("Medium")) {
                g.drawImage(portalOut, 128, 128, this);
                g.drawImage(portalIn, 512, 512, this);
            }
            if (workWithFile.getData("src/main/resources/data/TypeOfDifficult.txt").equals("Hard")) {
                for (int i = 0; i < wallX.size(); i++) {
                    g.drawImage(wall, wallX.get(i), wallY.get(i), this);
                }
            }
            g.drawImage(food, foodX, foodY, this);
            for (int i = 0; i < snakeSize; i++) {
                //g.drawImage(snake, x[i], y[i], this);
                if (i == 0) {
                    if (up)
                        g.drawImage(headUp, x[i], y[i], this);
                    if (left)
                        g.drawImage(headLeft, x[i], y[i], this);
                    if (down)
                        g.drawImage(headDown, x[i], y[i], this);
                    if (right)
                        g.drawImage(headRight, x[i], y[i], this);
                }
                else {
                    g.drawImage(body, x[i], y[i], this);
                }
            }
            /*Добавляет временный элемент, можно сделать бонусы временные
            if (isDateSet) {
                isDateSet = false;
                date1 = new Date();
                date2 = new Date();
            }
            if (date2.getTime() - date1.getTime() < 5000) {
                date2 = new Date();
                g.drawImage(body, 90, 90, this);
            }
            */

            Font font = new Font("Mongolian Baiti", Font.BOLD, 10);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("SCORE: " + score, gameFrame.getWidth() - 70, 20);

        } else {
            timer = new Timer(1000, this);
            timer.start();

            music.gameOver();

            int maxScore = Integer.parseInt(workWithFile.getData("src/main/resources/data/score.txt"));
            if (maxScore < score) {
                workWithFile.writeData(String.valueOf(score), "src/main/resources/data/score.txt");
            }
            //gameFrame.setVisible(false);
            createGameOverPage(gameFrame);
            //new GameOverScreen(score, mainMenu, gameFrame.getX(), gameFrame.getY());
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
        if (inGame && (workWithFile.getData("src/main/resources/data/isPause.txt").equals("false"))) {
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
            /*if (key == KeyEvent.VK_Y) {
                if (workWithFile.getData("src/main/resources/data/isPause.txt").equals("true")) {
                    workWithFile.writeData("false", "src/main/resources/data/isPause.txt");
                }
            }
            */
            if (key == KeyEvent.VK_R) {
                if (workWithFile.getData("src/main/resources/data/isPause.txt").equals("false")) {
                    workWithFile.writeData("true1", "src/main/resources/data/isPause.txt");
                    new Pause(gameFrame);
                }
            }

        }
    }

    public void createGameOverPage(JFrame gameFrame) {
        gameFrame.getContentPane().removeAll();

        JButton restartBtn;
        JButton mainMenuBtn;
        JButton exitBtn;
        WorkWithFile workWithFile = new WorkWithFile();
        JLabel gameOverLbl = new JLabel("GAME OVER");
        JLabel yourScoreLbl = new JLabel("You're score is " + score);
        JLabel maxScoreLbl = new JLabel("Champion score is " + workWithFile.getData("src/main/resources/data/score.txt"));

        gameFrame.setLayout(null);
        gameFrame.setTitle("Snake");
        gameFrame.setBounds(gameFrame.getX(), gameFrame.getY(), 480, 480);
        gameFrame.setBackground(Color.darkGray);
        gameFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        restartBtn = new JButton("Restart");
        restartBtn.setLocation(40, 90);
        restartBtn.setSize(400, 50);
        restartBtn.setForeground(Color.pink);
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtnClick.sound();
                audioBtnClick.setVolume();
                gameFrame.dispose();
                new GameWindow(gameFrame, gameFrame.getX(), gameFrame.getY());
            }
        });

        mainMenuBtn = new JButton("Menu");
        mainMenuBtn.setLocation(40, 150);
        mainMenuBtn.setSize(400, 50);
        mainMenuBtn.setForeground(Color.pink);
        mainMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audioBtnClick.sound();
                audioBtnClick.setVolume();
                gameFrame.dispose();
                //mainMenu.setLocation(gameFrame.getX(), gameFrame.getY());
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
                audioBtnClick.sound();
                audioBtnClick.setVolume();
                workWithFile.writeData("false", "src/main/resources/data/isSoundPlay.txt");
                System.exit(0);
            }
        });

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

        gameFrame.getContentPane().repaint();
    }
}
