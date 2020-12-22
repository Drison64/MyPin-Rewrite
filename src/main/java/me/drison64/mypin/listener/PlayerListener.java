/*
 * MIT License
 *
 * Copyright (c) 2020 Ondřej Vajďák
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.drison64.mypin.listener;

import me.drison64.mypin.inventories.AddInventory;
import me.drison64.mypin.inventories.EditInventory;
import me.drison64.mypin.inventories.EnterInventory;
import me.drison64.mypin.Main;
import me.drison64.mypin.managers.InventoryManager;
import me.drison64.mypin.managers.WaitingManager;
import me.drison64.mypin.objects.ClickType;
import me.drison64.mypin.objects.EditInventoryType;
import me.drison64.mypin.utils.DoorUtils;
import me.drison64.mypin.utils.PinUtils;
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

            switch (type) {
                case ADD: new AddInventory(main).open(player, null, event.getClickedBlock());
                case EDIT: new EditInventory(main, EditInventoryType.INFO, 1).open(player, null, event.getClickedBlock());
            }

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
