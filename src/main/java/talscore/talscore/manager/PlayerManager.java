package talscore.talscore.manager;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import talsapi.talsapi.TALSAPI;
import talscore.talscore.TALSCore;

import java.util.List;

public class PlayerManager {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    public String classPrefix;
    public long maxHealth;
    public long health;
    public int showLevel;
    public int getExp;
    public int getNextExp;
    public int expLeft;

    public String getPlayerName(Player p) {

        String str = "§6§l≪"+ TALSAPI.getPlayerDeta(p).getMainClass().getPrefix()+"§a: §fLv.§b"+TALSAPI.getPlayerDeta(p).getMainClass().getLevel()+"§6§l≫ §b"+p.getName()+"§r";

        return str;
    }

    public String tabPlayerName(Player p) {

        String str = "§6§l ≪"+ TALSAPI.getPlayerDeta(p).getMainClass().getPrefix()+"§a: §fLv.§b"+TALSAPI.getPlayerDeta(p).getMainClass().getLevel()+"§6§l≫ §b"+p.getName()+"§r";

        return str;
    }

    public void sendActionBar() {

        new BukkitRunnable() {

            public void run() {

                TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

                for (Player player : plugin.getServer().getOnlinePlayers()) {

                    classPrefix = TALSAPI.getPlayerDeta(player).getMainClass().getPrefix();
                    maxHealth = (int) player.getMaxHealth();
                    health = (int) player.getHealth();
                    showLevel = TALSAPI.getPlayerDeta(player).getMainClass().getLevel();
                    getExp = (int) TALSAPI.getPlayerDeta(player).getMainClass().getExp();
                    getNextExp = (int) TALSAPI.getPlayerDeta(player).getMainClass().getNextExp();
                    expLeft = getNextExp - getExp;

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§6§l≪" + classPrefix + "§a: §f§lLv.§b§l" +  showLevel + "§6§l≫" + "§e-§6§l≪§b§lNext§f§l.§a§l" + expLeft + "§2§lExp§6§l≫§e-§6§l≪§d§l" + health + "§5§l/§c§l" + maxHealth + "§4§lHP§6§l≫"));

                }
            }
        }.runTaskTimer(plugin,0,40);
    }

        public void putScoreBoard(Player p) {

        String classPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getPrefix();
        int levelPrefix = TALSAPI.getPlayerDeta(p).getMainClass().getLevel();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§6§l-§c§lT§6§lA§a§lL§b§lS§6§l-");

        Score score = objective.getScore("§6◆§e§l━━━━━━━━━━━━§6◆");
        score.setScore(14);

        Score score1 = objective.getScore(" §6§l≪" + classPrefix + "§6§l≫");
        score1.setScore(13);

        Score score2 = objective.getScore(" §f§lLv.§b§l" + levelPrefix);
        score2.setScore(12);

        Score score3 = objective.getScore(" §7[ §f" + (int) TALSAPI.getPlayerDeta(p).getMainClass().getExp() + "§7 / §f" + (int) TALSAPI.getPlayerDeta(p).getMainClass().getNextExp() + "§7 ]");
        score3.setScore(11);

        Score score4 = objective.getScore(" ");
        score4.setScore(10);

        Score score5 = objective.getScore(" §e§l" + (int) TALSAPI.getPlayerDeta(p).getGold() + " §6§lG");
        score5.setScore(9);

        Score score6 = objective.getScore("  ");
        score6.setScore(8);

        Score score7 = objective.getScore(" §6§lSP§a§l: §e§l" + TALSAPI.getPlayerDeta(p).getMainClass().getSP());
        score7.setScore(7);

        Score score8 = objective.getScore("   ");
        score8.setScore(6);

        Score score9 = objective.getScore(" §3§l[§b§lNews§3§l]");
        score9.setScore(5);

        Score score10 = objective.getScore(" §c§lAlpha §b§lOpen!!");
        score10.setScore(4);

        Score score11 = objective.getScore("    ");
        score11.setScore(3);

        Score score12 = objective.getScore(" §7TALSrpg.dip.jp");
        score12.setScore(2);

        Score score13 = objective.getScore("§6◆§e§l━━━━━━━━━━━━§6◆ ");
        score13.setScore(1);


        new BukkitRunnable() {
            public void run() {

                p.setScoreboard(board);

                this.cancel();
            }
        }.runTaskTimer(plugin,0,0);
    }
}