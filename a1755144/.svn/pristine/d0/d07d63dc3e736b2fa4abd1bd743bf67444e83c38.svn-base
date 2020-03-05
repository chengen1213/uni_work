import java.util.LinkedList;

public class Graph {

    private LinkedList<Node> nodes = new LinkedList<>();

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public int traverse(Node node) {
        int sum = 0;
        node.setVisited(true);
        for (Edge e : node.getEdges()) {
            sum += e.weight;
            if(!e.getEndNode().isVisited()){
                sum += traverse(e.getEndNode());
            }
        }
        return sum;
    }
}