package talscore.talscore.listener;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import talsapi.talsapi.api.event.TALSLevelChangeEvent;
import talscore.talscore.TALSCore;

public class LevelChange implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    @EventHandler
    public void onLevelChange (TALSLevelChangeEvent e) {

        Player p = e.getPlayer();

        p.setPlayerListName(plugin.manager.getPlayerName(p));
        plugin.manager.putScoreBoard(p);

        plugin.manager.showLevel = e.getNewlevel();

        p.sendMessage("§6Lv.§e" + e.getNewlevel() + " §6になりました");
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        String title = "§6§lLevel Up!!";
        String subtitle = "§e§l" + ((e.getOldLevel()) + " §e➟§e§l " + (e.getNewlevel()));
        p.sendTitle(title,subtitle, 20, 45, 20);

        Location loc = p.getLocation();
        loc.getWorld().spawnParticle(
                Particle.TOTEM,
                loc,
                600,
                1, // 散開させるXの範囲
                1, // 散開させるYの範囲
                1 // 散開させるZの範囲
        );

        loc.getWorld().spawnParticle(
                Particle.SPELL_MOB_AMBIENT,
                loc,
                300,
                2, // 散開させるXの範囲
                2, // 散開させるYの範囲
                2 // 散開させるZの範囲
        );

        loc.getWorld().spawnParticle(
                Particle.CLOUD,
                loc,
                100,
                3, // 散開させるXの範囲
                3, // 散開させるYの範囲
                3 // 散開させるZの範囲
        );

    }

}
