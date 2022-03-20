import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\src\\my\\uwf-cop6416\\project\\py\\data\\processed\\movie_casts.csv";

        System.out.println("Processing file " + fileName);

        MovieFileProcessor processor = new MovieFileProcessor();

        long start = System.currentTimeMillis();
        List<CastMember> castMembers = processor.process(new Scanner(new File(fileName)));
        long end = System.currentTimeMillis();

        System.out.println("Created " +
                castMembers.size() +
                " movie records in " +
                (end - start) +
                " ms.");

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

        BFSSearcher searcher = new BFSSearcher(graph);

        Vertex source = graph.getAdjacencyMap().get("nm0000102").get(0);
        Vertex target = graph.getAdjacencyMap().get("nm0000199").get(0);

        System.out.println("Running BFS.");

        start = System.currentTimeMillis();
        searcher.Execute(source);
        end = System.currentTimeMillis();

        System.out.println("BFS ran in " + (end - start) + " ms.");

        System.out.println("Getting shortest path.");

        start = System.currentTimeMillis();
        List<Vertex> path = searcher.path(source, target);
        end = System.currentTimeMillis();

        System.out.println("Got shortest path in " + (end - start) + " ms.");

        int degrees = -1;

        for (Vertex v : path) {
            System.out.println(v.toString());

            if (v instanceof ActorVertex) {
                degrees++;
            }
        }

        System.out.println(((ActorVertex)target).getId() +
                " has a " +
                ((ActorVertex)source).getId() +
                " number of "
                + degrees);
    }
}
