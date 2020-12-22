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
import me.drison64.mypin.objects.ConfigType;
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
import java.util.UUID;

public class InfoInventory extends CustomInventory {

    private Main main;
    private Block block;

    public InfoInventory(Main main, Block block) {
        this.main = main;
        this.block = block;
    }

    @Override
    protected void init(HashMap<Integer, ItemStack> hashMap) {

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

    @Override
    protected void fire(Event event) {

        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent e = (InventoryClickEvent) event;

            e.setCancelled(true);

            if (e.getRawSlot() == 53) {

                main.getInventoryManager().open(new CommandsInventory(main, 1, block), (Player) e.getWhoClicked());

            }

        }
    }

}
