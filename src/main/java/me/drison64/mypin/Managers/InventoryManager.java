package me.drison64.mypin.Managers;

import me.drison64.mypin.Inventories.AbstractInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashMap;

public class InventoryManager {

    private HashMap<Player, AbstractInventory> inventoryHashMap = new HashMap<>();

    public AbstractInventory createNew(AbstractInventory inventory, Player player) {

        inventoryHashMap.put(player, inventory);

        return inventory;

    }

    public void fire(Event e, Player player) {
        inventoryHashMap.get(player).fire(e);
    }

    public HashMap<Player, AbstractInventory> getInventoryHashMap() {
        return inventoryHashMap;
    }

}
