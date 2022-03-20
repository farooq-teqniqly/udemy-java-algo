public class Vertex {
    private final String key;
    private Integer distance;
    private DiscoveryState discoveryState;
    private Vertex predecessor;

    public Vertex(String key) {
        this.key = key;
        this.distance = Integer.MAX_VALUE;
        this.discoveryState = DiscoveryState.Undiscovered;
        this.predecessor = null;
    }

    public String getKey() {
        return this.key;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public DiscoveryState getDiscoveryState() {
        return discoveryState;
    }

    public void setDiscoveryState(DiscoveryState discoveryState) {
        this.discoveryState = discoveryState;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }
}

