public class Edge{

	private Vertex begin;
	private Vertex end;
	private int weight;
	
	public Edge(Vertex begin, Vertex end, int weight){
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
	public void setBegin(Vertex begin){
		this.begin = begin;
	}
	public Vertex getBegin(){
		return this.begin;
	}
	public void setEnd(Vertex end){
		this.end = end;
	}
	public Vertex getEnd(){
		return this.end;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public int getWeight(){
		return this.weight;
	}			
}