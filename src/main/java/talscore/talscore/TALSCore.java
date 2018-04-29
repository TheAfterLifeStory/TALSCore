package talscore.talscore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import talsapi.talsapi.TALSAPI;
import talsapi.talsapi.api.classes.MainClass;
import talsapi.talsapi.api.manager.enums.Classes;
import talscore.talscore.listener.*;
import talscore.talscore.manager.PlayerManager;

public final class TALSCore extends JavaPlugin implements Listener {

    //クラスの定義
    public ScoreBoard scoreBoard;
    public PlayerManager manager;
    public MenuInventory menuInventory;

    private String prefix = "§6§l≪§c§lT§6§lA§a§lL§b§lS§e§lCore§6§l≫§r ";

     /************************************************************
     |        *                *              +                  |
     |   * Copyright © 2018            *                    *    |
     |             *   The After Life Story        *             |
     |    +                *             All Rights Reserved.    |
     |            *                *          +            *     |
     ************************************************************/

    @Override
    public void onEnable() {

        //EXPの設定
        getServer().getPluginManager().registerEvents(this,this);

        //クラスの初期化
        scoreBoard = new ScoreBoard();
        manager = new PlayerManager();

        //リスナーの定義
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ClassChange(), this);
        getServer().getPluginManager().registerEvents(new LevelChange(), this);

        Bukkit.getConsoleSender().sendMessage(this.prefix + "§eLoading TALSCore");

        manager.sendActionBar();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    {
        LogsMessage(" ");
        LogsMessage("&e&l≫&6&l+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+&e&l≪");
        LogsMessage(" ");
        LogsMessage(" &6PluginName: &c&lT&6&lA&a&lL&b&lS&e&lCore");
        LogsMessage(" &9Creator: &bZero_Reset");
        LogsMessage(" &2MinecraftVersion: &a1.12");
        LogsMessage(" &6PluginVersion: &e0.3");
        LogsMessage(" &fDescription: &7Core plugin of The After Life Story");
        LogsMessage(" &fCopyright @ 2018 &c&lThe &6&lAfter &a&lLife &b&lStory &fAll Rights Reserved");
        LogsMessage(" ");
        LogsMessage("&e&l≫&6&l+=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=+&e&l≪");
        LogsMessage(" ");
        LogsMessage("&7/************************************************************");
        LogsMessage("&7|        &6*                *              &e+                  &7|");
        LogsMessage("&7|   &6* &fCopyright @ 2018            &6*                    *    &7|");
        LogsMessage("&7|             &6*   &fThe After Life Story        &6*             &7|");
        LogsMessage("&7|    &e+                &6*             &fAll Rights Reserved.    &7|");
        LogsMessage("&7|            &6*                *             &e+         &6*     &7|");
        LogsMessage("&7************************************************************/");
        LogsMessage(" ");
    }

    public void LogsMessage(String s)
    {
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }


    @EventHandler
    public void onExp(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        MainClass mainclass = TALSAPI.getPlayerDeta(p).getMainClass();

        if (TALSAPI.getPlayerDeta(p).getClasses()== Classes.NONE) {
            return;
        }

        int Exp = e.getDroppedExp();
        double NowExp = mainclass.getExp();
        mainclass.setExp(NowExp += Exp);
        p.sendMessage("§a" + Exp + " §2Exp 入手しました");
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

        int NowLevel = mainclass.getLevel();
        for(int level; NowExp > mainclass.getNextExp();) {
            mainclass.setExp(NowExp - mainclass.getNextExp());
            mainclass.setLevel(NowLevel += 1);
        }
    }
}
