package me.drison64.mypin.Managers;

import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.Pin;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class PinManager {

    private Main main;
    private ConfigManager configManager;

    public PinManager(Main main) {
        this.main = main;
        this.configManager = main.getConfigManager();
    }

    public Pin getNew() {
        return new Pin(main);
    }

    public Pin getNew(Block block) {
        return new Pin(block, main);
    }

}
