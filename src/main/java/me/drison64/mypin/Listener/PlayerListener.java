package me.drison64.mypin.Listener;

import me.drison64.mypin.Inventories.AddInventory;
import me.drison64.mypin.Inventories.EnterInventory;
import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.InventoryManager;
import me.drison64.mypin.Managers.WaitingManager;
import me.drison64.mypin.Objects.ClickType;
import me.drison64.mypin.Utils.DoorUtils;
import me.drison64.mypin.Utils.PinUtils;
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
    private PinUtils pinUtils;
    private DoorUtils doorUtils;

    public PlayerListener(Main main) {
        this.main = main;

        this.inventoryManager = main.getInventoryManager();
        this.waitingManager = main.getWaitingManager();
        this.pinUtils = main.getPinUtils();
        this.doorUtils = main.getDoorUtils();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Bukkit.getConsoleSender().sendMessage("pes1");

        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ClickType type = waitingManager.getWaiting(player);
            Bukkit.getConsoleSender().sendMessage("pes2");

            if (type == null) {

                if (pinUtils.isSet(event.getClickedBlock())) {

                    event.setCancelled(true);

                    new EnterInventory(main).open(player, null, event.getClickedBlock());

                    waitingManager.removeWaiting(player);

                } else {

                    if (doorUtils.isDoor(event.getClickedBlock())) {

                        if (pinUtils.isSet(doorUtils.getOtherHalfBlock(event.getClickedBlock()))) {

                            event.setCancelled(true);

                            new EnterInventory(main).open(player, null, event.getClickedBlock());

                            waitingManager.removeWaiting(player);

                        }

                    }

                }

                return;

            }

            event.setCancelled(true);

            new AddInventory(main).open(player, null, event.getClickedBlock());

            waitingManager.removeWaiting(player);
            Bukkit.getConsoleSender().sendMessage("pes3");
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        waitingManager.removeWaiting(player);

        inventoryManager.getInventoryHashMap().remove(player);

        inventoryManager.fire(e, e.getPlayer());
    }

}
