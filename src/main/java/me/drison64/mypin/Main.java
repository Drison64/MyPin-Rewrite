/*
 * MIT License
 *
 * Copyright (c) 2020 Ondřej Vajďák
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.drison64.mypin;

import me.drison64.mypin.configurations.Config;
import me.drison64.mypin.configurations.Data;
import me.drison64.mypin.listener.InventoryListener;
import me.drison64.mypin.listener.PlayerListener;
import me.drison64.mypin.managers.*;
import me.drison64.mypin.objects.Action.*;
import me.drison64.mypin.utils.DefaultActionsUtils;
import me.drison64.mypin.utils.DoorUtils;
import me.drison64.mypin.utils.PinUtils;
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



        actionsManager.registerAction(new Action_Interact(this, ActionType.INTERACT));
        actionsManager.registerAction(new Action_Wait(this, ActionType.WAIT));
        actionsManager.registerAction(new Action_ConsoleCommand(this, ActionType.CONSOLE_COMMAND));
        actionsManager.registerAction(new Action_PlayerCommand(this, ActionType.PLAYER_COMMAND));

        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(new PlayerListener(this), this);

        configManager.registerConfig(new Config(this));
        configManager.registerConfig(new Data(this));



        getCommand("cunt").setExecutor(new CmdCunt(this));
        getCommand("pin").setExecutor(new CmdPin(this));

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
