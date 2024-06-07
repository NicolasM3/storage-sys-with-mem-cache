package memCache;

import java.util.List;

public interface IMemCache {
    void RemoveAddress(String key);
    Integer GetAddress(String key);
    void UpdateAddress(String key, Integer line);
    void saveBackup();
    List<String> getKeys();
}
