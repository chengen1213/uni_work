public class Main{
    public static void main(String[] args) {
        Graph graph = new Graph();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
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
        Edge fg = new Edge(f,g,7);
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
        f.addEdge(fg);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        System.out.print(graph.traverse(a));

    }
}