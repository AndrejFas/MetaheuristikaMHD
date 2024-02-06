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
        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(segments.size());

        heuristic.createFirstValidSolution(segments, validator);

        validator.validate(heuristic.getSolution(),segments);
        System.out.println("Prijatelne riesenie:");
        for (int x:heuristic.getSolution()
             ) {
            System.out.print(" " + x);
        }
        int cost = 0;
        for (int i = 0; i < segments.size(); i++) {
            if (heuristic.getSolution()[i] == 1)
                cost += segments.get(i).getCost();
        }
        System.out.println("\nCelkova cena: " + cost);
    }
}
