package de.entjic;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DuckCore extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "//   ____ _    ____ _  __   ____ ______    \n" +
                "//  /  _ / \\ //   _/ |/ /  /  _ /  __/ \\ |\\\n" +
                "//  | | \\| | ||  / |   /   | | \\|  \\ | | //\n" +
                "//  | |_/| \\_/|  \\_|   \\   | |_/|  /_| \\// \n" +
                "//  \\____\\____\\____\\_|\\_\\  \\____\\____\\__/  \n" +
                "//");
    }
}
