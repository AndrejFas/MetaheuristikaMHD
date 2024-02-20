import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random random = new Random();
        int velkostPopulacie = 100;
        double mutationProbability = 50;
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Turnus> turnuses = new ArrayList<>();
        FileLoader.loadHrany(segments);
//        for (Segment s:segments
//             ) {
//            System.out.printf("%d %d %d %d %n", s.getId(),s.getCost(), s.getNode1(), s.getNode2() );
//        }
        FileLoader.loadTurnusy(turnuses, segments);
//        for (Turnus t:turnuses
//             ) {
//            System.out.printf("%d %s %d: ", t.getId(), t.getName(), t.getCount());
//            for (int i:t.getSegments()
//                 ) {
//                System.out.printf("%d ", i);
//            }
//            System.out.printf("%n");
//        }

        Population population = new Population(velkostPopulacie);
        Population newPopulation = new Population(velkostPopulacie);

        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(turnuses);

        heuristic.createFirstValidSolution(segments, validator, population);
        heuristic.createRestOfThePopulation(segments, validator, population, velkostPopulacie);

        int t = 0;
        population.sortPopulation();
        Solution bestSolution = population.getPopulation().get(0);
        int minHodnota = bestSolution.getCost();

        while(t < 2000){
            for (int i = 0; i < velkostPopulacie / 2; i++) {
                //Parovanie
                Solution[] pair = GeneticAlgorithm.getPair(population);
                //Krizenie
                Solution[] newSolution = GeneticAlgorithm.crossing(pair, heuristic, validator);
                //Mutacia
                Solution mutatedSolution1 = newSolution[0];
                Solution mutatedSolution2 = newSolution[1];
                if (random.nextDouble() % 1 < mutationProbability/100){
                    mutatedSolution1 = GeneticAlgorithm.mutate(newSolution[0], validator);
                }
                if (random.nextDouble() % 1 < mutationProbability/100){
                    mutatedSolution2 = GeneticAlgorithm.mutate(newSolution[1], validator);
                }

                newPopulation.addToPopulation(mutatedSolution1);
                newPopulation.addToPopulation(mutatedSolution2);
            }

            newPopulation.sortPopulation();
            //System.out.println(newPopulation.getPopulation().get(0).getCost() + " < " + minHodnota);
            if(newPopulation.getPopulation().get(0).getCost() < minHodnota) {
                bestSolution = new Solution(newPopulation.getPopulation().get(0));
                minHodnota = bestSolution.getCost();
                t= 0;
            }

            population = new Population(newPopulation);
            newPopulation = new Population(velkostPopulacie);
            t++;

        }

        System.out.println("Celkova cena: " + bestSolution.getCost());
        for (int i = 0; i < bestSolution.length(); i++) {
            if (population.getPopulation().get(0).get(i) == 1){
                System.out.print(" " + (i));
            }

        }
        System.out.println();

        //validator.seeValidate(population.getPopulation().get(0), segments);


    }
}
