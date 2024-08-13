package GameFolder;

import java.io.*;

public class CategoryProgressData {
    private static CategoryProgressData instance;
    private int progress; 
    private static final String FILE_NAME = "resources/categoryProgressData.txt";

    private CategoryProgressData() {
        loadFromFile();
    }

    public static synchronized CategoryProgressData getInstance() {
        if (instance == null) {
            instance = new CategoryProgressData();
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
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(progress + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            progress = 0;
            saveToFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            progress = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            progress = 0;
        }
    }
}
