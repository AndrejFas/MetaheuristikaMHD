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

        Population population = new Population(segments);

        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(turnuses);

        heuristic.createFirstValidSolution(segments, validator, population);
        heuristic.createRestOfThePopulation(segments, validator, population);

        int t = 0;
        while(t < 100){
            t++;
        }

//        validator.validate(population.getPopulation()[0], segments);
//        System.out.println("Prijatelne riesenie:");
//        for (int x:population.getPopulation()[0]
//             ) {
//            System.out.print(" " + x);
//        }
        population.sortPopulation();

        for (int j = 0; j < 10; j++) {

            System.out.println("Celkova cena: " + population.getPopulation().get(j).getCost());
        }

    }
}
