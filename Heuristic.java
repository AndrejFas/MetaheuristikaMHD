import java.util.ArrayList;

public class Heuristic {
    private final int[] solution;

    public Heuristic(int _size){
        solution = new int[_size];
    }

    public int[] getSolution() {
        return solution;
    }

    public void createFirstValidSolution(ArrayList<Segment> _segments, Validator _validator){

        do {
            int idMaxCost = -1;
            int maxCost = 0;

            for (int i = 0; i < _segments.size(); i++) {
                if(_segments.get(i).getCost() > maxCost && solution[i] == 0){
                    maxCost = _segments.get(i).getCost();
                    idMaxCost = i;
                }
            }

            if (idMaxCost != -1){
                solution[idMaxCost] = 1;
            }
        } while (!_validator.validate(solution, _segments));


    }
}
