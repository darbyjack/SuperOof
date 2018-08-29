package me.glaremasters.superoof.commands.base;

import me.glaremasters.superoof.utils.IHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by GlareMasters
 * Date: 8/29/2018
 * Time: 9:02 AM
 */
public class CommandHandler implements CommandExecutor, TabCompleter, IHandler {

    private List<CommandBase> commands;

    @Override
    public void enable() {
        commands = new ArrayList<>();
    }

    @Override
    public void disable() {
        commands.clear();
        commands = null;
    }

    public void register(CommandBase command) {
        commands.add(command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("so")) return true;
        if (args.length == 0 || args[0].isEmpty()) {
            getCommand("help").execute(sender, args);
            return true;
        }

        for (CommandBase command : commands) {
            if (!command.getName().equalsIgnoreCase(args[0]) && !command.getAliases().contains(args[0].toLowerCase())) continue;
            if (!command.allowConsole() && !(sender instanceof Player)) {
                // Possibly send a message here saying console isn't allowed for it
                return true;
            }
            if(!sender.hasPermission(command.getPermission())) {
                // Possibly send a message here saying no permission for this command
                return true;
            }
            args = Arrays.copyOfRange(args, 1, args.length);

            if ((command.getMinArgs() != -1 && command.getMinArgs() > args.length) || (command.getMaxArgs() != -1 && command.getMaxArgs() < args.length)) {
                // Possibly send a message here about an issue with the args provided
                return true;
            }
            if (command.allowConsole()) {
                command.execute(sender, args);
                return true;
            } else {
                command.execute((Player) sender, args);
                return true;
            }
        }
        // Possibly send a message here about command not being found
        return true;
    }

    private CommandBase getCommand(String name) {
        return commands.stream().filter(c -> c.getName() != null && c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<CommandBase> getCommands() { return commands; }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("so")) {
            if (args.length == 1) {
                List<String> commandNames = new ArrayList<>();
                if (!args[0].equals("")) {
                    for (String commandName : commands.stream().map(CommandBase::getName).collect(Collectors.toList())) {
                        if (!commandName.startsWith(args[0].toLowerCase())) continue;
                        commandNames.add(commandName);
                    }
                } else {
                    commandNames = commands.stream().map(CommandBase::getName).collect(Collectors.toList());
                }
                Collections.sort(commandNames);
                return commandNames;
            }
        }
        return null;
    }
}
