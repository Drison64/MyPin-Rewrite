package me.drison64.mypin.Managers;

import com.google.common.collect.Maps;
import me.drison64.mypin.Main;
import me.drison64.mypin.Objects.ClickType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WaitingManager {

    private HashMap<UUID, ClickType> waiting;

    public WaitingManager() {
        this.waiting = new HashMap<>();
    }

    public void addWaiting(Player player, ClickType type) {
        waiting.put(player.getUniqueId(), type);
    }

    public void removeWaiting(Player player) {
        waiting.remove(player.getUniqueId());
    }

    public ClickType getWaiting(Player player) {
        return waiting.get(player.getUniqueId());
    }

}
