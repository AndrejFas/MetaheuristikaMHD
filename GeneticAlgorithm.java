import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    private static final Random random = new Random();
    public static Solution[] getPair(Population _population){
        return _population.getPair();
    }

    public static Solution[] crossing(Solution[] pair, Heuristic _heuristic, Validator _validator) {
        Solution[] children = new Solution[2];
        children[0] = new Solution( new int[pair[0].length()], pair[0].getSegments());
        children[1] = new Solution( new int[pair[0].length()], pair[0].getSegments());

        for (int i = 0; i < pair[0].length(); i++) {
            if ((random.nextDouble() % 1) < 0.5 ){
                children[0].set(i,pair[0].get(i));
                children[1].set(i,pair[1].get(i));
            } else {
                children[0].set(i,pair[1].get(i));
                children[1].set(i,pair[0].get(i));
            }
        }
        while (!_validator.validate(children[0], children[0].getSegments())) {
            _heuristic.fixTheSolution(children[0].getSegments(), _validator, children[0]);
        }
        while (!_validator.validate(children[1], children[1].getSegments())) {
            _heuristic.fixTheSolution(children[1].getSegments(), _validator, children[1]);
        }

        return children;
    }

    public static Solution mutate(Solution _newSolution, Validator _validator) {

        ArrayList<Integer> potentionalMutatePoint = new ArrayList<>();
        for (int i = 0; i < _newSolution.length(); i++) {
            potentionalMutatePoint.add(i);
        }
        while (true){
            int mutatePoint = -1;
            int usedCount = 0;
            int usedCost = 999999999;
            // try to decrease the cost
            for (int i = 0; i < potentionalMutatePoint.size(); i++) {
                if (_newSolution.get(potentionalMutatePoint.get(i)) == 1 &&
                        (_newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount() > usedCount ||
                        _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount() == usedCount &&
                                _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCost() < usedCost)){
                    mutatePoint = i;
                    usedCount = _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount();
                    usedCost = _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCost();
                }
            }
            if (mutatePoint == -1){
                break;
            }
            // try if valid
            Solution mutatedSolution = new Solution(_newSolution);
            mutatedSolution.set(potentionalMutatePoint.get(mutatePoint), 0);
            if (_validator.validate(mutatedSolution,mutatedSolution.getSegments())){
                _validator.seeValidate(mutatedSolution,mutatedSolution.getSegments());
                return mutatedSolution;
            }
            else {
                potentionalMutatePoint.remove(mutatePoint);
            }

        }


//        int mutatePoint = 0;
//        int usedCount = 9999999;
//        int usedCost = 0;
        // increase the cost
//        for (int i = 0; i < potentionalMutatePoint.size(); i++) {
//            if (_newSolution.get(potentionalMutatePoint.get(i)) == 0 &&
//                    (_newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount() < usedCount ||
//                            _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount() == usedCount &&
//                                    _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCost() > usedCost)){
//                mutatePoint = i;
//                usedCount = _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCount();
//                usedCost = _newSolution.getSegments().get(potentionalMutatePoint.get(i)).getCost();
//            }
//        }
        int mutatePoint = random.nextInt(potentionalMutatePoint.size() - 1);

        _newSolution.set(potentionalMutatePoint.get(mutatePoint), 1);
        return _newSolution;
    }
}
