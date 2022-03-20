import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieFileProcessor implements MapFileProcessor {

    @Override
    public List<CastMember> process(Scanner scanner) {
        int lineCount = 0;
        List<CastMember> castMembers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (lineCount > 0) {
                String[] items = line.split(",");

                if (items.length != 4) {
                    continue;
                }

                CastMember castMember = new CastMember(items[0], items[1], items[2], items[3]);
                castMembers.add(castMember);
            }

            lineCount++;
        }

        return castMembers;
    }
}

