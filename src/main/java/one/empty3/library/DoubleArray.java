package one.empty3.library;
import java.util.List;
public class DoubleArray {
    private int length = 1000*1000*1000;
    List<double[]> doubles;
    List<int> index;
    List<int> location;
    int max = 0;
    public DoubleArray(){
        doubles = new ArrayList<>();
        doubles.add(new double[length]);
        index = new ArrayList<>();
        index.add(new int[length]);
      //  location = new ArrayList<>()
    }
    public double getDouble(int index) {
        return doubles.get(0)[this.index.get(0)[index]];
    }
    public double getDouble(int index, int n) {
        return doubles.get(0)[this.index.get(0)[index]+n];
    }
    
    public int add(Double d) {
        int start = max;
        doubles.get(0)[this.index.get(0)[max] = max] = d;
        max++;
        return start;
    }
    public int addDoubles(int n, double... ds) {
        int start = max;
        for (int i = 0; i<n ; i++) {
            doubles.get(0)[this.index.get(0)[max] = max] = d;
            max++;
        }
        return start;
        
    }
}
