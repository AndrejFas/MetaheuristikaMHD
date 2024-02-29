
public class Segment implements Comparable<Segment> {

    private int count;
    private final int index;
    private final int id;
    private final int[] nodes;
    private final int cost;

    public Segment(int _index, int _id, int _node1, int _node2, int _cost){
        this.index = _index;
        this.id = _id;
        this.nodes = new int[2];
        nodes[0] = _node1;
        nodes[1] = _node2;
        this.cost = _cost;
        this.count = 0;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public void increase() {
        count++;
    }

    public int getCount(){
        return count;
    }

    public int[] getNodes() {
        return nodes;
    }

    @Override
    public int compareTo(Segment other) {
        if (this.getCount() != other.getCount()){
            return this.count - other.getCount();
        }
        return other.getCost() - this.getCost();

    }
    public int getIndex() {
        return index;
    }
}
