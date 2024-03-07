package Flashcard;

import java.util.ArrayList;

public interface FileManager_inter {
    public ArrayList<String> readFile();

    public void writeFile(String fileContent);

    public void clearFileData();
}
