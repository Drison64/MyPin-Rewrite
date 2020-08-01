package me.drison64.mypin.Inventories;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

    public void open(Player player, String altTitle) {
        doOpen(player, altTitle);
    }

    public void open(Player player, String altTitle, PlayerInteractEvent e) {
        if (main.getPinUtils().isSet(e.getClickedBlock())) {
            this.block = e.getClickedBlock();
        } else {
            if (main.getDoorUtils().isDoor(e.getClickedBlock())) {
                if (main.getPinUtils().isSet(main.getDoorUtils().getOtherHalfBlock(e.getClickedBlock()))) {
                    this.block = main.getDoorUtils().getOtherHalfBlock(e.getClickedBlock());
                }
            } else {
                this.block = e.getClickedBlock();
            }
        }
        doOpen(player, altTitle);
    }

    private void doOpen(Player player, String altTitle) {
        Inventory inventory;
        if (altTitle == null) {
            inventory = Bukkit.createInventory(null, size, originalTitle);
            this.currentTitle = originalTitle;
        } else {
            inventory = Bukkit.createInventory(null, size, altTitle);
            this.currentTitle = altTitle;
        }

        init(inventory);

        player.openInventory(inventory);

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

}
