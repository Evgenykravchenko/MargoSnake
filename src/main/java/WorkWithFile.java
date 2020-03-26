import java.io.*;

public class WorkWithFile {

    public WorkWithFile() {}

    public String getMaxScore () {
        String score = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/score.txt"));
            score = reader.readLine();
            reader.close();
        } catch (IOException e) {

        }
        return score;
    }

    public void writeMaxScore (String score) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data/score.txt"));
            writer.write(score);
            writer.close();
        } catch (IOException e) {

        }
    }
}
