package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;

import java.util.List;

public class Action_Interact extends Action {

    private Main main;
    private double delay = 1;

    public Action_Interact(Main main, ActionType type) {
        super(main, type);
        this.main = main;
    }

    @Override
    public void run(List<String> data, Integer line, Block block, Player player) {
        String[] splitted = data.get(line - 1).split(" ");

        if (!(splitted.length < 2)) {
            try {
                delay = Double.parseDouble(splitted[1]);
            } catch (NumberFormatException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is not a integer");
            }
        }

        if (InteractEnum.DOOR.getMaterialList().contains(block.getType())) {

            open(data, line, block, player);

        }

        if (InteractEnum.TRAPDOOR.getMaterialList().contains(block.getType())) {

            open(data, line, block, player);

        }

        if (InteractEnum.BUTTON.getMaterialList().contains(block.getType())) {

            //TODO Button

        }

        if (InteractEnum.CHEST.getMaterialList().contains(block.getType())) {

            Chest chest = (Chest) block.getBlockData();

            player.openInventory(chest.getBlockInventory());

            runNext(data, line, block, player);

        }

    }

    public void open(List<String> data, Integer line, Block block, Player player) {
        Openable door = (Door) block.getBlockData();
        door.setOpen(true);
        block.setBlockData(door);
        block.getState().update();

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                door.setOpen(false);
                block.setBlockData(door);
                block.getState().update();
                runNext(data, line, block, player);
            }
        }, (long) (delay * 20));
    }

}
