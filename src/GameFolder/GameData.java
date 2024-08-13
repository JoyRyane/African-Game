package GameFolder;

import java.util.prefs.*;

public class GameData {
    private static GameData instance;
    private int totalHints;
    private int totalCoins;
    private static final String HINTS_KEY = "totalHints";
    private static final String COINS_KEY = "totalCoins";
    private Preferences prefs;

    private GameData() {
        prefs = Preferences.userNodeForPackage(GameData.class);
//        this.totalHints = prefs.getInt(HINTS_KEY, 0); // Default to 0 if not found
//        this.totalCoins = prefs.getInt(COINS_KEY, 0); // Default to 0 if not found
        prefs.putInt(HINTS_KEY, 0);
        prefs.putInt(COINS_KEY, 0);
        this.totalHints = 0;
        this.totalCoins = 0;
    }

    public static synchronized GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public int getTotalHints() {
        return totalHints;
    }

    public void addHints(int hints) {
        totalHints += hints;
        prefs.putInt(HINTS_KEY, totalHints);
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void addCoins(int coins) {
        totalCoins += coins;
        prefs.putInt(COINS_KEY, totalCoins);
    }

    public void reset() {
        totalHints = 0;
        totalCoins = 0;
        prefs.putInt(HINTS_KEY, totalHints);
        prefs.putInt(COINS_KEY, totalCoins);
    }
}
