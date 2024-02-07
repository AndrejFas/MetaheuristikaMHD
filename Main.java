import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        int velkostPopulacie = 20;
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

        Population population = new Population(segments, velkostPopulacie);

        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(turnuses);

        heuristic.createFirstValidSolution(segments, validator, population);
        heuristic.createRestOfThePopulation(segments, validator, population, velkostPopulacie);

        int t = 0;
        population.sortPopulation();
        int minHodnota = population.getPopulation().get(0).getCost();

        while(t < 1000){


            for (int i = 0; i < velkostPopulacie / 2; i++) {
                //Parovanie
                Solution[] pair = GeneticAlgorithm.getPair(population);
                //Krizenie
                Solution[] newSolution = GeneticAlgorithm.crossing(pair, heuristic, validator);
                //Mutacia
                Solution mutatedSolution1 = GeneticAlgorithm.mutate(newSolution[0], heuristic, validator);
                Solution mutatedSolution2 = GeneticAlgorithm.mutate(newSolution[1], heuristic, validator);
                //Selekcia
                if (mutatedSolution1.getCost() < mutatedSolution2.getCost()){
                    if (!population.contains(mutatedSolution1)){
                        population.addToPopulation(mutatedSolution1);
                        if (mutatedSolution1.getCost() < minHodnota){
                            t = 0;
                            minHodnota = mutatedSolution1.getCost();
                        }
                    }
                } else {
                    if (!population.contains(mutatedSolution2)){
                        population.addToPopulation(mutatedSolution2);
                        if (mutatedSolution2.getCost() < minHodnota){
                            t = 0;
                            minHodnota = mutatedSolution2.getCost();
                        }
                    }

                }
            }
            // Redukcia
            population.sortPopulation();
            while(population.getPopulation().size() > velkostPopulacie)
                population.getPopulation().remove(velkostPopulacie);
            t++;
        }




        for (int j = 0; j < velkostPopulacie; j++) {

            System.out.println("Celkova cena: " + population.getPopulation().get(j).getCost());
            //for (int i = 0; i < population.getPopulation().get(j).length(); i++) {
            //    System.out.print(" " + population.getPopulation().get(j).get(i));
            //}
            //System.out.println();
        }

    }
}
