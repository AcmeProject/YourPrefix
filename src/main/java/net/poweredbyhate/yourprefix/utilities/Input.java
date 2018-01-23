package net.poweredbyhate.yourprefix.utilities;

import net.poweredbyhate.yourprefix.YourPrefix;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lax on 12/20/2017.
 */
public class Input {

    private Player p;

    public Input(Player p) {
        this.p = p;
    }

    public void openDialogueGUI() {
        Inventory gui = Bukkit.createInventory(new MagicBukket(), 9*3, "Prefix Setting");
        gui.setItem(10, new Itemizer().createItem(Material.BOOK_AND_QUILL,1,0,"&aSet Prefix!","&7Click to set your prefix"));
        gui.setItem(11, new Itemizer().createItem(Material.BOOK,1,0,"&aReset Prefix!", "&7Click to reset your prefix!"));
        gui.setItem(15, new Itemizer().createItem(Material.BOOK_AND_QUILL,1,0,"&aSet Suffix!","&7Click to set your suffix"));
        gui.setItem(16, new Itemizer().createItem(Material.BOOK,1,0,"&aReset Suffix!", "&7Click to reset your suffix!"));
        gui.setItem(13, new Itemizer().createItem(Material.SKULL_ITEM, 1, 3, this.p.getDisplayName(),"&aPrefix : &r%PREFIX%~&aSuffix : &r%SUFFIX%".
                replace("%PREFIX%", YourPrefix.getMain().getChat().getPlayerPrefix(this.p).replace("%SUFFIX%", YourPrefix.getMain().getChat().getPlayerSuffix(this.p)))));
        for (int i = 0; i < gui.getSize(); i++) {
            if (gui.getItem(i) == null) {
                gui.setItem(i, new Itemizer().createItem(Material.STAINED_GLASS_PANE, 1, 15, " ",""));
            }
        }
        this.p.openInventory(gui);
    }

    public void askPrefix() {
        new AnvilGUI(YourPrefix.getMain(), this.p, "Enter Prefix Here", (player, reply) -> {
            if (reply == null || reply.equals("")) {
                this.p.closeInventory();
                return "";
            }
            this.p.closeInventory();
            if (parseInput(reply)) {
                YourPrefix.getMain().setPlayerPrefix(this.p, reply);
                this.p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bYour new prefix is: &r" + reply));
            }
            return "WRONG!";
        });
    }

    public void askSuffix() {
        new AnvilGUI(YourPrefix.getMain(), this.p, "Enter Suffix Here", (player, reply) -> {
            if (reply == null || reply.equals("")) {
                this.p.closeInventory();
                return "";
            }
            this.p.closeInventory();
            if (parseInput(reply)) {
                YourPrefix.getMain().setPlayerSuffix(this.p, reply);
                this.p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&bYour new suffix is: &r" + reply));
            }
            return "Incorrect.";
        });
    }

    public boolean parseInput(String input) {
        for (String s : YourPrefix.getMain().getConfig().getStringList("Censor.Words")) {
            Matcher m = Pattern.compile(s).matcher(input.toLowerCase());
            if (m.find()) {
                this.p.sendMessage(ChatColor.translateAlternateColorCodes('&', YourPrefix.getMain().getConfig().getString("Messages.ContainsCensored")));
                return false;
            }
        }
        for (String s : YourPrefix.getMain().getConfig().getStringList("Censor.Colors")) {
            if (input.contains("&"+s)) {
                this.p.sendMessage(ChatColor.translateAlternateColorCodes('&', YourPrefix.getMain().getConfig().getString("Messages.HasColor").replace("%COLOR%", s)));
                return false;
            }
        }
        if (input.length() > YourPrefix.getMain().getConfig().getInt("MaxLength")) {
            this.p.sendMessage(ChatColor.translateAlternateColorCodes('&', YourPrefix.getMain().getConfig().getString("Messages.TooLong")));
            return false;
        }
        if (input.length() < YourPrefix.getMain().getConfig().getInt("MinLength")) {
            this.p.sendMessage(ChatColor.translateAlternateColorCodes('&', YourPrefix.getMain().getConfig().getString("Messages.TooShort")));
            return false;
        }
        return true;
    }

}
