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
        double maxBatteryCapacity = 40;
        double minBatteryCapacity = 10;
        double batteryCapacity = maxBatteryCapacity;
        double chargingSpeed = 0.0026;
        double consumptionSpeed = 0.0013;

        for (int i = 0; i < _turnus.getSegments().size(); i++) {
            if(_solution.get(_turnus.getSegments().get(i))== 1){
                batteryCapacity += _segments.get(_turnus.getSegments().get(i)).getCost() * chargingSpeed;
                if (batteryCapacity > maxBatteryCapacity){
                    batteryCapacity = maxBatteryCapacity;
                }
            }
            else {
                batteryCapacity -= _segments.get(_turnus.getSegments().get(i)).getCost() * consumptionSpeed;
                if (batteryCapacity <= minBatteryCapacity){

                    return _turnus.getSegments().get(i);
                }
            }
        }
        return -1;
    }

    public boolean seeValidate(Solution _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
        ) {
            double maxBatteryCapacity = 40;
            double batteryCapacity = maxBatteryCapacity;
            double minBatteryCapacity = 10;
            double chargingSpeed = 0.0026;
            double consumptionSpeed = 0.0013;

            for (int i = 0; i < turnus.getSegments().size(); i++) {
                if(_solution.get(turnus.getSegments().get(i))== 1){
                    batteryCapacity += _segments.get(turnus.getSegments().get(i)).getCost() * chargingSpeed;
                    if (batteryCapacity > maxBatteryCapacity){
                        batteryCapacity = maxBatteryCapacity;
                    }
                }
                else {
                    batteryCapacity -= _segments.get(turnus.getSegments().get(i)).getCost() * consumptionSpeed;

                }
                if (batteryCapacity < minBatteryCapacity){
                    System.out.println("Battery at " + i + " segment is: " + batteryCapacity);
                }

            }

        }
        return true;
    }

}
