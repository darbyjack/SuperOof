package me.glaremasters.superoof;

import me.glaremasters.superoof.events.Chat;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperOof extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new Chat(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
