public class TestGraph{
	public static void main(String[] args) {

	Graph graph = new Graph();
		
	Vertex a = new Vertex("a");
	Vertex b = new Vertex("b");
	Vertex c = new Vertex("c");
	Vertex d = new Vertex("d");
	Vertex e = new Vertex("e");
	Vertex f = new Vertex("f");
	Vertex g = new Vertex("g");

	Edge ab = new Edge(a,b,2);
	Edge ae = new Edge(a,e,10);
	Edge bc = new Edge(b,c,3);
	Edge cd = new Edge(c,d,2);
	Edge cf = new Edge(c,f,1);
	Edge ea = new Edge(e,a,4);
	Edge ec = new Edge(e,c,5);
	Edge fc = new Edge(f,c,9);
	Edge fd = new Edge(f,d,8);
	Edge fe = new Edge(f,e,1);

	a.addEdge(ab);
	a.addEdge(ae);
	b.addEdge(bc);
	c.addEdge(cd);
	c.addEdge(cf);
	e.addEdge(ea);
	e.addEdge(ec);
	f.addEdge(fc);
	f.addEdge(fd);
	f.addEdge(fe);

	graph.add(a);
	graph.add(b);
	graph.add(c);
	graph.add(d);
	graph.add(e);
	graph.add(f);
	graph.add(g);

	System.out.println(graph.DFS(a));
	}
}