package Flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager implements FileManager_inter {
    private String fileName;
    private File fileObj;

    public FileManager(String fileName) {

        this.fileName = fileName;
        fileObj = new File(fileName);
    }

    public ArrayList<String> readFile() {
        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(fileObj);

            while (fileReader.hasNextLine()) {
                // String dataName[] = fileReader.nextLine().split("\n");
                // data += dataName[0] + "\n";
                String d = fileReader.nextLine();
                data.add(d);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            return new ArrayList<>() {
            };
        }
        return data;
    }

    public void writeFile(String fileContent) {

        try {
            FileWriter fileWriterObj = new FileWriter(this.fileName, true);

            // String[] data = fileContent.split("")
            fileWriterObj.write(fileContent + "\n");

            fileWriterObj.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile() {

    }

    public void clearFileData() {
        try {
            FileWriter fileWriterObj = new FileWriter(this.fileName);

            fileWriterObj.write("");
            fileWriterObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
