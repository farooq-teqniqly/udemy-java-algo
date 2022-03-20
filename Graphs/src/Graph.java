import java.util.*;
import java.util.function.Consumer;

public class Graph {
    private final boolean directed;
    private final Map<String, List<Vertex>> adjacencyMap = new HashMap<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void add(Vertex a, Vertex b) {
        this.addRecursive(a, b, false);
    }

    private void addRecursive(Vertex a, Vertex b, boolean stop) {
        if (!this.adjacencyMap.containsKey(a.getKey())) {
            List<Vertex> list = new ArrayList<>();
            list.add(a);

            if (b != null) {
                list.add(b);
            }

            this.adjacencyMap.put(a.getKey(), list);
        } else {
            this.adjacencyMap.get(a.getKey()).add(b);
        }

        if (!this.directed && !stop && b != null) {
            this.addRecursive(b, a, true);
        }
    }

    public List<Vertex> getNeighbors(Vertex vertex) {
        List<Vertex> vertices = this.adjacencyMap.get(vertex.getKey());
        int length = vertices.size();

        if (length == 1) {
            return new ArrayList<>();
        }

        return vertices.subList(1, vertices.size());
    }

    public List<Vertex> getVerticies() {
        List<Vertex> vertices = new ArrayList<>();

        for (String key : this.adjacencyMap.keySet()) {
            vertices.add(this.adjacencyMap.get(key).get(0));
        }

        return vertices;
    }

}
