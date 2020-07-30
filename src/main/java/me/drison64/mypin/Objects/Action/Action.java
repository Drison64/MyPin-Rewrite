package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Objects.ActionType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class Action {

    private String data;
    protected Player player;
    protected Block block;
    private ActionType type;

    public Action(String data, ActionType type, Player player, Block block) {
        this.data = data;
        this.type = type;
        this.player = player;
        this.block = block;
        run();
    }

    public abstract void run();

}
