import java.util.LinkedList;

public class Node{
	private String value;
	private LinkedList<Edge> edges = new LinkedList<>();
	private boolean isVisited = false;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}