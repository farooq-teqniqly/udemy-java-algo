public class GraphApp {
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        graph.add(a, b);
        graph.add(a, c);
        graph.add(b, d);
        graph.add(c, null);
        graph.add(d, null);

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

        System.out.println("Path from A to D: ");
        var path = searcher.path(a, d);

        while (!path.isEmpty()) {
            Vertex x = path.remove();
            System.out.print(x.getKey() + " -> ");
        }

        System.out.println();

        System.out.println("Path from C to A: ");
        path = searcher.path(c, a);

        while (!path.isEmpty()) {
            Vertex x = path.remove();
            System.out.print(x.getKey() + " -> ");
        }

    }
}
