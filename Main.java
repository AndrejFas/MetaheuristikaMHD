import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        int velkostPopulacie = 100;
        double mutationProbability = 50;
        int pocetVykonaniPredUkoncenim = 2000;
        int pocetPrenesenych = 10;

        Random random = new Random();
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

        while(t < pocetVykonaniPredUkoncenim){
            int i = 0;
            while ( i < velkostPopulacie) {
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
                if (!(population.contains(mutatedSolution1) || population.contains(mutatedSolution2))){
                    validator.seeValidate(mutatedSolution1, mutatedSolution1.getSegments());
                    validator.seeValidate(mutatedSolution2, mutatedSolution2.getSegments());
                    newPopulation.addToPopulation(mutatedSolution1);
                    newPopulation.addToPopulation(mutatedSolution2);
                    i += 2;
                }

            }

            newPopulation.sortPopulation();
            if (newPopulation.getPopulation().get(0).getCost() <  minHodnota){
                System.out.println(t + ") " + newPopulation.getPopulation().get(0).getCost() + " < " + minHodnota);
            }

            if(newPopulation.getPopulation().get(0).getCost() < minHodnota) {
                bestSolution = new Solution(newPopulation.getPopulation().get(0));
                minHodnota = bestSolution.getCost();
                t= 0;
            }

            //for (int j = 0; j < pocetPrenesenych; j++) {
            //    newPopulation.addToPopulation(new Solution(population.getPopulation().get(j)));
            //}
            population = new Population(newPopulation);
            population.sortPopulation();
            //for (int j = 0; j < pocetPrenesenych; j++) {
            //    population.removeFromPopulation(population.getPopulation().size()-1);
            //}
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
