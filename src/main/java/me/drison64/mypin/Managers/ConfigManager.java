package me.drison64.mypin.Managers;

import me.drison64.mypin.Configurations.Config;
import me.drison64.mypin.Configurations.Configuration;
import me.drison64.mypin.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private Main main;

    private List<Configuration> registeredConfigurations = new ArrayList<>();
    //private HashMap<String, Configuration> configNames;

    public ConfigManager(Main main) {
        this.main = main;
    }

    public void registerConfig(Configuration configuration) {
        registeredConfigurations.add(configuration);
        //configNames.put(configName, configuration);
    }

    public Configuration getConfig(Class<? extends Configuration> clazz) {
        for (int i = 0; i < registeredConfigurations.size(); i++) {
            if (registeredConfigurations.get(i).getClass() == clazz) {
                return registeredConfigurations.get(i);
            }
        }
        return null;
    }

}
