import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieApp {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "C:\\src\\my\\uwf-cop6416\\project\\py\\data\\processed\\movie_casts.csv";
        MovieFileProcessor processor = new MovieFileProcessor();
        List<CastMember> castMembers = processor.process(new Scanner(new File(fileName)));

        System.out.println("Created " + castMembers.size() + " movie records.");

        Graph graph = new Graph(false);

        for (CastMember castMember : castMembers) {
            graph.add(
                    new MovieVertex(castMember.getTitle().trim(), castMember.getTitleId().trim()),
                    new ActorVertex(castMember.getActor().trim(), castMember.getActorId().trim()));
        }

        BFSSearcher searcher = new BFSSearcher(graph);

        Vertex source = graph.getAdjacencyMap().get("Kevin Bacon").get(0);
        Vertex target = graph.getAdjacencyMap().get("Joey Lawrence").get(0);


        searcher.Execute(source);

        List<Vertex> discoveredVerticies = graph.getVerticies()
                .stream()
                .filter(v -> v.getDiscoveryState().equals(DiscoveryState.Discovered) &&
                        v.getDistance() > 6)
                .collect(Collectors.toList());

        Queue<Vertex> path = searcher.path(source, target);

        while (!path.isEmpty()) {
            Vertex x = path.remove();
            System.out.println(x.toString());

            if (x.getKey().equals(target.getKey())) {
                break;
            }
        }
    }
}
