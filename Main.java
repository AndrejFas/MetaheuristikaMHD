import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Segment> segments = new ArrayList<Segment>();
        FileLoader.loadHrany(segments);
        for (Segment s:segments
             ) {
            System.out.printf("%d %d %d %d %n", s.getId(),s.getCost(), s.getNode1(), s.getNode2() );
        }
    }
}
