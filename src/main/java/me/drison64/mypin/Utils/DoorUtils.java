package me.drison64.mypin.Utils;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;

public class DoorUtils {

    private Main main;

    public DoorUtils(Main main) {
        this.main = main;
    }

    public Block getOtherHalfBlock(Block block) {

        Door door;

        try {
            door = (Door) block.getBlockData();
        } catch (ClassCastException ex) {
            return null;
        }

        if (door.getHalf().toString() == "TOP") {
            return Bukkit.getWorld(block.getWorld().getName()).getBlockAt(block.getLocation().add(0, -1, 0));
        } else if (door.getHalf().toString() == "BOTTOM") {
            return Bukkit.getWorld(block.getWorld().getName()).getBlockAt(block.getLocation().add(0, 1, 0));
        }

        return null;

    }

    public Boolean isDoor(Block block) {

        try {
            Door door = (Door) block.getBlockData();
        } catch (ClassCastException ex) {
            return false;
        }

        return true;

    }

}
