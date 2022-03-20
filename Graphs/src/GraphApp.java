public class GraphApp {
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");

        graph.add(a, b);
        graph.add(a, c);
        graph.add(b, d);
        graph.add(b, e);
        graph.add(b, h);
        graph.add(c, e);
        graph.add(d, f);
        graph.add(f, g);
        graph.add(g, h);
        graph.add(e, h);

        for (Vertex vertex : graph.getVerticies()) {
            System.out.println("Vertex " + vertex.getKey());

            System.out.print("Neighbors: ");
            for (Vertex neighbor : graph.getNeighbors(vertex)) {
                System.out.print(neighbor.getKey() + ", ");
            }

            System.out.println();
        }

        BFSSearcher searcher = new BFSSearcher(graph);
        searcher.Execute(a);

        System.out.println("Path from A to E: ");
        var path = searcher.path(a, e);

        while (!path.isEmpty()) {
            Vertex x = path.remove();
            System.out.print(x.getKey() + " -> ");
        }
    }
}
