package me.drison64.mypin;

import me.drison64.mypin.Configurations.Config;
import me.drison64.mypin.Configurations.Data;
import me.drison64.mypin.Inventories.AddInventory;
import me.drison64.mypin.Inventories.EnterInventory;
import me.drison64.mypin.Listener.InventoryListener;
import me.drison64.mypin.Listener.PlayerListener;
import me.drison64.mypin.Managers.*;
import me.drison64.mypin.Objects.Action.Action_ConsoleCommand;
import me.drison64.mypin.Objects.Action.Action_Interact;
import me.drison64.mypin.Objects.Action.Action_PlayerCommand;
import me.drison64.mypin.Objects.Action.Action_Wait;
import me.drison64.mypin.Objects.ActionType;
import me.drison64.mypin.Utils.DefaultActionsUtils;
import me.drison64.mypin.Utils.DoorUtils;
import me.drison64.mypin.Utils.PinUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private PluginManager pluginManager;
    private InventoryManager inventoryManager;
    private ConfigManager configManager;
    private WaitingManager waitingManager;
    private PinManager pinManager;
    private ActionsManager actionsManager;
    private PinUtils pinUtils;
    private DefaultActionsUtils defaultActionsUtils;
    private DoorUtils doorUtils;

    @Override
    public void onEnable() {

        actionsManager = new ActionsManager(this);

        defaultActionsUtils = new DefaultActionsUtils(this);

        doorUtils = new DoorUtils(this);

        inventoryManager = new InventoryManager();
        //inventoryManager.registerInventory(new EditInventory(this));

        waitingManager = new WaitingManager();

        pinManager = new PinManager(this);

        pluginManager = Bukkit.getPluginManager();

        configManager = new ConfigManager(this);

        pinUtils = new PinUtils(this);



        actionsManager.registerAction(new Action_Interact(this, ActionType.ACTION));
        actionsManager.registerAction(new Action_Wait(this, ActionType.WAIT));
        actionsManager.registerAction(new Action_ConsoleCommand(this, ActionType.CONSOLE_COMMAND));
        actionsManager.registerAction(new Action_PlayerCommand(this, ActionType.PLAYER_COMMAND));

        inventoryManager.registerInventory(new AddInventory(this));
        inventoryManager.registerInventory(new EnterInventory(this));

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

    public PinUtils getPinUtils() {
        return pinUtils;
    }

    public DefaultActionsUtils getDefaultActionsUtils() {
        return defaultActionsUtils;
    }

    public DoorUtils getDoorUtils() {
        return doorUtils;
    }

    public ActionsManager getActionsManager() {
        return actionsManager;
    }
}
