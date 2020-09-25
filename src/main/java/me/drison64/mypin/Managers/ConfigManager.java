/*
 * MIT License
 *
 * Copyright (c) 2020 Ondřej Vajďák
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.drison64.mypin.managers;

import me.drison64.mypin.Configurations.Configuration;
import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.ConfigType;

import java.util.ArrayList;
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

    public Configuration getConfig(ConfigType type) {
        return getConfig(type.getClazz());
    }

}
