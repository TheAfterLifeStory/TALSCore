package talscore.talscore.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import talscore.talscore.TALSCore;

public class MenuInventory extends JavaPlugin implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    public GrandMenu menu;

    public void onMenuEnable() {

        menu = new GrandMenu(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("menu")){

            Player p = (Player) sender;
            menu.show(p.getPlayer());
            return true;

        }
        return false;
    }
}