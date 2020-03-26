import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void mainMenu() {
        Audio audio = new Audio(0.8);
        audio.play();
        audio.setVolume();
        audio.repeat();
    }

}
