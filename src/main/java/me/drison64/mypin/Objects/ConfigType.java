package me.drison64.mypin.Objects;

import me.drison64.mypin.Configurations.Config;
import me.drison64.mypin.Configurations.Configuration;

public enum ConfigType {

    CONFIG(Config.class);

    private Class<? extends Configuration> clazz;

    ConfigType(Class<? extends Configuration> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Configuration> getClazz() {
        return clazz;
    }
}
