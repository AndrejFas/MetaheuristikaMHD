import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        //==========Iteracie==================================================
        int[] iteratios = new int[] {1500,3000,4500,6000,7500,9000};
        double[] iterAvg = new double[iteratios.length];
        for (int j = 0; j < iteratios.length; j++) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += calculate(150, 0.8, iteratios[j], 0.1);
            }
            iterAvg[j] = (double) sum / 5;
        }
        int newIteration = iteratios[0];
        double bestAvg = iterAvg[0];
        for (int i = 1; i < iterAvg.length; i++) {
            if (iterAvg[i] < bestAvg){
                newIteration = iteratios[i];
                bestAvg = iterAvg[i];
            }
        }
        System.out.println("Best iteration count: " + newIteration);
        //==========Populacia==================================================
        int[] populatios = new int[] {50,100,150,200,250,300,400};
        double[] popAvg = new double[populatios.length];
        for (int j = 0; j < populatios.length; j++) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += calculate(populatios[j], 0.8, newIteration, 0.1);
            }
            popAvg[j] = (double) sum / 5;
        }
        int newPopulation = populatios[0];
        bestAvg = popAvg[0];
        for (int i = 1; i < popAvg.length; i++) {
            if (popAvg[i] < bestAvg){
                newPopulation = populatios[i];
                bestAvg = popAvg[i];
            }
        }
        System.out.println("Best population count: " + newPopulation);
        //==========Mutacia==================================================
        double[] mutations = new double[] {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1};
        double[] mutaAvg = new double[mutations.length];
        for (int j = 0; j < mutations.length; j++) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += calculate(newPopulation, mutations[j], newIteration, 0.1);
            }
            mutaAvg[j] = (double) sum / 5;
        }
        double newMutations = mutations[0];
        bestAvg = mutaAvg[0];
        for (int i = 1; i < mutaAvg.length; i++) {
            if (mutaAvg[i] < bestAvg){
                newMutations = mutations[i];
                bestAvg = mutaAvg[i];
            }
        }
        System.out.println("Best mutation probability: " + newMutations);
        //==========Prenos==================================================
        double[] transfers = new double[] {0,0.05,0.10,0.15,0.20,0.25,0.50};
        double[] tranAvg = new double[transfers.length];
        for (int j = 0; j < transfers.length; j++) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += calculate(newPopulation, newMutations, newIteration, transfers[j]);
            }
            tranAvg[j] = (double) sum / 5;
        }
        double newTransfer = transfers[0];
        bestAvg = tranAvg[0];
        for (int i = 1; i < tranAvg.length; i++) {
            if (tranAvg[i] < bestAvg){
                newTransfer = transfers[i];
                bestAvg = tranAvg[i];
            }
        }
        System.out.println(newMutations + " " + newIteration + " " + newTransfer + " " + newPopulation);

    }
    public static int calculate(int _populationSize, double _mutationProbability, int _numberOfIterationsBeforeEnd, double _transferedCount){
        int result;
        long transferedCount = Math.round(_populationSize * _transferedCount);
        String problem = "C1";

        Random random = new Random();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Turnus> turnuses = new ArrayList<>();

        FileLoader.loadHrany(segments, problem);
        FileLoader.loadTurnusy(turnuses, segments, problem);

        Population population = new Population(_populationSize);
        Population newPopulation = new Population(_populationSize);

        Validator validator = new Validator(turnuses);
        Heuristic heuristic = new Heuristic(turnuses);

        long start = System.currentTimeMillis();

        heuristic.createFirstValidSolution(segments, validator, population);
        heuristic.createRestOfThePopulation(segments, validator, population, _populationSize);

        population.sortPopulation();
        Solution bestSolution = population.getPopulation().get(0);
        int minHodnota = bestSolution.getCost();
        boolean transfer;
        int t = 0;

        while(t < _numberOfIterationsBeforeEnd){
            int i = 0;
            while ( i < _populationSize) {
                // Parovanie
                Solution[] pair = GeneticAlgorithm.getPair(population);
                // Krizenie
                Solution[] newSolution = GeneticAlgorithm.crossing(pair, heuristic, validator);
                if (newSolution[0].getCost() < minHodnota){
                    //System.out.printf("Iteracia: %4d, cena: %d%n", t, newSolution[0].getCost());
                    bestSolution = new Solution(newSolution[0]);
                    minHodnota = bestSolution.getCost();
                    t= 0;
                    newPopulation.addToPopulation(newSolution[0]);
                    i++;
                }
                else {
                    // Mutacia
                    Solution mutatedSolution1 = newSolution[0];
                    if (random.nextDouble() % 1 < _mutationProbability){
                        mutatedSolution1 = GeneticAlgorithm.mutate(newSolution[0], validator);
                    }
                    //if (!population.contains(mutatedSolution1)){
                        newPopulation.addToPopulation(mutatedSolution1);
                        if (mutatedSolution1.getCost() < minHodnota){
                            //System.out.printf("Iteracia: %4d, cena: %d%n", t, mutatedSolution1.getCost());
                            bestSolution = new Solution(mutatedSolution1);
                            minHodnota = bestSolution.getCost();
                            t= 0;
                        }
                        i++;
                    //}

                }

                if (newSolution[1].getCost() < minHodnota){
                    //System.out.printf("Iteracia: %4d, cena: %d%n", t, newSolution[1].getCost());
                    bestSolution = new Solution(newSolution[1]);
                    minHodnota = bestSolution.getCost();
                    t= 0;
                    newPopulation.addToPopulation(newSolution[1]);
                    i++;
                }
                else {
                    // Mutacia
                    Solution mutatedSolution2 = newSolution[1];

                    if (random.nextDouble() % 1 < _mutationProbability){
                        mutatedSolution2 = GeneticAlgorithm.mutate(newSolution[1], validator);
                    }
                    //if (!population.contains(mutatedSolution2)){
                        newPopulation.addToPopulation(mutatedSolution2);
                        if (mutatedSolution2.getCost() < minHodnota){
                            //System.out.printf("Iteracia: %4d, cena: %d%n", t, mutatedSolution2.getCost());
                            bestSolution = new Solution(mutatedSolution2);
                            minHodnota = bestSolution.getCost();
                            t= 0;
                        }
                        i++;
                    //}
                }




            }
            // Aktualizacia populacie
            transfer = ((t / (_numberOfIterationsBeforeEnd /3)) % 2) == 0 ;
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
            newPopulation = new Population(_populationSize);
            t++;

        }

        long end = System.currentTimeMillis() - start;

        ArrayList<Stop> stops = new ArrayList<>();
        FileLoader.loadStops(stops);
        result = bestSolution.getCost();
        System.out.println("\nCena najdeneho riesenia: " + result);
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
                //System.out.println(segments.get(i).getIndex() + ": " + firstStop + " --> " + secondStop + " (" + segments.get(i).getCost() + ")");
            }

        }

        //validator.seeValidate(population.getPopulation().get(0), segments);
        return result;
    }



}
