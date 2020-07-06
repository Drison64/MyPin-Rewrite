package me.drison64.mypin.Managers;

import me.drison64.mypin.Inventories.AbstractInventory;
import me.drison64.mypin.Objects.ClickType;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<AbstractInventory> inventories = new ArrayList<>();

    public void registerInventory(AbstractInventory inventory) {
        inventories.add(inventory);
    }

    public AbstractInventory getInventory(Class<? extends AbstractInventory> clazz) {
        for (AbstractInventory inventory : inventories) {
            if (inventory.getClass() == clazz) {
                return inventory;
            }
        }

        return null;
    }

    public AbstractInventory getInventory(ClickType type) {
        return getInventory(type.getClazz());
    }

    public void fire(Event e) {
        for (AbstractInventory inventory : inventories) {
            inventory.fire(e);
        }
    }

}
