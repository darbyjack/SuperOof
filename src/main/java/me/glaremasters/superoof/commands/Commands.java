package me.glaremasters.superoof.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import me.glaremasters.superoof.SuperOof;
import org.bukkit.command.CommandSender;

import static co.aikar.commands.ACFBukkitUtil.color;

/**
 * Created by GlareMasters
 * Date: 10/12/2018
 * Time: 10:46 AM
 */
@CommandAlias("so|superoof|oof")
public class Commands extends BaseCommand {

    @Dependency private SuperOof oof;


    @Subcommand("reload")
    @CommandPermission("so.reload")
    public void onReload(CommandSender sender) {
        oof.reloadConfig();
        sender.sendMessage(color(oof.getConfig().getString("messages.reload")));
    }

    @HelpCommand
    @Syntax("")
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
