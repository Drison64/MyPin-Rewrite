package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.ActionType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Action {

    private Main main;
    private ActionType type;

    public Action(Main main, ActionType type) {
        this.main = main;
        this.type = type;
    }

    public abstract void run(List<String> input, Integer line, Block block, Player player);

    public ActionType getType() {
        return type;
    }

    protected void runNext(List<String> input, Integer line, Block block, Player player) {

        if (input.get(line + 1).isEmpty()) {
            return;
        }

        main.getActionsManager().get(type).run(input, line + 1 , block, player);

    }

}
