import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static boolean loadHrany(ArrayList<Segment> list){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("HranyAll.txt"));
            String line0 = reader.readLine();
            String line = reader.readLine();
            int i = 1;

            while (line != null) {
                String[] tokens = line.split(",");
                list.add(new Segment(i, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                line = reader.readLine();
                i++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
