import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static void loadHrany(ArrayList<Segment> list){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("HranyAll.txt"));
            String line;
            reader.readLine();
            line = reader.readLine();
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

    }

    public static void loadTurnusy(ArrayList<Turnus> turnuses) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("TurnusyAll.csv"));
            String line;
            reader.readLine();
            line = reader.readLine();


            while (line != null) {
                String[] tokens = line.split(";");
                ArrayList<Integer> segments = new ArrayList<>();
                for (int i = 3; i < tokens.length; i++) {
                    segments.add(Integer.parseInt(tokens[i]));
                }
                turnuses.add(new Turnus( Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), segments));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
