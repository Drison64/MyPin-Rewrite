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
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class DefaultActionsUtils {

    private Main main;
    private List<Material> action_action;

    public DefaultActionsUtils(Main main) {
        this.main = main;
        this.action_action = Arrays.asList(
                Material.IRON_DOOR,
                Material.OAK_DOOR,
                Material.SPRUCE_DOOR,
                Material.BIRCH_DOOR,
                Material.JUNGLE_DOOR,
                Material.ACACIA_DOOR,
                Material.DARK_OAK_DOOR,

                Material.IRON_TRAPDOOR,
                Material.OAK_TRAPDOOR,
                Material.SPRUCE_TRAPDOOR,
                Material.BIRCH_TRAPDOOR,
                Material.JUNGLE_TRAPDOOR,
                Material.ACACIA_TRAPDOOR,
                Material.DARK_OAK_TRAPDOOR,

                Material.STONE_BUTTON,
                Material.OAK_BUTTON,
                Material.SPRUCE_BUTTON,
                Material.BIRCH_BUTTON,
                Material.JUNGLE_BUTTON,
                Material.ACACIA_BUTTON,
                Material.DARK_OAK_BUTTON,

                Material.CHEST,
                Material.TRAPPED_CHEST);
    }

    public List<String> getDefaultActions(Block block) {
        for (Material material : action_action) {
            if (block.getType() == material) {
                return Arrays.asList("interact");
            }
        }
        return Arrays.asList("");
    }

}
