package me.drison64.mypin.Managers;

import me.drison64.mypin.Inventories.AbstractInventory;
import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.Action.Action;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

public class ActionsManager {

    private Main main;

    private List<Action> registeredActions;

    public ActionsManager(Main main) {
        this.main = main;
        this.registeredActions = new ArrayList<>();
    }

    public void registerAction(Action action) {
        registeredActions.add(action);
    }

    public void fire() {
        for (Action action : registeredActions) {
            action.run();
        }
    }

    //TODO Actionsmanager (maybe this is not needed at all lul)

}
