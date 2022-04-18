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

package me.drison64.mypin;

import me.drison64.mypin.managers.WaitingManager;
import me.drison64.mypin.objects.ClickType;
import me.drison64.mypin.objects.ErrorsEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPin implements CommandExecutor {

    private Main main;
    private WaitingManager waitingManager;

    public CmdPin(Main main) {
        this.main = main;
        this.waitingManager = main.getWaitingManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorsEnum.COMMAND_ONLY_PLAYER.getErrorString());
            return false;
        }
        Player player = (Player) sender;

        if (args[0].equals("add")) {
            waitingManager.addWaiting(player, ClickType.ADD);
        }

        if (args[0].equals("edit")) {
            waitingManager.addWaiting(player, ClickType.EDIT);
        }

        return false;
    }

}
