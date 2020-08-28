package de.entjic.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileBuilder {
    File file;
    FileConfiguration config;

    public FileBuilder(File file, FileConfiguration config){
        this.file = file;
        this.config = config;
    }

    public static de.entjic.files.File create(Plugin plugin, String configName) {
        if (! plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        java.io.File configFile = new java.io.File(plugin.getDataFolder(), configName);
        if (! configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new de.entjic.files.File(configFile, YamlConfiguration.loadConfiguration(configFile));
    }

}
