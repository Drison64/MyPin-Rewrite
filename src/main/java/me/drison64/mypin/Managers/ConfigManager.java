package me.drison64.mypin.Managers;

import me.drison64.mypin.Main;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import java.util.HashMap;

public class ConfigManager {

    private Main main;

    private File file;
    private FileConfiguration config;
    private String fileName;
    private HashMap<String, FileConfiguration> configs;

    public ConfigManager(Main main) {
        this.main = main;
    }

    public void createConfig(String fileName) {

    }

    /*public FileConfiguration getConfig(String fileName) {

    }*/

    public void reloadConfig(String fileName) {

    }



}
