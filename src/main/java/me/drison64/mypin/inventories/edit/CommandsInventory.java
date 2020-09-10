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

package me.drison64.mypin.inventories.edit;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.ItemStackUtils;
import me.drison64.mypin.Main;
import me.drison64.mypin.inventories.EditInventory;
import me.drison64.mypin.objects.ConfigType;
import me.drison64.mypin.objects.EditInventoryType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandsInventory extends CustomInventory {

    private Main main;
    private Integer page;
    private Integer pages;
    private Block block;

    public CommandsInventory(Main main, Integer page, Block block) {
        this.main = main;
        this.page = page;
        this.block = block;
    }

    @Override
    protected void init(HashMap<Integer, ItemStack> hashMap) {

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

    @Override
    protected void fire(Event event) {

        if (event instanceof InventoryClickEvent) {

            InventoryClickEvent e = (InventoryClickEvent) event;

            if (e.getRawSlot() == 45) {

                main.getInventoryManager().open(new InfoInventory(main, block), (Player) e.getWhoClicked());

            }

            if (e.getRawSlot() == 53) {

                //TODO main.getInventoryManager().open(new EditInventory(main, EditInventoryType.PERMISSONS, 1, block), (Player) e.getWhoClicked());

            }

            if (page < pages) {

                if (e.getRawSlot() == 47) {
                    main.getInventoryManager().open(new CommandsInventory(main, page + 1, block), (Player) e.getWhoClicked());
                }

            }

            if (page > 1) {

                if (e.getRawSlot() == 51) {
                    main.getInventoryManager().open(new EditInventory(main, EditInventoryType.COMMANDS, page - 1, block), (Player) e.getWhoClicked());
                }

            }

            if (e.getRawSlot() == 48) {

                main.getInventoryManager().open(new RemoveCommandsInventory(main, 1, block), (Player) e.getWhoClicked());

            }

        }

    }

}
