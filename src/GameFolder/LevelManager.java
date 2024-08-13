package GameFolder;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private static LevelManager instance;
    private List<ShapeLevel> levels;

    private LevelManager() {
        levels = new ArrayList<>();
        levels.add(new ShapeLevel("LEVEL 1", false, true, 0));
        levels.add(new ShapeLevel("LEVEL 2", false, false, 1));
        levels.add(new ShapeLevel("LEVEL 3", true, false, 2));
        levels.add(new ShapeLevel("LEVEL 4", true, false, 3));
        levels.add(new ShapeLevel("LEVEL 5", true, false, 4));
        levels.add(new ShapeLevel("LEVEL 6", true, false, 5));
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

