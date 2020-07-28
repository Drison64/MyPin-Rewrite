package me.drison64.mypin.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Action_Interact extends Action {
    
    private String[] splitted;
    private int delay = 1;
    private int line;

    public Action_Interact(String data, ActionType type, Integer line) {
        super(data, type);
        this.splitted = data.split(" ");
        this.line = line;
    }

    @Override
    protected void run() {
        if (!(splitted[1].isEmpty())) {
            try {
                delay = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error occured at line: " + (line + 1) + ", value is not a integer");
            }
        }



    }

}
