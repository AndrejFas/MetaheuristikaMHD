import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Turnus> turnuses = new ArrayList<>();
        FileLoader.loadHrany(segments);
//        for (Segment s:segments
//             ) {
//            System.out.printf("%d %d %d %d %n", s.getId(),s.getCost(), s.getNode1(), s.getNode2() );
//        }
        FileLoader.loadTurnusy(turnuses);
//        for (Turnus t:turnuses
//             ) {
//            System.out.printf("%d %s %d: ", t.getId(), t.getName(), t.getCount());
//            for (int i:t.getSegments()
//                 ) {
//                System.out.printf("%d ", i);
//            }
//            System.out.printf("%n");
//        }
    }
}
