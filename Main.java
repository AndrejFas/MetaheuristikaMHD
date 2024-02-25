import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static void main(String[] args){
        int populationSize = 150;
        double mutationProbability = 80;
        int numberOfIterationsBeforeEnd = 4500;
        int transferedCount = populationSize / 10;
        String problem = "A";

        Random random = new Random();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Turnus> turnuses = new ArrayList<>();

        FileLoader.loadHrany(segments, problem);
        FileLoader.loadTurnusy(turnuses, segments, problem);

        Population population = new Population(populationSize);
        Population newPopulation = new Population(populationSize);

        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(turnuses);

        long start = System.currentTimeMillis();

        heuristic.createFirstValidSolution(segments, validator, population);
        heuristic.createRestOfThePopulation(segments, validator, population, populationSize);

        population.sortPopulation();
        Solution bestSolution = population.getPopulation().get(0);
        int minHodnota = bestSolution.getCost();
        boolean transfer;
        int t = 0;

        while(t < numberOfIterationsBeforeEnd){
            int i = 0;
            while ( i < populationSize) {
                // Parovanie
                Solution[] pair = GeneticAlgorithm.getPair(population);
                // Krizenie
                Solution[] newSolution = GeneticAlgorithm.crossing(pair, heuristic, validator);
                // Mutacia
                Solution mutatedSolution1 = newSolution[0];
                Solution mutatedSolution2 = newSolution[1];
                if (random.nextDouble() % 1 < mutationProbability/100){
                    mutatedSolution1 = GeneticAlgorithm.mutate(newSolution[0], validator, random);
                }
                if (random.nextDouble() % 1 < mutationProbability/100){
                    mutatedSolution2 = GeneticAlgorithm.mutate(newSolution[1], validator, random);
                }
                if (!(population.contains(mutatedSolution1) || population.contains(mutatedSolution2))){
                    validator.seeValidate(mutatedSolution1, mutatedSolution1.getSegments());
                    validator.seeValidate(mutatedSolution2, mutatedSolution2.getSegments());

                    newPopulation.addToPopulation(mutatedSolution1);
                    if (mutatedSolution1.getCost() < minHodnota){
                        System.out.println("Iteracia: " + t + ", cena najlepsieho riesenia: " + mutatedSolution1.getCost());
                        bestSolution = new Solution(mutatedSolution1);
                        minHodnota = bestSolution.getCost();
                        t= 0;
                    }
                    newPopulation.addToPopulation(mutatedSolution2);
                    if (mutatedSolution2.getCost() < minHodnota){
                        System.out.println("Iteracia: " + t + ", cena najlepsieho riesenia: " + mutatedSolution2.getCost());
                        bestSolution = new Solution(mutatedSolution2);
                        minHodnota = bestSolution.getCost();
                        t= 0;
                    }
                    i += 2;
                }

            }
            // Aktualizacia populacie
            transfer = ((t / 1500) % 2) == 0 ;
            if (transfer){
                for (int j = 0; j < transferedCount; j++) {
                    newPopulation.addToPopulation(new Solution(population.getPopulation().get(j)));
                }
            }

            population = new Population(newPopulation);
            population.sortPopulation();

            if (transfer){
                for (int j = 0; j < transferedCount; j++) {
                    population.removeFromPopulation(population.getPopulation().size()-1);
                }
            }
            newPopulation = new Population(populationSize);
            t++;

        }

        long end = System.currentTimeMillis() - start;

        ArrayList<Stop> stops = new ArrayList<>();
        FileLoader.loadStops(stops);

        System.out.println("\nCena najdeneho riesenia: " + bestSolution.getCost());
        System.out.println("Cas vypoctu: " + (double)end/1000);
        for (int i = 0; i < bestSolution.length(); i++) {

            if (bestSolution.get(i) == 1){
                int[] stopsId =  segments.get(i).getNodes();
                String firstStop = "";
                String secondStop = "";
                for (Stop stop: stops
                     ) {
                    if (stop.getId() == stopsId[0]){
                        firstStop = stop.getName();
                    }
                    if (stop.getId() == stopsId[1]){
                        secondStop = stop.getName();
                    }
                    if (!firstStop.equals("") && !secondStop.equals("")){
                        break;
                    }
                }
                System.out.println(segments.get(i).getIndex() + ": " + firstStop + " --> " + secondStop + " (" + segments.get(i).getCost() + ")");
            }

        }

        //validator.seeValidate(population.getPopulation().get(0), segments);

    }
}
