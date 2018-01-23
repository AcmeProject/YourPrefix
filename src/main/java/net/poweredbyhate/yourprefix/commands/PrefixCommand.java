package net.poweredbyhate.yourprefix.commands;

import net.poweredbyhate.yourprefix.YourPrefix;
import net.poweredbyhate.yourprefix.utilities.Input;
import net.poweredbyhate.yourprefix.utilities.MagicBukket;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Lax on 12/20/2017.
 */
public class PrefixCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || (!sender.hasPermission("myprefix.use") || !sender.hasPermission("yourprefix.use"))) {
            sender.sendMessage(ChatColor.RED + "Not enough permission!");
        }
        new Input((Player) sender).openDialogueGUI();
        return false;
    }
}
