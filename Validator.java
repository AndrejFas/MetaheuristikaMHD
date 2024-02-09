import java.util.ArrayList;

public class Validator {

    private final ArrayList<Turnus> turnuses;
    public Validator(ArrayList<Turnus> _turnuses) {
        turnuses = _turnuses;
    }

    public boolean validate(Solution _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
             ) {
            int idTurnus = validateTurnus(_solution, _segments, turnus);
            if( idTurnus >= 0){
                //System.out.println("Faild to validate turnus " + idTurnus + " (" + turnus.getName() + ")");
                return false;
            }
        }
        return true;
    }

    public int validateTurnus(Solution _solution, ArrayList<Segment> _segments, Turnus _turnus){
        double batteryCapacity = 30;
        double chargingSpeed = 0.0026;
        double consumptionSpeed = 0.0023;

        for (int i = 0; i < _turnus.getSegments().size(); i++) {
            if(_solution.get(_turnus.getSegments().get(i))== 1){
                batteryCapacity += _segments.get(_turnus.getSegments().get(i)).getCost() * chargingSpeed;
                if (batteryCapacity > 30){
                    batteryCapacity = 30;
                }
            }
            else {
                batteryCapacity -= _segments.get(_turnus.getSegments().get(i)).getCost() * consumptionSpeed;
                if (batteryCapacity <= 10){

                    return _turnus.getSegments().get(i);
                }
            }
        }
        return -1;
    }

}
