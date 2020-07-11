package me.drison64.mypin;

import me.drison64.mypin.Managers.InventoryManager;
import me.drison64.mypin.Objects.ClickType;
import me.drison64.mypin.Objects.ConfigType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdcunt implements CommandExecutor {

    Main main;

    InventoryManager inventoryManager;

    public cmdcunt(Main main) {
        this.main = main;

        inventoryManager = main.getInventoryManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //inventoryManager.getInventory(ClickType.ADD).open((Player) commandSender, null);

        main.getConfigManager().getConfig(ConfigType.CONFIG.getClazz()).get().set("pes", "kocka");
        main.getConfigManager().getConfig(ConfigType.CONFIG.getClazz()).save();

        return false;
    }

}
