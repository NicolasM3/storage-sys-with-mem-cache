package storage;

import file.FileImp;
import file.IFile;
import memCache.IMemCache;
import memCache.MemCache;

public class Storage implements IStorage{
    private String storagePath = "raw_storage.txt";
    private IFile file = new FileImp(storagePath);
    private IMemCache addressCache = new MemCache();
    private Integer lastLine = 0;

    @Override
    public void Create(String value) {
        try{
            file.write(value);
            addressCache.UpdateAddress(lastLine.toString(), lastLine);
            lastLine++;
        } catch (Exception e){
            throw new RuntimeException("Error creating file");
        }

    }

    @Override
    public String Read(Integer key) {
        try {
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            Integer file_line = addressCache.GetAddress(key.toString());
            return file.read(file_line);
        } catch (Exception e){
            throw new RuntimeException("Error reading file");
        }
    }

    @Override
    public void Update(Integer key, String value) {
        try{
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            file.write(value);
            addressCache.UpdateAddress(key.toString(), ++lastLine);
        } catch (Exception e){
            throw new RuntimeException("Error updating file");
        }
    }

    @Override
    public void Delete(Integer key) {
        try{
            if(!hasKey(key)){
                throw new Exception("Key not found");
            }

            addressCache.RemoveAddress(key.toString());
        } catch (Exception e){
            throw new RuntimeException("Error deleting file");
        }
    }

    public void Close(){
        addressCache.saveBackup();
    }

    private Boolean hasKey(Integer key){
        return addressCache.GetAddress(key.toString()) != null;
    }
}
