package me.drison64.mypin.Inventories;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

    protected String title;

    public int getSize() {
        return size;
    }

    private int size;

    private List<UUID> players = new ArrayList<>();

    public AbstractInventory(Main main, String title, int size) {
        this.main = main;
        this.size = size;
        this.title = title;
    }

    public void open(Player player, String altTitle) {
        Inventory inventory;
        if (altTitle == null) {
            inventory = Bukkit.createInventory(null, size, title);
        } else {
            inventory = Bukkit.createInventory(null, size, altTitle);
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
