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

        boolean needToBeFixed = true;
        while (needToBeFixed) {
            needToBeFixed = fixTheSolution(_segments, _validator, solution);
        }
        _population.addToPopulation(solution);

    }

    public void createRestOfThePopulation(ArrayList<Segment> _segments, Validator _validator, Population _population, int _velkostPopulacie){
        for (int i = 1; i < _velkostPopulacie; i++) {
            Solution solution = _population.getPopulation().get(0).getCopy();

            for (int j = 0; j < 30; j++) {
                int randInt = random.nextInt(solution.length());
                solution.set(randInt, 1 - solution.get(randInt));
            }

            boolean needToBeFixed = true;
            while (needToBeFixed){
                needToBeFixed = fixTheSolution(_segments, _validator, solution);
            }

            _population.addToPopulation(solution);
        }
    }

    public boolean fixTheSolution(ArrayList<Segment> _segments, Validator _validator, Solution _solution){
        for (Turnus turnus:turnuses
        ) {
            int[] idTurnus = _validator.validateTurnus(_solution, _segments, turnus);
            if( idTurnus[0] >= 0){
                if (idTurnus[2] >= 0){

                    if ( _solution.get(idTurnus[2]) != 1 && _segments.get(idTurnus[2]).getCount() > _segments.get(idTurnus[1]).getCount() &&
                            _segments.get(idTurnus[2]).getCount() > _segments.get(idTurnus[0]).getCount()){
                        _solution.set(idTurnus[2], 1);

                    } else if ( _solution.get(idTurnus[1]) != 1 && _segments.get(idTurnus[1]).getCount() > _segments.get(idTurnus[0]).getCount() &&
                            _segments.get(idTurnus[1]).getCount() > _segments.get(idTurnus[2]).getCount()){
                        _solution.set(idTurnus[1], 1);

                    } else {
                        _solution.set(idTurnus[0], 1);
                    }

                } else if (idTurnus[1] >= 0) {
                    if ( _solution.get(idTurnus[1]) != 1 && _segments.get(idTurnus[1]).getCount() > _segments.get(idTurnus[0]).getCount()){
                        _solution.set(idTurnus[1], 1);

                    } else {
                        _solution.set(idTurnus[0], 1);
                    }
                } else {
                    _solution.set(idTurnus[0], 1);
                }
                return true;
            }
        }
        return false;
    }

}
