package me.drison64.mypin.Configurations;

import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.ConfigManager;

public class Config extends Configuration {

    public Config(Main main) {
        super(main);
        createConfig("config");
    }

}
