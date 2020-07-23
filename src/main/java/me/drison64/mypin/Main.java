package me.drison64.mypin;

import me.drison64.mypin.Configurations.Config;
import me.drison64.mypin.Configurations.Data;
import me.drison64.mypin.Inventories.AddInventory;
import me.drison64.mypin.Listener.InventoryListener;
import me.drison64.mypin.Listener.PlayerListener;
import me.drison64.mypin.Managers.ConfigManager;
import me.drison64.mypin.Managers.InventoryManager;
import me.drison64.mypin.Managers.PinManager;
import me.drison64.mypin.Managers.WaitingManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PluginManager pluginManager;
    private InventoryManager inventoryManager;
    private ConfigManager configManager;
    private WaitingManager waitingManager;
    private PinManager pinManager;

    @Override
    public void onEnable() {

        inventoryManager = new InventoryManager();
        //inventoryManager.registerInventory(new EditInventory(this));

        waitingManager = new WaitingManager();

        pinManager = new PinManager(this);

        pluginManager = Bukkit.getPluginManager();

        configManager = new ConfigManager(this);



        inventoryManager.registerInventory(new AddInventory(this));

        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(new PlayerListener(this), this);

        configManager.registerConfig(new Config(this));
        configManager.registerConfig(new Data(this));



        getCommand("cunt").setExecutor(new cmdcunt(this));
        getCommand("pin").setExecutor(new cmdpin(this));

    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public WaitingManager getWaitingManager() {
        return waitingManager;
    }

    public PinManager getPinManager() {
        return pinManager;
    }

}
