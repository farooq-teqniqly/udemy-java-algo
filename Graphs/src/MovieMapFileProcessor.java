import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieMapFileProcessor implements MapFileProcessor {

    @Override
    public List<CastMember> process(Scanner scanner) {
        int lineCount = 0;
        List<CastMember> castMembers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (lineCount > 0) {
                String[] items = line.split("\\|");

                if (items.length != 3) {
                    continue;
                }

                String titleId = items[0];
                String[] cast = items[2].split(",");

                for (String actorId : cast) {
                    CastMember castMember = new CastMember(titleId, items[1], actorId, "");
                    castMembers.add(castMember);
                }
            }

            lineCount++;
        }

        return castMembers;
    }
}
