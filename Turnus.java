import java.util.ArrayList;

public class Turnus {
    private final int id;
    private final String name;
    private final int count;
    private final ArrayList<Integer> segments;

    public Turnus(int _id, String _name, int _count, ArrayList<Integer> _segments){

        this.id = _id;
        this.name = _name;
        this.count = _count;
        this.segments = _segments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Integer> getSegments() {
        return segments;
    }
}
