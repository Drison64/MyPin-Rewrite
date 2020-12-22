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

public enum ActionType {

    INTERACT("interact", Action_Interact.class),
    WAIT("wait", Action_Wait.class),
    CONSOLE_COMMAND("console_command", Action_ConsoleCommand.class),
    PLAYER_COMMAND("player_command", Action_PlayerCommand.class);

    private String fancyname;
    private Class<? extends Action> clazz;

    public String getFancyname() {
        return fancyname;
    }

    public Class<? extends Action> getClazz() {
        return clazz;
    }

    ActionType(String fancyname, Class<? extends Action> clazz) {
        this.fancyname = fancyname;
        this.clazz = clazz;
    }
}
