package me.glaremasters.superoof;

import me.glaremasters.superoof.commands.CommandHelp;
import me.glaremasters.superoof.commands.base.CommandHandler;
import me.glaremasters.superoof.events.Chat;
import me.glaremasters.superoof.updater.SpigotUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class SuperOof extends JavaPlugin {

    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new Chat(this), this);
        SpigotUpdater updater = new SpigotUpdater(this, 58916);
        updateCheck(updater);

        commandHandler = new CommandHandler();
        commandHandler.enable();

        getCommand("so").setExecutor(commandHandler);

        Stream.of(new CommandHelp(this)).forEach(commandHandler::register);

    }

    @Override
    public void onDisable() {
        getLogger().info("Moderately-large oof! The plugin seems to be oofing itself :(");
    }

    private void updateCheck(SpigotUpdater updater) {
        try {
            if (updater.checkForUpdates()) {
                getLogger().info("You appear to be running a version other than our latest stable release." + " You can download our newest version at: " + updater.getResourceURL());
            }
        } catch (Exception ex) {
            getLogger().info("Could not check for updates! Stacktrace:");
            ex.printStackTrace();
        }
    }

    /**
     * Get the command handler in the plugin
     * @return the handler being used
     */
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
