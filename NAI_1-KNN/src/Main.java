import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.exit(1);
        }
        int k = Integer.parseInt(args[0]);
        String train = args[1];
        String test = args[2];

        List<Node> trainList = getList(train);
        List<Node> testList = getList(test);

        KNN(testList, trainList, k);
    }

    public static List<Node> getList(String file) throws IOException {
        String line;
        List<Node> nodeList = new ArrayList<>();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            String[] tmp = line.split(";");
            List<Double> values = new ArrayList<>();
            for (int i = 0; i < tmp.length - 1; i++)
                values.add(Double.parseDouble(tmp[i]));
            String name = tmp[tmp.length - 1];
            nodeList.add(new Node(values, name));
        }
        return nodeList;
    }

    public static double getDistance(Node node1, Node node2) {
        double distance = 0;
        int size = node1.getValues().size();

        for (int i = 0; i < size; i++) {
            distance += Math.pow(node1.getValues().get(i) - node2.getValues().get(i), 2); //licz kwadrat
        }
        return distance;
    }

    public static String KNN(List<Node> nodeTestList, List<Node> nodeTrainList, int k) {

        String label = "";
        double sameLabel = 0;
        for (Node testedNode : nodeTestList) {

            List<ComparedDistances> distanceList = new ArrayList<>();

            for (Node trainedNode : nodeTrainList)
                distanceList.add(new ComparedDistances(testedNode, trainedNode, getDistance(testedNode, trainedNode))); //lista par wezlow i odleglosci
            Collections.sort(distanceList);

            List<String> stringList = new ArrayList<>();
            Set<String> stringSet = new HashSet<>();

            for (int j = 0; j < k; j++) {
                stringList.add(distanceList.get(j).getNodeTrain().getName());
                stringSet.add(distanceList.get(j).getNodeTrain().getName());
            }
            int max = 0;
            for (String string : stringSet) {
                if (Collections.frequency(stringList, string) > max) {
                    max = Collections.frequency(stringList, string);
                    label = string;
                }
            }
            if (label.equals(testedNode.getName()))
                sameLabel++;

            System.out.println();
            System.out.println("K = " + k);
            System.out.println("most common label: " + label);
            System.out.println("target label: " + testedNode.getName());
            System.out.println("accuracy the cure: " + (sameLabel / nodeTestList.size()) * 100 + "%");
        }
        return label;
    }
}
