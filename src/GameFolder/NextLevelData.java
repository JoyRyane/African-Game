package GameFolder;

import java.io.*;

public class NextLevelData {
    private static NextLevelData instance;
    private int nextLevel;
    private static final String FILE_NAME = "resources/nextlevel.txt";

    private NextLevelData() {
        loadFromFile();
    }

    public static synchronized NextLevelData getInstance() {
        if (instance == null) {
            instance = new NextLevelData();
        }
        return instance;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(nextLevel + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            nextLevel = 2;
            saveToFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            nextLevel = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            nextLevel = 2;
        }
    }
}
