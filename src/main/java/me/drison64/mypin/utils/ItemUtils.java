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

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ItemUtils {

    public static ItemStack mkitem(int count, Material material, String name, List<String> desc) {
        ItemStack item = new ItemStack(material, count);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (!(desc.toString().length() < 1)) {
            meta.setLore(desc);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack mkskull(int count, String url, String name, List<String> desc) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, count);

        if (url == null || url.isEmpty())
            return skull;
        ItemMeta skullMeta = skull.getItemMeta();
        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (!(desc.toString().length() < 1)) {
            skullMeta.setLore(desc);
        }
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        //TODO LEARN AND UNDERSTAND THIS PART

        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //TODO LEARN AND UNDERSTAND THIS PART

        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static ItemStack mkskullname(int count, Player player, String name, List<String> desc) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, count);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (!(desc.toString().length() < 1)) {
            meta.setLore(desc);
        }
        meta.setOwningPlayer(player);
        item.setItemMeta(meta);
        return item;
    }

}
