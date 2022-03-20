import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            System.out.println("USAGE: MovieApp [movie map file path] [source actor id] [target actor id]");
            return;
        }

        String fileName = args[0];
        String sourceActorId = args[1];
        String targetActorId = args[2];

        List<CastMember> castMembers = getCastMembers(fileName);

        Graph graph = createGraph(castMembers);

        Vertex source = graph.getAdjacencyMap().get(sourceActorId).get(0);
        Vertex target = graph.getAdjacencyMap().get(targetActorId).get(0);

        BFSSearcher searcher = runBFS(graph, source, target);

        List<Vertex> path = getShortestPath(source, target, searcher);

        printShortestPath(source, target, path);
    }

    private static void printShortestPath(Vertex source, Vertex target, List<Vertex> path) {
        int degrees = -1;

        for (Vertex v : path) {
            System.out.print(v.toString());

            if (v instanceof ActorVertex) {
                degrees++;

                if (!v.getKey().equals(source.getKey())) {
                    System.out.println(" was in");
                }

            }

            if (v instanceof MovieVertex) {
                System.out.println(" with");
            }
        }

        System.out.println();
        System.out.println(target.getKey() +
                " has a " +
                source.getKey() +
                " number of "
                + degrees);
    }

    private static List<Vertex> getShortestPath(Vertex source, Vertex target, BFSSearcher searcher) {
        long start;
        long end;
        System.out.println("Getting shortest path.");

        start = System.currentTimeMillis();
        List<Vertex> path = searcher.path(source, target);
        end = System.currentTimeMillis();

        System.out.println("Got shortest path in " + (end - start) + " ms.");

        return path;
    }

    private static Graph createGraph(List<CastMember> castMembers) {
        long start;
        long end;
        Graph graph = new Graph(false);

        start = System.currentTimeMillis();

        for (CastMember castMember : castMembers) {
            graph.add(
                    new MovieVertex(castMember.getTitleId().trim(), castMember.getTitle().trim()),
                    new ActorVertex(castMember.getActorId().trim(), castMember.getActor().trim()));
        }

        end = System.currentTimeMillis();

        System.out.println("Created a graph of " +
                graph.getAdjacencyMap().size() +
                " verticies in "
                + (end - start) +
                " ms.");

        return graph;
    }

    private static List<CastMember> getCastMembers(String fileName) throws FileNotFoundException {
        System.out.println("Processing file " + fileName);

        MapFileProcessor processor = new MovieMapFileProcessor();

        long start = System.currentTimeMillis();
        List<CastMember> castMembers = processor.process(new Scanner(new File(fileName)));
        long end = System.currentTimeMillis();

        System.out.println("Created " +
                castMembers.size() +
                " movie records in " +
                (end - start) +
                " ms.");

        return castMembers;
    }

    private static BFSSearcher runBFS(Graph graph, Vertex source, Vertex target) {
        long start;
        long end;
        BFSSearcher searcher = new BFSSearcher(graph);

        System.out.println("Running BFS.");

        start = System.currentTimeMillis();
        searcher.Execute(source);
        end = System.currentTimeMillis();

        System.out.println("BFS ran in " + (end - start) + " ms.");

        return searcher;
    }
}

