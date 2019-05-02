package FileIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    private String filePath;

    public FileService(String filePath) {
        this.filePath = filePath;
    }
	
    public void readFileAndOutputToConsole() {
        try {
            Files.lines(Paths.get(this.filePath),StandardCharsets.ISO_8859_1)
            .forEach((line) -> {
                System.out.println(line);
            });

        } catch (FileNotFoundException ex) {
            System.out.println("File not exist." +ex);
        } catch (IOException ex) {
            System.out.println("File IO Exception" + ex);
        }
    }
    
    public static void main(String[] args) {
        String path = "./src/FileIO/fileIO.txt";
        FileService fileService = new FileService(path);
        fileService.readFileAndOutputToConsole();

    }
}