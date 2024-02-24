import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {
    public static void loadHrany(ArrayList<Segment> list, String _problem){
        BufferedReader reader;

        try {
            String filename = "files/" + _problem + "_useky.csv";
            reader = new BufferedReader(new FileReader(filename));
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

    public static void loadTurnusy(ArrayList<Turnus> _turnuses, ArrayList<Segment> _segments, String _problem) {
        BufferedReader reader;

        try {
            String filename = "files/" + _problem + "_turnusy.csv";
            reader = new BufferedReader(new FileReader(filename));
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
                _turnuses.add(new Turnus( Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]), segments));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadStops(ArrayList<Stop> _stops){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("files/ZastavkyAll.csv"));
            String line;
            reader.readLine();
            line = reader.readLine();

            while (line != null) {
                String[] tokens = line.split(";");
                _stops.add(new Stop(Integer.parseInt(tokens[0]), tokens[1]));
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
