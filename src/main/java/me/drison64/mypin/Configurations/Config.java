package me.drison64.mypin.Configurations;

import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.ConfigManager;

public class Config extends Configuration {

    private Main main;
    private ConfigManager configManager;

    public Config(Main main) {
        super(main);
        this.main = main;
        this.configManager = main.getConfigManager();
        createConfig("config");
        configManager.registerConfig(this);
    }

}
