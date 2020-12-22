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

package me.drison64.mypin.utils;

import me.drison64.mypin.Main;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;

public class DoorUtils {

    public static Block getOtherHalfBlock(Block block) {

        Door door;

        try {
            door = (Door) block.getBlockData();
        } catch (ClassCastException ex) {
            return null;
        }

        if (door.getHalf().toString() == "TOP") {
            return Bukkit.getWorld(block.getWorld().getName()).getBlockAt(block.getLocation().add(0, -1, 0));
        } else if (door.getHalf().toString() == "BOTTOM") {
            return Bukkit.getWorld(block.getWorld().getName()).getBlockAt(block.getLocation().add(0, 1, 0));
        }

        return null;

    }

    public static Boolean isDoor(Block block) {

        try {
            Door door = (Door) block.getBlockData();
        } catch (ClassCastException ex) {
            return false;
        }

        return true;

    }

}
