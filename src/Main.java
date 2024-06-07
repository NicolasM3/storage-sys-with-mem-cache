import storage.IDataEntity;
import storage.IStorage;
import storage.Storage;

class Example implements IDataEntity {
    String id;
    String text;

    public Example(String id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override public String getId() {
        return id;
    }
}

public class Main {
    public static void main(String[] args) {
        IStorage storage1 = new Storage("example1.txt");
        storage1.Create(new Example("1", "Line 1"));
        storage1.Create(new Example("2", "Line 2"));
        storage1.Create(new Example("3", "Line 3"));
        storage1.Delete("1");
        storage1.Update(new Example("2","Line 2 updated"));
        System.out.println(storage1.Read("2"));

        IStorage storage2 = new Storage("example2.txt");
        storage2.Create(new Example("1", "This is the second file line 1"));
        storage2.Create(new Example("2", "This is the second file line 2"));
        storage2.Close();

        storage1.Close();
    }
}