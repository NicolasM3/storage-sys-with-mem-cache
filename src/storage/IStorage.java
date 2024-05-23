package storage;

public interface IStorage {
    void Create(String value);
    String Read(Integer key);
    void Update(Integer key, String value);
    void Delete(Integer key);
    void Close();
}
