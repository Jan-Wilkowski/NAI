import java.util.List;

public class Node {
    private final List<Double> values;
    private final String name;
    public Node(List<Double> values,String name) {
        this.values = values;
        this.name = name;
    }
    public List<Double> getValues() {
        return values;
    }
    public String getName() {
        return name;
    }

}
