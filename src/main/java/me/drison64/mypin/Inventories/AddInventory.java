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

package me.drison64.mypin.Inventories;

import me.drison64.inventoryapi.CustomInventory;
import me.drison64.inventoryapi.ItemStackUtils;
import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.Pin;
import me.drison64.mypin.Utils.ArrayUtils;
import me.drison64.mypin.Utils.InventoryTitleUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

public class AddInventory extends CustomInventory {

    private Main main;
    private Block block;
    private String code;

    public AddInventory(Main main, Block block, String code, String title) {
        this.main = main;
        this.block = block;
        this.code = code;
        this.title = title;
    }

    public AddInventory(Main main, Block block, String code) {
        this.main = main;
        this.block = block;
        this.code = code;
        this.title = "Please set a pin";
    }

    @Override
    protected void init(HashMap<Integer, ItemStack> hashMap) {
        size = 54;
        originalTitle = "Please set a pin";
        set(12, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/caf1b280cab59f4469dab9f1a2af7927ed96a81df1e24d50a8e3984abfe4044", "1", Arrays.asList("")));
        set(13, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/e4b1e1d426123ce40cd6a54b0f876ad30c08539cf5a6ea63e847dc507950ff", "2", Arrays.asList("")));
        set(14, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/904ccf8b5332c196c9ea02b22b39b99facd1cc82bfe3f7d7aeedc3c3329039", "3", Arrays.asList("")));
        set(21, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/6b4fc18e975f4f222d885216e363adc9e6d456aa29080e48eb47144dda436f7", "4", Arrays.asList("")));
        set(22, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/1d8b22239712e0ad579a62ae4c115103e7728825e17508acd6cc89174ee838", "5", Arrays.asList("")));
        set(23, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/9eefbad16712a05f98e4f0de5b4486af3987b46ea6ab4e3be93d14a832c56e", "6", Arrays.asList("")));
        set(30, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/a3e69fa942df3d5ea53a3a97491617510924c6b8d7c4371197378a1cf2def27", "7", Arrays.asList("")));
        set(31, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/7d184fd4ab51d4622f49b54ce7a1395c29f02ad35ce5abd5d3c25638f3a82", "8", Arrays.asList("")));
        set(32, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/1b2454a5faa25f7c4f5771d52bb4f55deb1939f75efd8e0ac421812ba3dc7", "9", Arrays.asList("")));
        set(40, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/ffa45911b16298cfca4b2291eeda666113bc6f2a37dcb2ecd8c2754d24ef6", "0", Arrays.asList("")));

        if (title == originalTitle) {
            set(41, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")));
            set(39, ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")));
        } else {
            set(41, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/a92e31ffb59c90ab08fc9dc1fe26802035a3a47c42fee63423bcdb4262ecb9b6", "Confirm", Arrays.asList("")));
            set(39, ItemStackUtils.mkskull(1, "http://textures.minecraft.net/texture/edf5c2f893bd3f89ca40703ded3e42dd0fbdba6f6768c8789afdff1fa78bf6", "Back", Arrays.asList("")));
        }

        fill(ItemStackUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")), 54);
    }

    @Override
    protected void fire(Event event) {
        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent e = (InventoryClickEvent) event;
            int slot = e.getRawSlot();
            e.setCancelled(true);
            if (ArrayUtils.contains(new Object[]{12, 13, 14, 21, 22, 23, 30, 31, 32, 40}, slot)) {

                code = code + slot;

                String addcode = InventoryTitleUtils.addCode(e, originalTitle);

                main.getInventoryManager().open(new AddInventory(main, block, code, addcode), (Player) e.getWhoClicked());

            }
            if (slot == 39) {
                String removecode = InventoryTitleUtils.removeCode(e, originalTitle);

                code = code.substring(0, code.length() - 2);

                if (removecode == null) {
                    main.getInventoryManager().open(new AddInventory(main, block, code, originalTitle), (Player) e.getWhoClicked());
                } else {
                    main.getInventoryManager().open(new AddInventory(main, block, code, removecode), (Player) e.getWhoClicked());
                }
            } else if (slot == 41) {

                Pin pin = main.getPinManager().getNew();
                pin.setPin(code);
                pin.setWorld(block.getWorld());
                pin.setOwner(e.getWhoClicked().getUniqueId());
                pin.setCommands(main.getDefaultActionsUtils().getDefaultActions(block));
                pin.setDefaultpermissions("10000");
                pin.setDisabled(false);
                pin.toStorage(block);

                e.getWhoClicked().closeInventory();

            }
        }
    }



    /*private final Main main;
    private PinManager pinManager;
    private final String originalTitle = "Please set a pin";

    private final Object[] number_buttons = {12, 13, 14, 21, 22, 23, 30, 31, 32, 40};


    public AddInventory(Main main) {
        super(main, "Please set a pin", 54);
        this.main = main;
        this.pinManager = main.getPinManager();
    }

    @Override
    public void init(Inventory inventory) {
        inventory.setItem(12, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/caf1b280cab59f4469dab9f1a2af7927ed96a81df1e24d50a8e3984abfe4044", "1", Arrays.asList("")));
        inventory.setItem(13, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/e4b1e1d426123ce40cd6a54b0f876ad30c08539cf5a6ea63e847dc507950ff", "2", Arrays.asList("")));
        inventory.setItem(14, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/904ccf8b5332c196c9ea02b22b39b99facd1cc82bfe3f7d7aeedc3c3329039", "3", Arrays.asList("")));
        inventory.setItem(21, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/6b4fc18e975f4f222d885216e363adc9e6d456aa29080e48eb47144dda436f7", "4", Arrays.asList("")));
        inventory.setItem(22, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/1d8b22239712e0ad579a62ae4c115103e7728825e17508acd6cc89174ee838", "5", Arrays.asList("")));
        inventory.setItem(23, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/9eefbad16712a05f98e4f0de5b4486af3987b46ea6ab4e3be93d14a832c56e", "6", Arrays.asList("")));
        inventory.setItem(30, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/a3e69fa942df3d5ea53a3a97491617510924c6b8d7c4371197378a1cf2def27", "7", Arrays.asList("")));
        inventory.setItem(31, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/7d184fd4ab51d4622f49b54ce7a1395c29f02ad35ce5abd5d3c25638f3a82", "8", Arrays.asList("")));
        inventory.setItem(32, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/1b2454a5faa25f7c4f5771d52bb4f55deb1939f75efd8e0ac421812ba3dc7", "9", Arrays.asList("")));
        inventory.setItem(40, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/ffa45911b16298cfca4b2291eeda666113bc6f2a37dcb2ecd8c2754d24ef6", "0", Arrays.asList("")));

        if (currentTitle == originalTitle) {
            inventory.setItem(41, ItemUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")));
            inventory.setItem(39, ItemUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")));
        } else {
            inventory.setItem(41, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/a92e31ffb59c90ab08fc9dc1fe26802035a3a47c42fee63423bcdb4262ecb9b6", "Confirm", Arrays.asList("")));
            inventory.setItem(39, ItemUtils.mkskull(1, "http://textures.minecraft.net/texture/edf5c2f893bd3f89ca40703ded3e42dd0fbdba6f6768c8789afdff1fa78bf6", "Back", Arrays.asList("")));
        }

        fill(inventory, ItemUtils.mkitem(1, Material.GRAY_STAINED_GLASS_PANE, "", Arrays.asList("")));
    }

    @Override
    protected void click(InventoryClickEvent e) {
        int slot = e.getRawSlot();
        e.setCancelled(true);
        if (ArrayUtils.contains(number_buttons, slot)) {

            code = code + slot;

            open((Player) e.getWhoClicked(), InventoryTitleUtils.addCode(e, originalTitle), block);
        }
        if (slot == 39) {
            String removecode = InventoryTitleUtils.removeCode(e, originalTitle);

            code = code.substring(0, code.length() - 1);

            if (removecode == null) {
                open((Player) e.getWhoClicked(), originalTitle, block);
            } else {
                open((Player) e.getWhoClicked(), removecode, block);
            }
            open((Player) e.getWhoClicked(), InventoryTitleUtils.removeCode(e, originalTitle), block);
        } else if (slot == 41) {

            Pin pin = pinManager.getNew();
            pin.setPin(code);
            pin
                    .
                    setWorld
                    (
                    block
                            .
                                    getWorld());
            pin.setOwner(e.getWhoClicked().getUniqueId());
            pin.setCommands(main.getDefaultActionsUtils().getDefaultActions(block));
            pin.setDefaultpermissions("10000");
            pin.setDisabled(false);
            pin.toStorage(block);

            e.getWhoClicked().closeInventory();

        }
    }

    @Override
    protected void close(Player player) {

    }*/

}
