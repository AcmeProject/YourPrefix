package net.poweredbyhate.yourprefix;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PlayerData {

    private YourPrefix plugin;
    private String player;
    private File userFolder;
    private File userFile;
    private FileConfiguration userData;


    public PlayerData(YourPrefix plugin, String player) {
        this.plugin = plugin;
        this.player = player;
        checkFile();
    }

    private void checkFile() {
        userFolder = new File(plugin.getDataFolder(), "Users");
        userFile = new File(userFolder, Bukkit.getOfflinePlayer(player).getUniqueId() + ".yml");
        userData = new YamlConfiguration();
        if (!userFolder.exists()) {
            userFolder.mkdirs();
        }
        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
                userData.load(userFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        try {
            userData.load(userFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getUserConfig() {
        return userData;
    }

    public List<String> getPrefixes() {
        return userData.getStringList("prefixes");
    }

    public List<String> getSuffixes() {
        return userData.getStringList("suffixes");
    }

    public void savePrefix(String prefix, int mode) { //ik there's a better way, i'll figure it out when Im not at 102F
        List prefixes = getPrefixes();
        if (mode == 1) {
            if (!prefixes.contains(prefix)) {
                prefixes.add(prefix);
                getUserConfig().set("prefixes", prefixes);
                saveData();
            }
            return;
        }
        if (mode == 0) {
            prefixes.remove(prefix);
            getUserConfig().set("prefixes", prefixes);
            saveData();
        }
    }

    public void saveSuffix(String suffix, int mode) {
        List suffixes = getSuffixes();
        if (mode == 1) {
            if (!suffixes.contains(suffix)) {
                suffixes.add(suffix);
                getUserConfig().set("suffixes", suffixes);
                saveData();
            }
            return;
        }
        if (mode == 0) {
            suffixes.remove(suffix);
            getUserConfig().set("prefixes", suffixes);
            saveData();
        }
    }

    public void saveData() {
        try {
            userData.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}