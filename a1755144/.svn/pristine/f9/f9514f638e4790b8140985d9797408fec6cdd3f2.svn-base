import java.util.LinkedList;
public class Graph{
	private LinkedList<Vertex> vertices = new LinkedList<>();
	public void add(Vertex vertex){
		vertices.add(vertex);
	}
	public int DFS(Vertex vertex){
		int sum = 0;
		vertex.isVisited = true;
		for(Edge e : vertex.getEdges()){
			sum+=e.getWeight();
			if(!e.getEnd().isVisited){
				sum+=DFS(e.getEnd());
			}
		}
		return sum;
	}
}