package me.glaremasters.superoof;

import co.aikar.commands.BukkitCommandManager;
import me.glaremasters.superoof.commands.Commands;
import me.glaremasters.superoof.events.Chat;
import me.glaremasters.superoof.updater.SpigotUpdater;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperOof extends JavaPlugin {

    private BukkitCommandManager manager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.manager = new BukkitCommandManager(this);
        manager.enableUnstableAPI("help");
        manager.registerCommand(new Commands());
        getServer().getPluginManager().registerEvents(new Chat(this), this);
        SpigotUpdater updater = new SpigotUpdater(this, 58916);
        updateCheck(updater);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}