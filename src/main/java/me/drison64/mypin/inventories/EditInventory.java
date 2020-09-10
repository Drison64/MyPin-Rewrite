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

package me.drison64.mypin.inventories;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.ItemStackUtils;
import me.drison64.mypin.Main;
import me.drison64.mypin.objects.ConfigType;
import me.drison64.mypin.objects.EditInventoryType;
import me.drison64.mypin.objects.Pin;
import me.drison64.mypin.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EditInventory extends CustomInventory {

    private Main main;
    private EditInventoryType type;
    private Integer page;
    private Integer pages;
    private Block block;

    public EditInventory(Main main, EditInventoryType type, Integer page, Block block) {
        this.main = main;
        this.type = type;
        this.page = page;
        this.block = block;
    }

    @Override
    protected void init(HashMap<Integer, ItemStack> hashMap) {

        if (type == EditInventoryType.INFO) {
            initInfo(hashMap);
        } else if (type == EditInventoryType.COMMANDS) {
            initCommands(hashMap, page);
        } else if (type == EditInventoryType.COMMANDS_REMOVE) {
            initRemoveCommands(hashMap, page);
        } else if (type == EditInventoryType.COMMANDS_ADD) {
            initAddCommands(hashMap, block);
        } else if (type == EditInventoryType.PERMISSONS) {
            initPermissions(hashMap);
        }

    }

    public void initInfo(HashMap<Integer, ItemStack> hashMap) {

        title = "Pin edit";
        size = 54;

        Bukkit.getPlayer("Drison64").sendMessage("pejsek");
        int[] slots = {46,47,51,52};

        for (int i : slots) {
            set(i, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }

        String suuid = main.getConfigManager().getConfig(ConfigType.DATA).get().getString("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".owner");
        UUID uuid = UUID.fromString(suuid);
        Player player = Bukkit.getServer().getPlayer(uuid);

        boolean isset = main.getConfigManager().getConfig(ConfigType.DATA).get().isSet("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".disabled");
        if (isset) {
            boolean disabled = main.getConfigManager().getConfig(ConfigType.DATA).get().getBoolean("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".disabled");
            if (disabled) {
                set(45, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/3cc470ae2631efdfaf967b369413bc2451cd7a39465da7836a6c7a14e877", ChatColor.DARK_RED + "DISABLED", Arrays.asList("")));
            } else {
                set(45, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/3296d3e1493fa32d827a3635a683e5bded64914d75e73aacdccba46d8fd90", ChatColor.GREEN + "ENABLED", Arrays.asList("")));
            }
        } else {
            set(45, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/3296d3e1493fa32d827a3635a683e5bded64914d75e73aacdccba46d8fd90", ChatColor.GREEN + "ENABLED", Arrays.asList("")));
        }

        set(48, ItemStackUtils.mkitem(1, Material.RED_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        set(49, ItemStackUtils.mkitem(1, Material.BARRIER, ChatColor.DARK_RED + "DELETE THIS PIN", Arrays.asList("")));
        set(50, ItemStackUtils.mkitem(1, Material.RED_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        set(53, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/ea26e5ff186778eee6dbf98a15074384c3211d16be0f29460bbd964aeff", "Next", Arrays.asList("")));
        set(19, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/caf1b280cab59f4469dab9f1a2af7927ed96a81df1e24d50a8e3984abfe4044", "ID: " + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ(), Arrays.asList("")));
        set(22, ItemStackUtils.mkskullname(1, player, "Owner: " + player.getDisplayName(), Arrays.asList("")));
        set(25, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25", "Location:", Arrays.asList("", "X: " + block.getLocation().getBlockX(), "Y: " + block.getLocation().getBlockY(), "Z: " + block.getLocation().getBlockZ(), "World: " + block.getLocation().getWorld().toString())));
    }

    public void initCommands(HashMap<Integer, ItemStack> hashMap, Integer page) {
        int[] slots = {46,49,52};

        int actions = main.getConfigManager().getConfig(ConfigType.DATA).get().getStringList("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".commands").size();
        int pages = actions / 45;
        if (actions % 45 != 0) pages++;
        if (pages == 0) {
            pages++;
        }

        title = "Pin Edit - Commands (" + page + "/" + pages + "" +")";
        size = 54;

        this.pages = pages;

        for (int i : slots) {
            set(i, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }

        set(45, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/a89dd7af4c803b5287c433707c7c437cc28d521bb682c47a4d3d5d2a48afa6", "Previous", Arrays.asList("")));

        if (page < pages) {
            set(47, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/d1b62db5c0a3fa1ef441bf7044f511be58bedf9b6731853e50ce90cd44fb69", "Down", Arrays.asList("")));
        } else {
            set(47, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        if (page > 1) {
            set(51, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/14a5667ef7285c9225fc267d45117eab5478c786bd5af0a199c29a2c14c1f", "Up", Arrays.asList("")));
        } else {
            set(51, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        if (!(actions < 1)) {
            set(48, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/4e4b8b8d2362c864e062301487d94d3272a6b570afbf80c2c5b148c954579d46", "Remove", Arrays.asList("")));
        } else {
            set(48, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        set(50, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/b056bc1244fcff99344f12aba42ac23fee6ef6e3351d27d273c1572531f", "Add", Arrays.asList("")));
        set(53, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/ea26e5ff186778eee6dbf98a15074384c3211d16be0f29460bbd964aeff", "Next", Arrays.asList("")));

        List<String> actionslist = main.getConfigManager().getConfig(ConfigType.DATA).get().getStringList("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".commands");
        for (int i = 0; i < 45; i++) {
            if (i + (45 * (page - 1)) >= actionslist.size()) {
                break;
            }

            if (!(actionslist.get(i + (45 * (page - 1))) == null)) {
                int id = i + (45 * (page - 1));
                set(i, ItemStackUtils.mkitem(1, Material.COMMAND_BLOCK, actionslist.get(i + (45 * (page - 1))), Arrays.asList("ID: " + id)));
            } else {
                break;
            }
        }

    }

    public void initRemoveCommands(HashMap<Integer, ItemStack> hashMap, Integer page) {

        int actions = main.getConfigManager().getConfig(ConfigType.DATA).get().getStringList("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".commands").size();
        int pages = actions / 45;
        if (actions % 45 != 0) pages++;
        if (pages == 0) {
            pages++;
        }

        title = "Pin Edit - Commands (" + page + "/" + pages + "" +")";
        size = 54;

        this.pages = pages;

        for (int i = 46; i < 55; i++) {
            set(i, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }

        set(45, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/a89dd7af4c803b5287c433707c7c437cc28d521bb682c47a4d3d5d2a48afa6", "Back", Arrays.asList("")));

        if (page < pages) {
            set(47, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/d1b62db5c0a3fa1ef441bf7044f511be58bedf9b6731853e50ce90cd44fb69", "Down", Arrays.asList("")));
        } else {
            set(47, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }
        if (page > 1) {
            set(51, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/14a5667ef7285c9225fc267d45117eab5478c786bd5af0a199c29a2c14c1f", "Up", Arrays.asList("")));
        } else {
            set(51, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, " ", Arrays.asList("")));
        }

        List<String> actionslist = main.getConfigManager().getConfig(ConfigType.DATA).get().getStringList("data.blocks." + block.getLocation().getBlockX() + block.getLocation().getBlockY() + block.getLocation().getBlockZ() + ".commands");
        for (int i = 0; i < 45; i++) {
            if (i + (45 * (page - 1)) >= actionslist.size()) {
                break;
            }

            if (!(actionslist.get(i + (45 * (page - 1))) == null)) {
                int id = i + (45 * (page - 1));
                set(i, ItemStackUtils.mkitem(1, Material.COMMAND_BLOCK, actionslist.get(i + (45 * (page - 1))), Arrays.asList("ID: " + id)));
            } else {
                break;
            }
        }

    }

    public void initAddCommands(HashMap<Integer, ItemStack> hashMap, Block block) {



    }

    public void initPermissions(HashMap<Integer, ItemStack> hashMap) {



    }

    @Override
    protected void fire(Event event) {

        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent e = (InventoryClickEvent) event;

            e.setCancelled(true);

            if (EditInventoryType.INFO == type) {

                if (e.getRawSlot() == 53) {

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS, 1, block), (Player) e.getWhoClicked());

                }

            } else if (EditInventoryType.COMMANDS == type) {

                if (e.getRawSlot() == 45) {

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.INFO, 1, block), (Player) e.getWhoClicked());

                }

                if (e.getRawSlot() == 53) {

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.PERMISSONS, 1, block), (Player) e.getWhoClicked());

                }

                if (page < pages) {

                    if (e.getRawSlot() == 47) {
                        main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS, page + 1, block), (Player) e.getWhoClicked());
                    }

                }

                if (page > 1) {

                    if (e.getRawSlot() == 51) {
                        main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS, page - 1, block), (Player) e.getWhoClicked());
                    }

                }

                if (e.getRawSlot() == 48) {

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS_REMOVE, 1, block), (Player) e.getWhoClicked());

                }

            } else if (EditInventoryType.PERMISSONS == type) {

                if (e.getRawSlot() == 45) {

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS, 1, block), (Player) e.getWhoClicked());

                }

            } else if (EditInventoryType.COMMANDS_REMOVE == type) {

                if (!(ArrayUtils.contains(new Object[]{45,46,47,48,49,50,51,52,53}, e.getRawSlot()))) {

                    Pin pin = main.getPinManager().getNew(block);

                    pin.getCommands().remove(e.getRawSlot() + (45 * (page - 1)));

                    pin.commandsToStorage(block);

                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS_REMOVE, 1, block), (Player) e.getWhoClicked());

                }

            } else if (EditInventoryType.COMMANDS_ADD == type) {



            }
        }

    }

}
