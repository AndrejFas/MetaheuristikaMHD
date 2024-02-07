import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Heuristic {
    private final ArrayList<Turnus> turnuses;
    private final Random random;

    public Heuristic(ArrayList<Turnus> _turnuses){
        turnuses = _turnuses;
        random = new Random();
    }

    public void createFirstValidSolution(ArrayList<Segment> _segments, Validator _validator, Population _population){

        Solution solution = new Solution(_segments);
        _population.addToPopulationOnIndex(solution, 0);

        do {
            for (Turnus turnus:turnuses
            ) {
                int idTurnus = _validator.validateTurnus(_population.getPopulation().get(0), _segments, turnus);
                if( idTurnus >= 0){
                    solution.set(idTurnus, 1);
                    break;
                }
            }
        } while (!_validator.validate(_population.getPopulation().get(0), _segments));

    }

    public void createRestOfThePopulation(ArrayList<Segment> _segments, Validator _validator, Population _population){
        for (int i = 1; i < 10; i++) {
            Solution solution = _population.getPopulation().get(0).getCopy();

            for (int j = 0; j < 10; j++) {
                int randInt = random.nextInt(solution.length());
                solution.set(randInt, (solution.get(randInt)+ 1) % 2);
            }

            while (!_validator.validate(solution, _segments)){
                for (Turnus turnus:turnuses
                ) {
                    int idTurnus = _validator.validateTurnus(solution, _segments, turnus);
                    if( idTurnus >= 0){
                        solution.set(idTurnus, 1);
                        break;
                    }
                }
            }

            _population.addToPopulationOnIndex(solution, i);
        }
    }

}
