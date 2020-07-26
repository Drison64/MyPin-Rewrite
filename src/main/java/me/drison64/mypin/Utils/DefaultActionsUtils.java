package me.drison64.mypin.Utils;

import me.drison64.mypin.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class DefaultActionsUtils {

    private Main main;
    private List<Material> action_action;

    public DefaultActionsUtils(Main main) {
        this.main = main;
        this.action_action = Arrays.asList(
                Material.IRON_DOOR,
                Material.OAK_DOOR,
                Material.SPRUCE_DOOR,
                Material.BIRCH_DOOR,
                Material.JUNGLE_DOOR,
                Material.ACACIA_DOOR,
                Material.DARK_OAK_DOOR,

                Material.STONE_BUTTON,
                Material.OAK_BUTTON,
                Material.SPRUCE_BUTTON,
                Material.BIRCH_BUTTON,
                Material.JUNGLE_BUTTON,
                Material.ACACIA_BUTTON,
                Material.DARK_OAK_BUTTON,

                Material.CHEST,
                Material.TRAPPED_CHEST);
    }

    public List<String> getDefaultActions(Block block) {
        for (Material material : action_action) {
            if (block.getType() == material) {
                return Arrays.asList("action");
            }
        }
        return Arrays.asList("");
    }

}
