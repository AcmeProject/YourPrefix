package net.poweredbyhate.yourprefix;

import net.milkbowl.vault.chat.Chat;
import net.poweredbyhate.yourprefix.commands.PrefixCommand;
import net.poweredbyhate.yourprefix.listeners.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class YourPrefix extends JavaPlugin {

    private static YourPrefix instance;
    private static Chat chat = null;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("prefix").setExecutor(new PrefixCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        if (!setupChat()) {
            getLogger().log(Level.INFO, "Failed to hook into vault");
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public void setPlayerPrefix(Player p, String prefix) {
        for (World w : Bukkit.getWorlds()) {
            getChat().setPlayerPrefix(w.getName(), p, prefix);
        }
    }

    public void setPlayerSuffix(Player p, String prefix) {
        for (World w : Bukkit.getWorlds()) {
            getChat().setPlayerSuffix(w.getName(), p, prefix);
        }
    }

    public Chat getChat() {
        return chat;
    }

    public static YourPrefix getMain() {
        return instance;
    }

}
