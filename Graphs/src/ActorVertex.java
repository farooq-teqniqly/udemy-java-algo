public class ActorVertex extends Vertex {

    private final String id;

    public ActorVertex(String key, String id) {

        super(key);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ActorVertex{" +
                "key='" + super.getKey() + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}
