package talscore.talscore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import talsapi.talsapi.TALSAPI;
import talscore.talscore.TALSCore;

public class PlayerJoin implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        String classPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getPrefix();
        int levelPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getLevel();

        p.setDisplayName(plugin.manager.getPlayerName(p));
        p.setPlayerListName(plugin.manager.tabPlayerName(p));

        e.setJoinMessage(plugin.manager.getPlayerName(p) + "§fが§bログイン§fしました");

        p.sendMessage("§6§l-Welcome §e§lto §c§lThe §6§lAfter §a§lLife §b§lStory§6§l-");
        p.sendMessage("§3§l[§b§lNews§3§l]§a§l⇒§c§lAlpha §b§lOpen!!");
        p.sendMessage("§6§l[§e§lInfo§6§l]§a§l⇒§fAlphaTESTに参加された方のアイテムは");
        p.sendMessage("       §f次のTEST、または本公開と同時に初期状態に戻ります。ご了承下さい。");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent q) {
        Player p = q.getPlayer();
        String classPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getPrefix();
        int levelPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getLevel();

        p.setPlayerListName(plugin.manager.getPlayerName(p));

        q.setQuitMessage("§6≪" + classPrefix + "§a: §fLv.§b" + levelPrefix + "§6≫ §b" + q.getPlayer().getName() + "§fが§cログアウト§fしました");

    }

}