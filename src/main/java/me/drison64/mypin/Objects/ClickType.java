package me.drison64.mypin.Objects;

import me.drison64.mypin.Inventories.AbstractInventory;
import me.drison64.mypin.Inventories.AddInventory;
import me.drison64.mypin.Inventories.EnterInventory;

public enum ClickType {

    ADD(AddInventory.class),
    ENTER(EnterInventory.class);

    private Class<? extends AbstractInventory> clazz;

    private ClickType(Class<? extends AbstractInventory> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends AbstractInventory> getClazz() {
        return clazz;
    }

}
