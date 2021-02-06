package de.duckcore.entjic;

import de.duckcore.entjic.builder.inventory.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicBoolean;

public class DuckCore {
    private static final DuckCore duckcore = new DuckCore();
    private JavaPlugin javaPlugin;
    private final AtomicBoolean REGISTERED = new AtomicBoolean(false);

    private DuckCore() {
    }

    public void init(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        helloWorld();
        registerSimpleInventory();
    }

    private void helloWorld() {
        javaPlugin.getServer().getConsoleSender()
                .sendMessage(ChatColor.AQUA +
                        "//   ____ _    ____ _  __   ____ ______    \n" +
                        "//  /  _ / \\ //   _/ |/ /  /  _ /  __/ \\ |\\\n" +
                        "//  | | \\| | ||  / |   /   | | \\|  \\ | | //\n" +
                        "//  | |_/| \\_/|  \\_|   \\   | |_/|  /_| \\// \n" +
                        "//  \\____\\____\\____\\_|\\_\\  \\____\\____\\__/  \n" +
                        "//");
    }

    private void registerSimpleInventory() {
        if (REGISTERED.getAndSet(true)) {
            throw new IllegalStateException("SimpleInventory is already registered");
        }
        Bukkit.getPluginManager().registerEvents(new InventoryListener(javaPlugin), javaPlugin);
    }

    public static DuckCore getDuckcore() {
        return duckcore;
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
