package talscore.talscore.listener;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;
import talscore.talscore.TALSCore;

public class GrandMenu implements Listener {

    TALSCore plugin = TALSCore.getPlugin(TALSCore.class);

    private Inventory inv;
    private ItemStack a, b, c, d;

    public GrandMenu(Plugin p) {
        inv = Bukkit.getServer().createInventory(null, 9, "§6§lGrand Menu");

        a = createItem(DyeColor.GREEN, ChatColor.GREEN + "§b§lSkill§a§l/§c§lAbility Menu");
        b = createItem(DyeColor.YELLOW, ChatColor.YELLOW + "§c§lClass Menu");
        c = createItem(DyeColor.RED, ChatColor.RED + "§d§lParty Menu");
        d = createItem(DyeColor.RED, ChatColor.RED + "§f§lOthers");

        inv.setItem(1, a);
        inv.setItem(3, b);
        inv.setItem(5, c);
        inv.setItem(7, d);

        Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }

    private ItemStack createItem(DyeColor dc, String name) {
        ItemStack i = new Wool(dc).toItemStack(1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList("test aaaaa"));
        i.setItemMeta(im);
        return i;
    }

    public void show(Player p) {
        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Creative")) {
            e.setCancelled(true);
            e.getWhoClicked().setGameMode(GameMode.CREATIVE);
            e.getWhoClicked().closeInventory();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival")) {
            e.setCancelled(true);
            e.getWhoClicked().setGameMode(GameMode.SURVIVAL);
            e.getWhoClicked().closeInventory();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Adventure")) {
            e.setCancelled(true);
            e.getWhoClicked().setGameMode(GameMode.ADVENTURE);
            e.getWhoClicked().closeInventory();
        }
    }
}