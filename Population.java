import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Population {

    private final ArrayList<Solution> population;
    private final ArrayList<Segment> segments;
    public Population(ArrayList<Segment> _segment){

        this.population = new ArrayList<>();
        this.segments = _segment;
    }

    public ArrayList<Solution> getPopulation() {
        return population;
    }

    public void addToPopulationOnIndex(Solution _solution, int _index) {
        population.add(_index,_solution);
    }



    public void sortPopulation(){
        Collections.sort(population);
    }
}
