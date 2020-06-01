package me.glaremasters.superoof.events;

import me.glaremasters.superoof.SuperOof;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.List;

/**
 * Created by GlareMasters on 5/2/2018.
 */
public class Chat implements Listener {

    private final List<String> commands;

    public Chat(SuperOof oof) {
        this.commands = oof.getConfig().getStringList("commands");
    }

    private void strikeLightningToPlayer(Player player) {
        player.getWorld().strikeLightning(player.getLocation());
    }

    private void dispatchAndReplaceCommands(String cmd, Player player) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("\\{player}", player.getName()));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("superoof.oof")) return;
        if (!event.getMessage().toLowerCase().contains("oof")) return;
        event.getRecipients().forEach(this::strikeLightningToPlayer);
        commands.forEach(cmd -> dispatchAndReplaceCommands(cmd, event.getPlayer()));
    }

}
