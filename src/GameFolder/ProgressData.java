package GameFolder;

import java.io.*;

public class ProgressData {
    private static ProgressData instance;
    private int progress; 
    private int score;    
    private static final String FILE_NAME = "resources/progressData.txt";

    private ProgressData() {
        loadFromFile();
    }

    public static synchronized ProgressData getInstance() {
        if (instance == null) {
            instance = new ProgressData();
        }
        return instance;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        saveToFile();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(progress + "\n");
            writer.write(score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            progress = 0;
            score = 0;
            saveToFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            progress = Integer.parseInt(reader.readLine());
            score = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            progress = 0;
            score = 0;
        }
    }
}
