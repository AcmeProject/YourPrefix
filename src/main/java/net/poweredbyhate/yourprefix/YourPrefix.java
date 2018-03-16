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
        getCommand("yourprefix").setExecutor(new PrefixCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        if (!setupChat()) {
            getLogger().log(Level.SEVERE, "Failed to hook into vault");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public void setPlayerPrefix(Player p, String prefix) {
        getChat().setPlayerPrefix(null, p, prefix);
        new PlayerData(this, p.getName()).savePrefix(prefix, 1);

    }

    public void setPlayerSuffix(Player p, String suffix) {
        getChat().setPlayerSuffix(null, p, suffix);
        new PlayerData(this, p.getName()).saveSuffix(suffix, 1);
    }

    public void unsetPlayerPrefix(Player p, String prefix) {
        new PlayerData(this, p.getName()).savePrefix(chat.getPlayerPrefix(null, p), 0);
        getChat().setPlayerPrefix(null, p, "");
    }

    public void unsetPlayerSuffix(Player p, String suffix) {
        new PlayerData(this, p.getName()).saveSuffix(chat.getPlayerSuffix(null, p), 0);
        getChat().setPlayerSuffix(null, p, "");
    }

    public Chat getChat() {
        return chat;
    }

    public static YourPrefix getMain() {
        return instance;
    }

}
