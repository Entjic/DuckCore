package de.duckcore.entjic.files;

import de.duckcore.entjic.DuckCore;
import java.util.HashMap;

public class FileContainer {

  private final static FileContainer instance = new FileContainer();
  private final HashMap<String, File> files = new HashMap<>();

  public static FileContainer getInstance() {
    return instance;
  }

  public File getFile(String name) {
    if (files.containsKey(name)) {
      return files.get(name);
    } else {
      File file = FileBuilder.create(DuckCore.getDuckcore().getJavaPlugin(), name + ".yml");
      files.put(name, file);
      return file;
    }
  }

  public void reloadFiles() {
    files.values().forEach(File::load);
    files.values().forEach(File::save);
  }
}
