package objects;

import java.io.Serializable;
import java.util.Objects;
import utils.FileUtils;

public class MP3 implements Serializable{

    private String name; //имя файла
    private String path; //путь к файлу
    private int duration; // длительность

    //имя и путь передаются с помощью этого конструктора
    public MP3(String name, String path) {
        this.name = name;
        this.path = path;
    }

    
    @Override
    // для корректного отображения объекта MP3 при добавлении в плейлист
    public String toString() {
        return FileUtils.getFileNameWithoutExtension(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MP3)) return false;
            
        MP3 mp3 = (MP3)obj;
        return path.equals(mp3.getPath());
    }
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.path);
        return hash;
    }
    
   
   
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
   
}
