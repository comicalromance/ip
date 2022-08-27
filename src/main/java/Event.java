public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description, Tag.E);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description + " (" + time + ")";
    }
}