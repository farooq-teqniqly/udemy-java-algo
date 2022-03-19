public class GraphApp {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        graph.add(a, b);
        graph.add(a, c);
        graph.add(b, d);
        graph.add(c, null);
        graph.add(d, null);

        graph.showNeighbors(neighbors -> {
            for (Vertex neighbor : neighbors) {
                if (neighbor == null) {
                    continue;
                }

                System.out.print(neighbor.getKey() + ", ");
            }

            System.out.println();
        });
    }
}
