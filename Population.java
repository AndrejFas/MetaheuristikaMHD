import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

    private final ArrayList<Solution> population;
    private final int velkostPopulacie;
    private double costOfPopulation = 0;
    private final Random random;
    public Population(int _velkostPopulacie){
        this.random = new Random();
        this.population = new ArrayList<>();
        velkostPopulacie = _velkostPopulacie;
    }
    public Population(Population _population){
        this.random = new Random();
        this.population = _population.getPopulation();
        this.velkostPopulacie = _population.velkostPopulacie;
        this.costOfPopulation = _population.costOfPopulation;
    }

    public ArrayList<Solution> getPopulation() {
        return population;
    }


    public void addToPopulation(Solution _solution) {
        population.add(_solution);
        costOfPopulation += 1 / (double)_solution.getCost();
    }

    public void removeFromPopulation(int _index){
        costOfPopulation -=  1 / (double)population.get(_index).getCost();
        population.remove(_index);
    }

    public void sortPopulation(){
        Collections.sort(population);
    }

    public Solution[] getPair(){
        Solution[] pair = new Solution[2];
        double random1 = random.nextDouble() % 1;
        double random2 = random.nextDouble() % 1;

        int index1 = 0;
        int index2 = 0;

        double cost = 0;
        for (Solution s: population) {
            cost += (1 / (double)s.getCost()) / costOfPopulation;
            if (cost < random1){index1++;}
            if (cost < random2){index2++;}
            if (cost > random1 && cost > random2){break;}
        }

        pair[0] = population.get(index1);
        pair[1] = population.get(index2);
        return pair;
    }
}
