package de.entjic;

import de.entjic.builder.ItemBuilder;
import de.entjic.builder.inventory.InventoryListener;
import de.entjic.builder.inventory.dialog.DialogBuilder;
import de.entjic.builder.inventory.dialog.InventoryDialog;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        //     InventoryDialog root = createDialog();
        //     traverse(root);
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
        InventoryDialog root = new InventoryDialog(0, 9);
        root.setItem(4, new ItemBuilder(Material.GOLD_BLOCK).buildItemStack(), event -> {
            event.getWhoClicked().sendMessage("uwu");
        });
        InventoryDialog node0 = new InventoryDialog(1, 18);
        node0.setItem(0, new ItemBuilder(Material.CYAN_BED).buildItemStack(), event -> {
            event.getWhoClicked().sendMessage("");
        });
        root.addChild(node0);
    }


    private void traverse(InventoryDialog node) {
        node.getChildren().forEach(this::traverse);
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
