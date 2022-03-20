public class CastMember {
    private final String titleId;
    private final String title;
    private final String actorId;
    private final String actor;

    public CastMember(String titleId, String title, String actorId, String actor) {

        this.titleId = titleId;
        this.title = title;
        this.actorId = actorId;
        this.actor = actor;
    }

    public String getTitleId() {
        return titleId;
    }

    public String getTitle() {
        return title;
    }

    public String getActorId() {
        return actorId;
    }

    public String getActor() {
        return actor;
    }
}
