package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Objects.ActionType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class Action {

    private ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public abstract void run(String data, Integer line, Block block, Player player);

    public ActionType getType() {
        return type;
    }
}
