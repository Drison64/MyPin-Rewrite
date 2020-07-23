package me.drison64.mypin;

import me.drison64.mypin.Managers.WaitingManager;
import me.drison64.mypin.Objects.ClickType;
import me.drison64.mypin.Objects.ErrorsEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdpin implements CommandExecutor {

    private Main main;
    private WaitingManager waitingManager;

    public cmdpin(Main main) {
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
        if (args[0].isEmpty()) {
            player.sendMessage("YOU FUCKING CUNT");
        }

        if (args[0].equals("add")) {
            player.sendMessage("hovno");
            waitingManager.addWaiting(player, ClickType.ADD);
        }

        return false;
    }

}
