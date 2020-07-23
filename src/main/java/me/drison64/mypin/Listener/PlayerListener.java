package me.drison64.mypin.Listener;

import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.InventoryManager;
import me.drison64.mypin.Managers.PinManager;
import me.drison64.mypin.Managers.WaitingManager;
import me.drison64.mypin.Objects.ClickType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Main main;

    private InventoryManager inventoryManager;
    private WaitingManager waitingManager;

    public PlayerListener(Main main) {
        this.main = main;

        inventoryManager = main.getInventoryManager();
        waitingManager = main.getWaitingManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Bukkit.getConsoleSender().sendMessage("pes1");

        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ClickType type = waitingManager.getWaiting(player);
            Bukkit.getConsoleSender().sendMessage("pes2");

            if (type == null) {
                return;
            }

            event.setCancelled(true);

            inventoryManager.getInventory(type).open(player, null);

            waitingManager.removeWaiting(player);
            Bukkit.getConsoleSender().sendMessage("pes3");
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        waitingManager.removeWaiting(player);

        inventoryManager.fire(e);
    }

}
