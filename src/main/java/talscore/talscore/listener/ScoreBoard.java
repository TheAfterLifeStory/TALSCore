package talscore.talscore.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import talsapi.talsapi.api.event.TALSExpChangeEvent;
import talscore.talscore.TALSCore;

public class ScoreBoard implements Listener {
    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    public ScoreBoard() {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        plugin.manager.putScoreBoard(p);
    }

    @EventHandler
    public void expChange(TALSExpChangeEvent e) {

        Player p = e.getPlayer();
        plugin.manager.putScoreBoard(p);
    }
}
