import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {
    private static FileInputStream stream;
    private static Player player;

    public Music() {}

    public void click() {
        try {
            stream = new FileInputStream("src/main/resources/music/btnClickSound.mp3");
            Player player = new Player(stream);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Can't find sound file!");
        }
    }

    public void gameOver() {
        try {
            stream = new FileInputStream("src/main/resources/music/gameOverSound.mp3");
            Player player = new Player(stream);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Can't find sound file!");
        }
    }

    public void pickUpSmth() {
        try {
            stream = new FileInputStream("src/main/resources/music/pickUpSmthSound.mp3");
            Player player = new Player(stream);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Can't find sound file!");
        }
    }

}
