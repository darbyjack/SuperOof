package me.glaremasters.superoof.commands;

import me.glaremasters.superoof.SuperOof;
import me.glaremasters.superoof.commands.base.CommandBase;
import org.bukkit.command.CommandSender;

import static me.glaremasters.superoof.utils.ColorUtil.color;

/**
 * Created by GlareMasters
 * Date: 8/29/2018
 * Time: 9:01 AM
 */
public class CommandHelp extends CommandBase {

    private SuperOof superOof;

    public CommandHelp(SuperOof superOof) {
        super("help", color(superOof.getConfig().getString("description.help")), "superoof.help", true, null, null, 0, 0);
        this.superOof = superOof;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        for (int i = 0; i < superOof.getCommandHandler().getCommands().size(); i++) {
            CommandBase command = superOof.getCommandHandler().getCommands().get(i);
            sender.sendMessage(color(superOof.getConfig().getString("messages.help-message").replace("{command}", command.getName()).replace("{arguments}", command.getArguments()).replace("{description}", command.getDescription())));
        }
    }

}
