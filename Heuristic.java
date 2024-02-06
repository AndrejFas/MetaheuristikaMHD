import java.util.ArrayList;

public class Heuristic {
    private final int[] solution;
    private final ArrayList<Turnus> turnuses;

    public Heuristic(int _size, ArrayList<Turnus> _turnuses){
        solution = new int[_size];
        turnuses = _turnuses;
    }

    public int[] getSolution() {
        return solution;
    }

    public void createFirstValidSolution(ArrayList<Segment> _segments, Validator _validator){

        do {
            for (Turnus turnus:turnuses
            ) {
                int idTurnus = _validator.validateTurnus(solution, _segments, turnus);
                if( idTurnus >= 0){
                    solution[idTurnus] = 1;
                    break;
                }
            }
        } while (!_validator.validate(solution, _segments));

    }

}
