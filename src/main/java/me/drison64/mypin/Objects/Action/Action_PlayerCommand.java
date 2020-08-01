package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Main;
import me.drison64.mypin.Utils.StringStitcherUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class Action_PlayerCommand extends Action {

    private Main main;
    private String[] splitted;
    private String command;
    private int delay = 1;

    public Action_PlayerCommand(Main main, ActionType type) {
        super(main, type);
        this.main = main;
    }

    @Override
    public void run(List<String> data, Integer line, Block block, Player player) {

        this.splitted = data.get(line - 1).split(" ");

        if (splitted.length < 2) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is empty.");
        }

        command = StringStitcherUtils.stitch(splitted, 1);

        player.performCommand(command);
        runNext(data, line, block, player);

    }

}
