package de.entjic;

import de.entjic.builder.ItemBuilder;
import de.entjic.builder.inventory.InventoryListener;
import de.entjic.builder.inventory.dialog.Dialog;
import de.entjic.builder.inventory.dialog.Node;
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
        Dialog root = new Dialog(0, 9, "Kompass");
        root.getInventory().setItem(4, new ItemBuilder(Material.GOLD_BLOCK).buildItemStack(), event -> {
            event.getWhoClicked().sendMessage("uwu");
        });
        Dialog node0 = new Dialog(1, 18, "Bedwars");
        node0.getInventory().setItem(0, new ItemBuilder(Material.CYAN_BED).buildItemStack(), event -> {
            event.getWhoClicked().sendMessage("c: != :c");
        });
        root.addChild(node0);
    }


    private void traverse(Node node) {
        node.getChildren().forEach(this::traverse);
    }

    public Dialog findNode(Dialog dialog, String find) {
        if (dialog.getInventory().getTitle().equals(find)) {
            return dialog;
        } else {
            for (Node child : dialog.getChildren()) {
                Dialog result = findNode((Dialog) child, find);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
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
