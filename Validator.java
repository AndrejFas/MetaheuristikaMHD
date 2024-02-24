import java.util.ArrayList;

public class Validator {

    private final ArrayList<Turnus> turnuses;
    public Validator(ArrayList<Turnus> _turnuses) {
        turnuses = _turnuses;
    }

    private final double maxBatteryCapacity = 40;

    private final double minBatteryCapacity = 10;
    private final double chargingSpeed = 0.0026;
    private final double consumptionSpeed = 0.0013;

    public boolean validate(Solution _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
             ) {
            int[] idTurnus = validateTurnus(_solution, _segments, turnus);
            if( idTurnus[0] >= 0){
                //System.out.println("Faild to validate turnus " + idTurnus + " (" + turnus.getName() + ")");
                return false;
            }
        }
        return true;
    }

    public int[] validateTurnus(Solution _solution, ArrayList<Segment> _segments, Turnus _turnus){
        double batteryCapacity = maxBatteryCapacity;

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
                    int[] result = new int[3];

                    result[0] = _turnus.getSegments().get(i);

                    if (i >= 1) {
                        result[1] = _turnus.getSegments().get(i - 1);
                    } else {
                        result[1] = -1;
                    }

                    if (i >= 2){
                        result[2] = _turnus.getSegments().get(i-2);
                    } else {
                        result[2] = -1;
                    }
                    return result;
                }
            }
        }
        int[] result = new int[3];
        result[0] = -1;
        return  result;
    }

    public void seeValidate(Solution _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
        ) {
            double batteryCapacity = maxBatteryCapacity;

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

    }

}
