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

package me.drison64.mypin.objects.Action;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.bukkit.entity.Player;

import java.util.List;

public class Action_Interact extends Action {

    private Main main;
    private double delay = 1;

    public Action_Interact(Main main, ActionType type) {
        super(main, type);
        this.main = main;
    }

    @Override
    public void run(List<String> data, Integer line, Block block, Player player) {
        String[] splitted = data.get(line - 1).split(" ");

        if (!(splitted.length < 2)) {
            try {
                delay = Double.parseDouble(splitted[1]);
            } catch (NumberFormatException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is not a integer");
            }
        }

        if (InteractEnum.DOOR.getMaterialList().contains(block.getType())) {

            open(data, line, block, player);

        }

        if (InteractEnum.TRAPDOOR.getMaterialList().contains(block.getType())) {

            open(data, line, block, player);

        }

        if (InteractEnum.BUTTON.getMaterialList().contains(block.getType())) {

            Powerable powerable = (Powerable) block.getBlockData();
            powerable.setPowered(true);
            block.setBlockData(powerable);
            block.getState().update();

            Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                @Override
                public void run() {
                    powerable.setPowered(false);
                    block.setBlockData(powerable);
                    block.getState().update();
                    runNext(data, line, block, player);
                }
            }, (long) (delay * 20));

        }

        if (InteractEnum.CHEST.getMaterialList().contains(block.getType())) {

            Chest chest = (Chest) block.getState();

            Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                @Override
                public void run() {
                    player.openInventory(chest.getBlockInventory());

                    runNext(data, line, block, player);
                }
            }, 1L);

        }

    }

    public void open(List<String> data, Integer line, Block block, Player player) {
        Openable door = (Openable) block.getBlockData();
        door.setOpen(true);
        block.setBlockData(door);
        block.getState().update();

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                door.setOpen(false);
                block.setBlockData(door);
                block.getState().update();
                runNext(data, line, block, player);
            }
        }, (long) (delay * 20));
    }

}
