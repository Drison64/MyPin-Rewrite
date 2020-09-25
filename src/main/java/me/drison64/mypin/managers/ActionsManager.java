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

package me.drison64.mypin.managers;

import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.Action.Action;
import me.drison64.mypin.Objects.Action.ActionType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class ActionsManager {

    private Main main;

    private HashMap<ActionType, Action> registeredActions;

    public ActionsManager(Main main) {
        this.main = main;
        this.registeredActions = new HashMap<>();
    }

    public Action get(ActionType type) {
        return registeredActions.get(type);
    }

    public void registerAction(Action action) {
        registeredActions.put(action.getType(), action);
    }

    public void fire(List<String> data, Integer line, Block block, Player player) {
        for (Action action : registeredActions.values()) {

            if (data.get(line - 1).split(" ")[0].equals(action.getType().getFancyname())) {
                action.run(data, line, block, player);
            }

        }
    }

}
