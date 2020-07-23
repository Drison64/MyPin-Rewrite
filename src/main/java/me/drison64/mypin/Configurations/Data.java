package me.drison64.mypin.Configurations;


import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.ConfigManager;

public class Data extends Configuration {

    private Main main;
    private ConfigManager configManager;

    public Data(Main main) {
        super(main);
        this.main = main;
        this.configManager = main.getConfigManager();
        createConfig("data");
    }

}
