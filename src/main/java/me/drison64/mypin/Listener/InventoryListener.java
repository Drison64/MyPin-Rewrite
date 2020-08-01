package me.drison64.mypin.Listener;

import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    private Main main;
    private InventoryManager inventoryManager;

    public InventoryListener(Main main) {
        this.main = main;
        inventoryManager = main.getInventoryManager();
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        inventoryManager.fire(e, (Player) e.getWhoClicked());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        inventoryManager.getInventoryHashMap().remove(e.getPlayer());

        inventoryManager.fire(e, (Player) e.getPlayer());
    }

}
