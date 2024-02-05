
public class Segment {
    private final int id;
    private final int node1;
    private final int node2;
    private final int cost;

    public Segment(int _id, int _node1, int _node2, int _cost){
        id = _id;
        node1 = _node1;
        node2 = _node2;
        cost = _cost;
    }

    public int getId() {
        return id;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getCost() {
        return cost;
    }
}
