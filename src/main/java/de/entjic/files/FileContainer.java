package de.entjic.files;

import de.entjic.DuckCore;

import java.util.HashMap;

public class FileContainer {
    private final static FileContainer instance = new FileContainer();
    private HashMap<String, File> files = new HashMap<>();

    public static FileContainer getInstance() {
        return instance;
    }

    public File getFile(String name) {
        if(files.containsKey(name)) {
            return files.get(name);
        } else {
            File file = FileBuilder.create(DuckCore.getProvidingPlugin(DuckCore.class), name + ".yml");
            files.put(name, file);
            return file;
        }
    }

    public void reloadFiles(){
        files.values().forEach(File::load);
        files.values().forEach(File::save);
    }
}
