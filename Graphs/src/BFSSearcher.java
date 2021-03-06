import java.util.*;

public class BFSSearcher {
    private final Graph graph;
    private final Queue<Vertex> queue;

    public BFSSearcher(Graph graph) {
        this.graph = graph;
        this.queue = new LinkedList<>();
    }

    public void Execute(Vertex source) {
        source.setDistance(0);
        source.setDiscoveryState(DiscoveryState.Discovering);
        this.queue.add(source);

        while (!this.queue.isEmpty()) {
            Vertex currentVertex = this.queue.remove();

            for (Vertex neighbor : this.graph.getNeighbors(currentVertex)) {
                if (neighbor == null) {
                    continue;
                }

                if (neighbor.getDiscoveryState() == DiscoveryState.Undiscovered) {
                    neighbor.setDiscoveryState(DiscoveryState.Discovering);
                    neighbor.setDistance(currentVertex.getDistance() + 1);
                    neighbor.setPredecessor(currentVertex);
                    this.queue.add(neighbor);
                }
            }

            currentVertex.setDiscoveryState(DiscoveryState.Discovered);
        }
    }

    public List<Vertex> path(Vertex source, Vertex target) {
        List<Vertex> pathList = new ArrayList<>();
        Queue<Vertex> pathQueue = new LinkedList<>();
        this.pathRecursive(source, target, pathQueue);

        while (!pathQueue.isEmpty()) {
            Vertex vertex = pathQueue.remove();
            pathList.add(vertex);

            if (vertex.getKey().equals(target.getKey())) {
                break;
            }
        }

        Collections.reverse(pathList);

        return pathList;
    }

    private void pathRecursive(Vertex source, Vertex target, Queue<Vertex> pathQueue) {
        if (source.getKey().equals(target.getKey())) {
            pathQueue.add(source);
            return;
        }

        Vertex predecessor = target.getPredecessor();

        if (predecessor == null) {
            return;
        }

        this.pathRecursive(source, predecessor, pathQueue);
        pathQueue.add(target);
    }
}
