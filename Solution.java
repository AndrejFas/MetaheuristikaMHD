import java.util.ArrayList;
import java.util.Arrays;

public class Solution implements Comparable<Solution> {
    private final int[] solution;
    private final ArrayList<Segment> segments;
    public Solution(ArrayList<Segment> _segments){
        this.solution = new int[_segments.size()];
        this.segments = _segments;
    }

    public Solution(int[] _solution, ArrayList<Segment> _segments){
        this.solution = _solution;
        this.segments = _segments;
    }

    public int[] getSolution() {
        return solution;
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

    public Solution getCopy(){
        return new Solution(Arrays.copyOf(solution,solution.length), segments);
    }

    @Override
    public int compareTo(Solution other) {
        return Integer.compare(this.getCost(), other.getCost());
    }
}
