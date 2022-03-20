import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\src\\my\\uwf-cop6416\\project\\py\\data\\processed\\movie_casts.csv";
        MovieFileProcessor processor = new MovieFileProcessor();
        List<CastMember> castMembers = processor.process(new Scanner(new File(fileName)));

        System.out.println("Created " + castMembers.size() + " movie records.");

        Graph graph = new Graph(false);

        for (CastMember castMember : castMembers) {
            graph.add(
                    new MovieVertex(castMember.getTitleId().trim(), castMember.getTitle().trim()),
                    new ActorVertex(castMember.getActorId().trim(), castMember.getActor().trim()));
        }

        BFSSearcher searcher = new BFSSearcher(graph);

        Vertex source = graph.getAdjacencyMap().get("nm0000102").get(0);
        Vertex target = graph.getAdjacencyMap().get("nm0000199").get(0);

        searcher.Execute(source);

        List<Vertex> path = searcher.path(source, target);
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
