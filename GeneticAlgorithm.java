import java.util.Random;

public class GeneticAlgorithm {

    private static final Random random = new Random();
    public static Solution[] getPair(Population _population){
        return _population.getPair();
    }

    public static Solution[] crossing(Solution[] pair, Heuristic _heuristic, Validator _validator) {
        int crossPoint = random.nextInt(pair[0].length());
        Solution[] children = new Solution[2];
        children[0] = new Solution( new int[pair[0].length()], pair[0].getSegments());
        children[1] = new Solution( new int[pair[0].length()], pair[0].getSegments());

        for (int i = 0; i < pair[0].length(); i++) {
            if (i < crossPoint){
                children[0].set(i,pair[0].get(i));
                children[1].set(i,pair[1].get(i));
            } else {
                children[0].set(i,pair[1].get(i));
                children[1].set(i,pair[0].get(i));
            }
        }
        _heuristic.fixTheSolution(children[0].getSegments(), _validator,children[0]);
        _heuristic.fixTheSolution(children[1].getSegments(), _validator,children[1]);

        return children;
    }

    public static Solution mutate(Solution newSolution, Heuristic _heuristic, Validator _validator) {
        int mutatePoint = random.nextInt(newSolution.length());
        newSolution.set(mutatePoint, 1 - newSolution.get(mutatePoint));
        _heuristic.fixTheSolution(newSolution.getSegments(), _validator,newSolution);
        return newSolution;
    }
}
