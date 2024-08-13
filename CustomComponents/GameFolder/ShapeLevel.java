package GameFolder;

public class ShapeLevel {
    private String name;
    private boolean locked;
    private boolean completed;
    private int index;

    public ShapeLevel(String name, boolean locked, boolean completed, int index) {
        this.name = name;
        this.locked = locked;
        this.completed = completed;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public int getIndex() {
    	return index;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setIndex(int index) {
    	this.index = index;
    }
}

