package storage;

public interface IStorage {
    void Create(IDataEntity value);
    String Read(String key);
    void Update(IDataEntity value);
    void Delete(String key);
    void Close();
}
