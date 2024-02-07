import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Population {

    private final ArrayList<Solution> population;
    private final ArrayList<Segment> segments;

    private final Random random;
    public Population(ArrayList<Segment> _segment){
        this.random = new Random();
        this.population = new ArrayList<>();
        this.segments = _segment;
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
        pair[0] = population.get(0);
        pair[1] = population.get(random.nextInt(8)+1);
        return pair;
    }


}
