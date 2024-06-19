package GameFolder;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private static LevelManager instance;
    private List<ShapeLevel> levels;

    private LevelManager() {
        levels = new ArrayList<>();
        levels.add(new ShapeLevel("LEVEL 1", false, true));
        levels.add(new ShapeLevel("LEVEL 2", false, false));
        levels.add(new ShapeLevel("LEVEL 3", true, false));
        levels.add(new ShapeLevel("LEVEL 4", true, false));
        levels.add(new ShapeLevel("LEVEL 5", true, false));
        levels.add(new ShapeLevel("LEVEL 6", true, false));
    }

    public static LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public List<ShapeLevel> getLevels() {
        return levels;
    }
}

