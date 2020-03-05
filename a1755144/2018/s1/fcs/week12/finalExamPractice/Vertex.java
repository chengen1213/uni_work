import java.util.LinkedList;
public class Vertex{
	private String value;
	public boolean isVisited = false;
	private LinkedList<Edge> edges = new LinkedList<>();
	public Vertex(String value){
		this.value = value;
	}
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	public LinkedList<Edge> getEdges(){
		return this.edges;
	}
}