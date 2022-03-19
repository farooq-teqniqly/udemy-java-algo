import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void showNeighbors(Consumer<List<Vertex>> callback) {
        if (callback != null) {
            for (String key : this.adjacencyMap.keySet()) {
                callback.accept(this.adjacencyMap.get(key));
            }
        }
    }
}
