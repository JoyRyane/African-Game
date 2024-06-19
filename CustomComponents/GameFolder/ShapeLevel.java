package GameFolder;

public class ShapeLevel {
    private String name;
    private boolean locked;
    private boolean completed;

    public ShapeLevel(String name, boolean locked, boolean completed) {
        this.name = name;
        this.locked = locked;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

