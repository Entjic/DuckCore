package de.duckcore.entjic.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;

public abstract class SimpleCommandExecutor implements CommandExecutor {
    boolean hasSubCommands = false;
    HashMap<String, SimpleCommandExecutor> subCommands = new HashMap<>();
    String subCommandDoesNotExist = "There is no such subcommand!";

    public SimpleCommandExecutor(boolean hasSubCommands){
        this.hasSubCommands = hasSubCommands;
    }

    public void registerCommand(String name, SimpleCommandExecutor subCommand) {
        subCommands.put(name.toLowerCase(), subCommand);
    }

    public void handleSubCommands(String subCommand, CommandSender sender, Command command, String[] args) {
        if (! hasSubCommands) return;
        if (! subCommands.containsKey(subCommand.toLowerCase())) {
            sender.sendMessage(subCommandDoesNotExist + " -> " + subCommand);
        }

        if (args.length > 1) {
            args = Arrays.copyOfRange(args, 1, args.length - 1);
        }

        subCommands.get(subCommand).onCommand(sender, command, command.getLabel(), args);
    }

}
