import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    private String track = "src/main/resources/music/mainMenuSound.wav";
    private File file = new File(track);
    private AudioInputStream audioInputStream = null;
    private Clip clip = null;
    private FloatControl volumeC = null;

    public void setWt(double wt) {
        this.wt = wt;
    }

    public double wt;
    private boolean pl_audio;

    public Audio(double wt) {
        this.wt = wt;
        this.pl_audio = false;
    }

    public void sound() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e) {

        }
    }

    public void setVolume() {
        if (wt < 0) wt = 0;
        if (wt > 1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min) * (float)wt + min);
    }

    public void play() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
        }

        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (!this.pl_audio) {
                clip.setFramePosition(0);
                clip.start();
                this.pl_audio = true;
            }

        } catch (LineUnavailableException | IOException e) {

        }
    }

    public void stop() {
        clip.stop();
        clip.close();
        this.pl_audio = false;
    }

    public void repeat() {
        if (this.pl_audio) {
            clip.loop(Integer.MAX_VALUE);
        }
    }
}
