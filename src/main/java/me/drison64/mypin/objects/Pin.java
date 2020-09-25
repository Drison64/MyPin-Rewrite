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

package me.drison64.mypin.objects;

import me.drison64.mypin.Configurations.Configuration;
import me.drison64.mypin.Main;
import me.drison64.mypin.Managers.ConfigManager;
import me.drison64.mypin.Utils.EncryptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;
import java.util.UUID;

public class Pin {

    private String pin;
    private World world;
    private UUID owner;
    private List<String> commands;
    private List<String> permissions;
    private String defaultpermissions;
    private boolean disabled;
    private Main main;
    private ConfigManager configManager;
    private Configuration data;

    @Override
    public String toString() {
        return "Pin{" +
                "pin='" + pin + '\'' +
                ", world=" + world +
                ", owner=" + owner +
                ", commands=" + commands +
                ", permissions=" + permissions +
                ", defaultpermissions='" + defaultpermissions + '\'' +
                ", disabled=" + disabled +
                '}';
    }

    public void toStorage(String id) {
        data.get().set("data.blocks." + id + ".pin", this.pin);
        data.get().set("data.blocks." + id + ".world", this.world.getName());
        data.get().set("data.blocks." + id + ".owner", this.owner.toString());
        data.get().set("data.blocks." + id + ".commands", this.commands);
        data.get().set("data.blocks." + id + ".defaultpermissions", this.defaultpermissions);
        data.get().set("data.blocks." + id + ".permissions", this.permissions);
        data.get().set("data.blocks." + id + ".disabled", this.disabled);
        data.save();
    }

    public void toStorage(Block block) {
        String id = String.valueOf(block.getX()) + String.valueOf(block.getY()) + String.valueOf(block.getZ());
        //Always pin, world, owner, disabled and defaultpermissions are set.
        Bukkit.getPlayer("Drison64").sendMessage("toStorage: " + this.pin);
        data.get().set("data.blocks." + id + ".pin", EncryptionUtils.toSHA256(this.pin, block.getLocation()));
        data.get().set("data.blocks." + id + ".world", this.world.getName());
        data.get().set("data.blocks." + id + ".owner", this.owner.toString());
        data.get().set("data.blocks." + id + ".commands", this.commands);
        data.get().set("data.blocks." + id + ".defaultpermissions", this.defaultpermissions);

        //TODO Permissions

        // data.get().set("data.blocks." + id + ".permissions", this.permissions);
        data.get().set("data.blocks." + id + ".disabled", this.disabled);
        data.save();
    }

    /*
      pin.setPin(hash);
      pin.setWorld(this.block.getLocation().getWorld());
      pin.setOwner(player.getUniqueId());
      pin.setDefaultpermissions("10000");
      pin.setDisabled(false);
    */

    public void setup(String pin, World world, UUID owner, Boolean disabled) {
        this.pin = pin;
        this.world = world;
        this.owner = owner;
        this.defaultpermissions = "10000";
        this.disabled = disabled;
    }

    public void delete(Block block) {
        Location loc = block.getLocation();
        data.get().set("data.blocks." + loc.getBlockX() + loc.getBlockY() + loc.getBlockZ(), null);
    }

    public void delete(int id) {
        data.get().set("data.blocks." + id, null);
    }

    public Pin(Main main) {
        this.main = main;
        this.configManager = main.getConfigManager();
        this.data = configManager.getConfig(ConfigType.DATA);
    }

    public Pin(Block blockInput, Main main) {
        this.main = main;
        this.configManager = main.getConfigManager();
        this.data = configManager.getConfig(ConfigType.DATA);
        Block block = null;

        if (main.getPinUtils().isSet(blockInput)) {
            block = blockInput;
        } else {
            if (main.getDoorUtils().isDoor(blockInput)) {
                if (main.getPinUtils().isSet(main.getDoorUtils().getOtherHalfBlock(blockInput))) {
                    block = main.getDoorUtils().getOtherHalfBlock(blockInput);
                }
            } else {
                block = blockInput;
            }
        }

        if (block == null) {
            return;
        }

        String id = String.valueOf(block.getX()) + String.valueOf(block.getY()) + String.valueOf(block.getZ());
        if (!(data.get().isSet("data.blocks." + id))) {
            return;
        }
        if (data.get().isSet("data.blocks." + id + ".world")) {
            if (Bukkit.getWorld(data.get().getString("data.blocks." + id + ".world")) != null) {
                this.world = Bukkit.getWorld(data.get().getString("data.blocks." + id + ".world"));
            }
        }
        if (data.get().isSet("data.blocks." + id + ".pin")) {
            this.pin = data.get().getString("data.blocks." + id + ".pin");
        }
        if (data.get().isSet("data.blocks." + id + ".owner")) {
            this.owner = UUID.fromString(data.get().getString("data.blocks." + id + ".owner"));
        }
        if (data.get().isSet("data.blocks." + id + ".commands")) {
            this.commands = data.get().getStringList("data.blocks." + id + ".commands");
        }
        if (data.get().isSet("data.blocks." + id + ".defaultpermissions")) {
            this.defaultpermissions = data.get().getString("data.blocks." + id + ".defaultpermissions");
        }
        if (data.get().isSet("data.blocks." + id + ".permissions")) {
            this.permissions = data.get().getStringList("data.blocks." + id + ".permissions");
        }
        if (data.get().isSet("data.blocks." + id + ".disabled")) {
            this.disabled = data.get().getBoolean("data.blocks." + id + ".disabled");
        }
    }

    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public UUID getOwner() {
        return owner;
    }
    public void setOwner(UUID owner) {
        this.owner = owner;
    }
    public List<String> getCommands() {
        return commands;
    }
    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    public String getDefaultpermissions() {
        return defaultpermissions;
    }
    public void setDefaultpermissions(String defaultpermissions) {
        this.defaultpermissions = defaultpermissions;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
