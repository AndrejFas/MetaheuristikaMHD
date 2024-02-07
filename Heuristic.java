import java.util.ArrayList;
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
            fixTheSolution(_segments, _validator, solution);
        } while (!_validator.validate(_population.getPopulation().get(0), _segments));

    }

    public void createRestOfThePopulation(ArrayList<Segment> _segments, Validator _validator, Population _population, int _velkostPopulacie){
        for (int i = 1; i < _velkostPopulacie; i++) {
            Solution solution = _population.getPopulation().get(0).getCopy();

            for (int j = 0; j < 10; j++) {
                int randInt = random.nextInt(solution.length());
                solution.set(randInt, 1 - solution.get(randInt));
            }

            while (!_validator.validate(solution, _segments)){
                fixTheSolution(_segments, _validator, solution);
            }

            _population.addToPopulationOnIndex(solution, i);
        }
    }

    public void fixTheSolution(ArrayList<Segment> _segments, Validator _validator, Solution _solution){
        for (Turnus turnus:turnuses
        ) {
            int idTurnus = _validator.validateTurnus(_solution, _segments, turnus);
            if( idTurnus >= 0){
                _solution.set(idTurnus, 1);
                break;
            }
        }
    }

}
