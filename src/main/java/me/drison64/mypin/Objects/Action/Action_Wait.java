package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class Action_Wait extends Action {

    private Main main;
    private String[] splitted;
    private int delay = 1;

    public Action_Wait(Main main, ActionType type) {
        super(main, type);
        this.main = main;
    }

    @Override
    public void run(List<String> data, Integer line, Block block, Player player) {

        this.splitted = data.get(line - 1).split(" ");

        if (!(splitted[1].isEmpty())) {
            try {
                delay = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is not a integer");
            }
        }

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {

                runNext(data, line, block, player);

            }
        }, delay * 20);

    }

}
