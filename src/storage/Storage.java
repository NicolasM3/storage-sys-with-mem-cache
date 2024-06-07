package storage;

import file.FileImp;
import file.IFile;
import memCache.IMemCache;
import memCache.MemCache;

import java.util.List;

public class Storage implements IStorage {
    private final IFile file;
    private final IMemCache addressCache;
    private Integer lastRegister = 0;

    public Storage(String storagePath) {
        this.file = new FileImp(storagePath);
        this.addressCache = new MemCache(storagePath + ".mem_cache");
    }

    @Override
    public void Create(IDataEntity value) {
        try{
            file.write(value.toString());
            addressCache.UpdateAddress(value.getId(), this.lastRegister++);
        } catch (Exception e){
            throw new RuntimeException("Error creating file");
        }

    }

    @Override
    public String Read(String key) {
        try {
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            Integer file_line = addressCache.GetAddress(key);
            return file.read(file_line);
        } catch (Exception e){
            throw new RuntimeException("Error reading file");
        }
    }

    @Override
    public void Update(IDataEntity value) {
        String key = value.getId();
        try{
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            file.write(value.toString());
            addressCache.UpdateAddress(key, this.lastRegister++);
        } catch (Exception e){
            throw new RuntimeException("Error updating file");
        }
    }

    @Override
    public void Delete(String key) {
        try{
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            addressCache.RemoveAddress(key);
        } catch (Exception e){
            throw new RuntimeException("Error deleting file");
        }
    }

    public void Close(){
        addressCache.saveBackup();
    }

    private Boolean hasKey(String key){
        return addressCache.GetAddress(key) != null;
    }
}
