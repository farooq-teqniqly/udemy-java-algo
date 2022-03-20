public class ActorVertex extends Vertex {

    private final String name;

    public ActorVertex(String key, String name) {

        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ActorVertex{" +
                "key='" + super.getKey() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
