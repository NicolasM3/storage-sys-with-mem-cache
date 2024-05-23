package file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileImp implements IFile {
    private String filePath;
    private BufferedWriter storageWriter;

    public FileImp(String filePath) {
        this.filePath = filePath;

        createStorage();
        storageWriter = createBuffers();
    }

    public void createStorage() {
        Path file = Paths.get(this.filePath);
        try {
            Files.createFile(file);
            System.out.println("File created successfully");
        } catch (IOException e) {
            System.out.println("A storage file already exist");
        }
    }


    public BufferedWriter createBuffers() {
        try{
            return Files.newBufferedWriter(Paths.get(this.filePath), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String text) {
        try {
            storageWriter.write(text);
            storageWriter.newLine();
            storageWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(Integer line) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.skip(line).findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
