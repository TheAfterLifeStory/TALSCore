package talscore.talscore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import talsapi.talsapi.TALSAPI;
import talsapi.talsapi.api.event.TALSClassChangeEvent;
import talsapi.talsapi.api.event.TALSExpChangeEvent;
import talscore.talscore.TALSCore;

public class ClassChange implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    @EventHandler
    public void onClassChange(TALSClassChangeEvent e) {

        Player p = e.getPlayer();

        p.setDisplayName(plugin.manager.getPlayerName(p));
        p.setPlayerListName(plugin.manager.tabPlayerName(p));

        plugin.manager.putScoreBoard(p);

        plugin.manager.classPrefix = getClass().getName();
    }

}

