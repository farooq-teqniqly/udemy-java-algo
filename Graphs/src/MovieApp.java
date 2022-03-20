import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieApp {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\src\\my\\uwf-cop6416\\project\\py\\data\\processed\\movie_casts.csv";
        MovieFileProcessor processor = new MovieFileProcessor();
        List<CastMember> castMembers = processor.process(new Scanner(new File(fileName)));

        System.out.println("Created " + castMembers.size() + " movie records.");

        Graph graph = new Graph(true);

        for (CastMember castMember : castMembers) {
            graph.add(new MovieVertex(castMember.getTitle().trim()), new ActorVertex(castMember.getActor().trim()));
        }

        BFSSearcher searcher = new BFSSearcher(graph);

        String actorName = "Kevin Bacon";

        Vertex actor = graph.getVerticies()
                .stream()
                .filter(v -> v.getKey().equals(actorName))
                .collect(Collectors.toList())
                .get(0);

        searcher.Execute(actor);

        List<Vertex> discoveredVerticies = graph.getVerticies()
                .stream()
                .filter(v -> v.getDiscoveryState().equals(DiscoveryState.Discovered))
                .collect(Collectors.toList());

    }
}
