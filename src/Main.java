import storage.IStorage;
import storage.Storage;

public class Main {
    public static void main(String[] args) {
        IStorage storage = new Storage();
        storage.Create("Line 1");
        storage.Create("Line 2");
        storage.Create("Line 3");

        storage.Close();
    }
}