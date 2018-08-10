package me.glaremasters.superoof.events;

import me.glaremasters.superoof.SuperOof;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.List;

/**
 * Created by GlareMasters on 5/2/2018.
 */
public class Chat implements Listener {

    private SuperOof oof;
    private List<String> commands;

    public Chat(SuperOof oof) {
        this.oof = oof;
        this.commands = oof.getConfig().getStringList("commands");
    }


    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("superoof.off")) return;
        if (!event.getMessage().toLowerCase().contains("oof")) return;
        event.getRecipients().forEach(p -> p.getWorld().strikeLightningEffect(p.getLocation()));
        commands.forEach(cmd -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd.replaceAll("\\{player}", event.getPlayer().getName())));
    }

}
