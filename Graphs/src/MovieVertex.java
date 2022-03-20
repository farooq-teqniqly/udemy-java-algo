public class MovieVertex extends Vertex {

    private final String id;

    public MovieVertex(String key, String id) {
        super(key);

        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MovieVertex{" +
                "key='" + super.getKey() + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}

