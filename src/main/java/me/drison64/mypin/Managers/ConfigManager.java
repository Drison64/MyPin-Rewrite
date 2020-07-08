package me.drison64.mypin.Managers;

import me.drison64.mypin.Configurations.Configuration;
import me.drison64.mypin.Main;

import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private Main main;

    private List<Configuration> registeredConfigurations;
    private HashMap<String, Configuration> configNames;

    public ConfigManager(Main main) {
        this.main = main;
    }

    public void registerConfig(Configuration configuration, String configName) {
        registeredConfigurations.add(configuration);
        configNames.put(configName, configuration);
    }

    /*public void getConfig(String configName) {
        for (Configuration configuration : configNames.keySet()) {
            if (configuration == configName) {

            }
        }

    }*/

}
