package me.drison64.mypin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class Debug implements CommandExecutor {

    private Main main;
    private boolean active;
    private boolean sendToConsole;
    private boolean sendToPlayer;
    private List<Player> playersList = new ArrayList<>();

    public Debug(Main main) {
        this.main = main;
        this.active = false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args[0].equals("console")) {
            if (args[1] == null || !(args[1].equals("true") || args[1].equals("false"))) {
                return false;
            }
            this.sendToConsole = Boolean.parseBoolean(args[1]);
        }
        return false;
    }

}
