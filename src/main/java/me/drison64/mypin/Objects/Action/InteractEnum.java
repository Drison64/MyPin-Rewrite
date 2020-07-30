package me.drison64.mypin.Objects.Action;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum InteractEnum {

    DOOR(Arrays.asList(Material.IRON_DOOR, Material.OAK_DOOR, Material.SPRUCE_DOOR, Material.BIRCH_DOOR, Material.JUNGLE_DOOR, Material.ACACIA_DOOR, Material.DARK_OAK_DOOR)),
    TRAPDOOR(Arrays.asList(Material.IRON_TRAPDOOR, Material.OAK_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.JUNGLE_TRAPDOOR, Material.ACACIA_TRAPDOOR, Material.DARK_OAK_TRAPDOOR)),
    BUTTON(Arrays.asList(Material.STONE_BUTTON, Material.OAK_BUTTON, Material.SPRUCE_BUTTON, Material.BIRCH_BUTTON, Material.JUNGLE_BUTTON, Material.ACACIA_BUTTON, Material.DARK_OAK_BUTTON)),
    CHEST(Arrays.asList(Material.CHEST, Material.TRAPPED_CHEST));

    private List<Material> materialList;

    InteractEnum(List<Material> materialList) {
        this.materialList = materialList;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }
}
