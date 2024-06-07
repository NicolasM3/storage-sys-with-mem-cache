package memCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemCache implements IMemCache {
    Map<String, Integer> addressMap;
    private final String path;

    public MemCache(String path) {
        this.path = path;
        Path filePath = Paths.get(this.path + ".mem_cache");
        if(Files.exists(filePath)){
            try {
                String content = new String(Files.readAllBytes(filePath));
                String[] lines = content.split(";");
                addressMap = new HashMap<>();
                for(String line: lines){
                    String[] parts = line.split(":");
                    addressMap.put(parts[0], Integer.parseInt(parts[1]));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            addressMap = new HashMap<>();
        }
    }

    @Override
    public void RemoveAddress(String key) {
        addressMap.remove(key);
    }

    @Override
    public Integer GetAddress(String key) {
        return addressMap.get(key);
    }

    @Override
    public void UpdateAddress(String key, Integer line) {
        addressMap.put(key, line);
    }

    @Override
    public void saveBackup() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for(Map.Entry<String, Integer> entry: addressMap.entrySet()){
                lines.add(entry.getKey() + ":" + entry.getValue());
            }
            Files.write(Paths.get(this.path), String.join(";", lines).getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getKeys() {
        return new ArrayList<String>(addressMap.keySet());
    }
}
