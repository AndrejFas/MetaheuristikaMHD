import java.util.ArrayList;

public class Validator {

    private final ArrayList<Turnus> turnuses;
    public Validator(ArrayList<Turnus> _turnuses) {
        turnuses = _turnuses;
    }

    public boolean validate(int[] _solution, ArrayList<Segment> _segments){

        for (Turnus turnus:turnuses
             ) {
            int batteryCapacity = 5000;

            for (int i = 0; i < turnus.getSegments().size(); i++) {
                if(_solution[turnus.getSegments().get(i)] == 1){
                    batteryCapacity += _segments.get(turnus.getSegments().get(i)).getCost();
                    if (batteryCapacity > 5000){
                        batteryCapacity = 5000;
                    }
                }
                else {
                    batteryCapacity -= _segments.get(turnus.getSegments().get(i)).getCost();
                    if (batteryCapacity <= 0){
                        System.out.println("Faild to validate turnus " + i + " (" + turnus.getName() + ")");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ArrayList<Turnus> getTurnuses() {
        return turnuses;
    }
}
