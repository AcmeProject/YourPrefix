package net.poweredbyhate.yourprefix.utilities;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by Lax on 12/20/2017.
 */
public class MagicBukket implements InventoryHolder {

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null, 54);
    }
}
