package talscore.talscore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import talsapi.talsapi.api.event.TALSClassChangeEvent;
import talsapi.talsapi.api.event.TALSExpChangeEvent;
import talscore.talscore.TALSCore;

public class ClassChange implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    @EventHandler
    public void onClassChange(TALSExpChangeEvent e) {

        Player p = e.getPlayer();

        p.setPlayerListName(plugin.manager.getPlayerName(p));

        plugin.manager.putScoreBoard(p);

        plugin.manager.classPrefix = getClass().getName();
    }

}

