package me.drison64.mypin.Objects.Action;

import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.ActionType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Player;

public class Action_Interact extends Action {

    private Main main;
    private String[] splitted;
    private int delay = 1;
    private int line;

    public Action_Interact(Main main, String data, ActionType type, Integer line, Block block, Player player) {
        super(data, type, player, block);
        this.main = main;
        this.splitted = data.split(" ");
        this.line = line;
    }

    @Override
    public void run() {
        if (!(splitted[1].isEmpty())) {
            try {
                delay = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is not a integer");
            }
        }

        if (InteractEnum.DOOR.getMaterialList().contains(block.getType())) {

            Door door = (Door) block.getBlockData();

            door.setOpen(true);

            Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                @Override
                public void run() {
                    door.setOpen(false);
                }
            }, delay * 20);

        }

        if (InteractEnum.TRAPDOOR.getMaterialList().contains(block.getType())) {

            TrapDoor trapDoor = (TrapDoor) block.getBlockData();

            trapDoor.setOpen(true);

            Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                @Override
                public void run() {
                    trapDoor.setOpen(false);
                }
            }, delay * 20);

        }

        if (InteractEnum.BUTTON.getMaterialList().contains(block.getType())) {

            //TODO Button

        }

        if (InteractEnum.CHEST.getMaterialList().contains(block.getType())) {

            Chest chest = (Chest) block.getBlockData();

            player.openInventory(chest.getBlockInventory());

        }

    }

}
