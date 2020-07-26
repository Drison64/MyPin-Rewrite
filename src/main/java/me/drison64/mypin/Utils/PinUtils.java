package me.drison64.mypin.Utils;

import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.ConfigManager;
import me.drison64.mypin.Objects.ConfigType;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class PinUtils {

    private Main main;
    private ConfigManager configManager;

    public PinUtils(Main main) {
        this.main = main;
        this.configManager = main.getConfigManager();
    }

    public boolean isSet(Block block) {
        Location loc = block.getLocation();
        return configManager.getConfig(ConfigType.DATA).get().isSet("data.blocks." + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ());
    }

}
