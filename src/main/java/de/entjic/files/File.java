package de.entjic.files;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class File{

    private java.io.File file;
    private FileConfiguration config;

    public File(java.io.File file, FileConfiguration config){
        this.file = file;
        this.config = config;
    }
    @Deprecated
    public <T> T get(String path) {
        return (T) config.get(path);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Cant save the config file: " + file.getAbsolutePath(), e);
        }
    }

    public String getString(String path){
        return config.getString(path);
    }

    public String getString(String path, String def){
        return config.getString(path,def);
    }

    public Integer getInt(String path){
        return config.getInt(path);
    }

    public Integer getInt(String path, int def){
        return config.getInt(path, def);
    }

    public void load() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getFileConfiguration(){
        return config;
    }


}
