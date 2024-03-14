public class ComparedDistances implements Comparable<ComparedDistances> {

    @Override
    public int compareTo(ComparedDistances anotherNode) {
        return Double.compare(this.distance, anotherNode.distance);
    }

    private final Node nodeTest;
    private final Node nodeTrain;
    private final double distance;
    public ComparedDistances(Node nodeTest, Node nodeTrain, double distance) {
        this.nodeTest = nodeTest;
        this.nodeTrain = nodeTrain;
        this.distance = distance;
    }
    public Node getNodeTrain() {
        return nodeTrain;
    }
}

