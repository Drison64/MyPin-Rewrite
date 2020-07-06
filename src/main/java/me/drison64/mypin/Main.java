package me.drison64.mypin;

import me.drison64.mypin.Inventories.AddInventory;
import me.drison64.mypin.Listener.InventoryListener;
import me.drison64.mypin.Managers.ConfigManager;
import me.drison64.mypin.Managers.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PluginManager pluginManager;
    private InventoryManager inventoryManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {

        inventoryManager = new InventoryManager();
        inventoryManager.registerInventory(new AddInventory(this));
        //inventoryManager.registerInventory(new EditInventory(this));

        pluginManager = Bukkit.getPluginManager();
        //pluginManager.registerEvents(new PlayerListener(this), this);
        pluginManager.registerEvents(new InventoryListener(this), this);

        configManager = new ConfigManager(this);

        getCommand("cunt").setExecutor(new cmdcunt(this));
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}
