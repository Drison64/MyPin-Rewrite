package me.drison64.mypin.Utils;

import me.drison64.mypin.Main;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryTitleUtils {

    private Main main;

    public InventoryTitleUtils(Main main) {
        this.main = main;
    }

    public static String addCode(InventoryClickEvent e, String originalTitle) {
        int slot = e.getRawSlot();
        int[] number_buttons = {12, 13, 14, 21, 22, 23, 30, 31, 32, 40};
        String[] actions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int indextracker = 0;
        for (int i : number_buttons) {
            indextracker++;
            if (slot == i) {
                if (e.getView().getTitle().equals(originalTitle)) {
                    return "Pin: " + actions[indextracker -1];
                } else {
                    return e.getView().getTitle() + actions[indextracker - 1];
                }
            }
        }
        return e.getView().getTitle();
    }

    public static String removeCode(InventoryClickEvent e, String originalTitle) {
        if (e.getInventory().getItem(39).getType().equals(Material.PLAYER_HEAD)) {
            if (e.getView().getTitle() == originalTitle) {
                return null;
            }
            if (e.getView().getTitle().length() == 6) {
                return null;
            } else {
                return e.getView().getTitle().substring(0, e.getView().getTitle().length() - 1);
            }
        }
        return e.getView().getTitle();
    }

}
