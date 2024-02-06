import java.util.ArrayList;

public class Validator {

    private final ArrayList<Turnus> turnuses;
    public Validator(ArrayList<Turnus> _turnuses) {
        turnuses = _turnuses;
    }

    public boolean validate(int[] _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
             ) {
            int idTurnus = validateTurnus(_solution, _segments, turnus);
            if( idTurnus >= 0){
                System.out.println("Faild to validate turnus " + idTurnus + " (" + turnus.getName() + ")");
                return false;
            }
        }
        return true;
    }

    public int validateTurnus(int[] _solution, ArrayList<Segment> _segments, Turnus _turnus){
        int batteryCapacity = 5000;

        for (int i = 0; i < _turnus.getSegments().size(); i++) {
            if(_solution[_turnus.getSegments().get(i)] == 1){
                batteryCapacity += _segments.get(_turnus.getSegments().get(i)).getCost();
                if (batteryCapacity > 5000){
                    batteryCapacity = 5000;
                }
            }
            else {
                batteryCapacity -= _segments.get(_turnus.getSegments().get(i)).getCost();
                if (batteryCapacity <= 0){

                    return _turnus.getSegments().get(i);
                }
            }
        }
        return -1;
    }

}
