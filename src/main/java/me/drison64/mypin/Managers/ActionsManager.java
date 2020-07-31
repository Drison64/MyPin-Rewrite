package me.drison64.mypin.Managers;

import me.drison64.mypin.Inventories.AbstractInventory;
import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.Action.Action;
import me.drison64.mypin.Objects.ActionType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActionsManager {

    private Main main;

    private HashMap<ActionType, Action> registeredActions;

    public ActionsManager(Main main) {
        this.main = main;
        this.registeredActions = new HashMap<>();
    }

    public void registerAction(Action action) {
        registeredActions.put(action.getType(), action);
    }

    public void fire(String data, Integer line, Block block, Player player) {
        for (Action action : registeredActions.values()) {
            action.run(data, line, block, player);
        }
    }

    //TODO Actionsmanager (maybe this is not needed at all lul)

}
