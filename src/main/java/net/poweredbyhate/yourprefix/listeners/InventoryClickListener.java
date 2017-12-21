package net.poweredbyhate.yourprefix.listeners;

import net.poweredbyhate.yourprefix.YourPrefix;
import net.poweredbyhate.yourprefix.utilities.Input;
import net.poweredbyhate.yourprefix.utilities.MagicBukket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Lax on 12/20/2017.
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent ev) {
        if (ev.getClickedInventory().getHolder() instanceof MagicBukket) {
            ev.setCancelled(true);
            if (!ev.getCurrentItem().hasItemMeta()) {
                return;
            }
            String name = ChatColor.stripColor(ev.getCurrentItem().getItemMeta().getDisplayName());
            Player p = (Player) ev.getWhoClicked();
            if (name.equalsIgnoreCase("Set Prefix!") && p.hasPermission("yourprefix.prefix.use")) {
                new Input((Player) ev.getWhoClicked()).askPrefix();
            }
            if (name.equalsIgnoreCase("Reset Prefix!") && p.hasPermission("yourprefix.prefix.use")) {
                YourPrefix.getMain().getChat().setPlayerPrefix((Player) ev.getWhoClicked(),"");
            }
        }
    }
}
