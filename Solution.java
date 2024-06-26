import java.util.ArrayList;


public class Solution implements Comparable<Solution> {
    private final int[] solution;
    private final ArrayList<Segment> segments;
    public Solution(ArrayList<Segment> _segments){
        this.solution = new int[_segments.size()];
        this.segments = _segments;
    }

    public Solution(Solution _solution){
        this.solution = new int[_solution.getSegments().size()];
        this.segments = _solution.getSegments();
        for (int i = 0; i < this.solution.length; i++) {
            this.solution[i] = _solution.get(i);
        }
    }

    public Solution(int[] _solution, ArrayList<Segment> _segments){
        this.solution = _solution;
        this.segments = _segments;
    }
    public ArrayList<Segment> getSegments(){
        return segments;
    }

    public int getCost(){
        int cost = 0;
        for (int i = 0; i < solution.length; i++) {
            if (solution[i] == 1)
                cost += segments.get(i).getCost();
        }
        return cost;
    }

    public int length(){
        return solution.length;
    }

    public int get(int _index){
        return solution[_index];
    }

    public void set(int _index, int _value) {
        solution[_index] = _value;
    }

    @Override
    public int compareTo(Solution other) {
        return Integer.compare(this.getCost(), other.getCost());
    }
}
