public class MovieVertex extends Vertex {

    private final String title;

    public MovieVertex(String key, String title) {
        super(key);

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "MovieVertex{" +
                "key='" + super.getKey() + '\'' +
                "title='" + title + '\'' +
                '}';
    }
}

