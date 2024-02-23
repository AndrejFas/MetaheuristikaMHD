import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static void loadHrany(ArrayList<Segment> list){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("files/A_useky.csv"));
            String line;
            reader.readLine();
            line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(";");
                list.add(new Segment(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadTurnusy(ArrayList<Turnus> turnuses, ArrayList<Segment> _segments) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("files/A_turnusy.csv"));
            String line;
            reader.readLine();
            line = reader.readLine();


            while (line != null) {
                String[] tokens = line.split(";");
                ArrayList<Integer> segments = new ArrayList<>();
                for (int i = 4; i < tokens.length; i++) {
                    segments.add(Integer.parseInt(tokens[i]));
                    _segments.get(Integer.parseInt(tokens[i])).increase();
                }
                turnuses.add(new Turnus( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]), segments));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
