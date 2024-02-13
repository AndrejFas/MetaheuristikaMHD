import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

    private final ArrayList<Solution> population;
    private final int velkostPopulacie;

    private final Random random;
    public Population(int _velkostPopulacie){
        this.random = new Random();
        this.population = new ArrayList<>();
        velkostPopulacie = _velkostPopulacie;
    }

    public ArrayList<Solution> getPopulation() {
        return population;
    }

    public void addToPopulationOnIndex(Solution _solution, int _index) {
        population.add(_index,_solution);
    }

    public void addToPopulation(Solution _solution) {
        population.add(_solution);
    }

    public void sortPopulation(){
        Collections.sort(population);
    }

    public Solution[] getPair(){
        Solution[] pair = new Solution[2];
        pair[0] = population.get(random.nextInt(velkostPopulacie-1));
        pair[1] = population.get(random.nextInt(velkostPopulacie-2)+1);
        return pair;
    }

    public boolean contains (Solution _solution){
        for (Solution existingSolution:population
             ) {
            boolean match = true;
            for (int i = 0; i < existingSolution.length(); i++) {
                if (existingSolution.get(i) != _solution.get(i)){
                    match = false;
                }
            }
            if (match){
                return true;
            }
        }
        return false;
    }


}
