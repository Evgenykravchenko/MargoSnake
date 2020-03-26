import java.io.*;

public class WorkWithFile {

    public WorkWithFile() {}

    public String getData (String pathName) {
        String data = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathName));
            data = reader.readLine();
            reader.close();
        } catch (IOException e) {

        }
        return data;
    }

    public void writeData (String data, String pathName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
            writer.write(data);
            writer.close();
        } catch (IOException e) {

        }
    }
}
