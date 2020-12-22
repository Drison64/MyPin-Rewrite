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

package me.drison64.mypin.Inventories;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractInventory {

    protected Main main;

    protected String originalTitle;

    protected String currentTitle;

    protected String code;

    protected Block block;

    protected Inventory inventory;

    public int getSize() {
        return size;
    }

    private int size;

    private List<UUID> players = new ArrayList<>();

    public AbstractInventory(Main main, String originalTitle, int size) {
        this.main = main;
        this.size = size;
        this.originalTitle = originalTitle;
        this.code = "";
    }

    public void open(Player player, String altTitle, Block block) {

        if (main.getPinUtils().isSet(block)) {
            this.block = block;
        } else {
            if (main.getDoorUtils().isDoor(block)) {
                if (main.getPinUtils().isSet(main.getDoorUtils().getOtherHalfBlock(block))) {
                    this.block = main.getDoorUtils().getOtherHalfBlock(block);
                } else {
                    this.block = block;
                }
            } else {
                this.block = block;
            }
        }

        if (altTitle == null) {
            inventory = Bukkit.createInventory(null, size, originalTitle);
            this.currentTitle = originalTitle;
        } else {
            inventory = Bukkit.createInventory(null, size, altTitle);
            this.currentTitle = altTitle;
        }

        init(inventory);

        player.openInventory(inventory);

        main.getInventoryManager().getInventoryHashMap().put(player, this);

        players.add(player.getUniqueId());

    }

    protected void fill(Inventory inventory, ItemStack item) {
        for (int i = 0; i < size; i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
                inventory.setItem(i, item);
            }
        }
    }

    public void fire(Event e) {
        if (e instanceof InventoryClickEvent) {
            InventoryClickEvent ice = (InventoryClickEvent) e;

            Player player = (Player) ice.getWhoClicked();

            if (players.contains(player.getUniqueId())) {
                click(ice);
            }
        }

        if (e instanceof InventoryCloseEvent) {
            InventoryCloseEvent ice = (InventoryCloseEvent) e;

            Player player = (Player) ice.getPlayer();

            if (players.contains(player.getUniqueId())) {
                players.remove(player.getUniqueId());

                close(player);
            }
        }

        if (e instanceof PlayerQuitEvent) {
            PlayerQuitEvent pqe = (PlayerQuitEvent) e;

            Player player = pqe.getPlayer();

            if (players.contains(player.getUniqueId())) {
                players.remove(player.getUniqueId());
            }
        }
    }

    public abstract void init(Inventory inventory);
    protected abstract void click(InventoryClickEvent event);
    protected abstract void close(Player player);

    public Inventory getInventory() {
        return inventory;
    }

}
