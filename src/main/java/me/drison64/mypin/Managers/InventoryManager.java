package me.drison64.mypin.Managers;

import me.drison64.mypin.Inventories.AbstractInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {

    private List<AbstractInventory> inventories = new ArrayList<>();
    private HashMap<Player, AbstractInventory> inventoryHashMap = new HashMap<>();

    public AbstractInventory createNew(AbstractInventory inventory, Player player) {

        inventoryHashMap.put(player, inventory);

        return inventory;

    }

    public void fire(Event e, Player player) {
        inventoryHashMap.get(player).fire(e);
    }

}
