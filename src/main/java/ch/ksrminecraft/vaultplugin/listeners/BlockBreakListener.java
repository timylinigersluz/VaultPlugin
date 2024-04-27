package ch.ksrminecraft.vaultplugin.listeners;

import ch.ksrminecraft.vaultplugin.VaultPlugin;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onDiaBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE || event.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            Economy economy = VaultPlugin.getEconomy();
            economy.depositPlayer(event.getPlayer(), 1.0);
            event.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Ein neuer Dia gibt dir " + ChatColor.GOLD + "1$" + ChatColor.GREEN + " , weiter so!"));
            event.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Neuer Kontostand: " + ChatColor.GOLD + economy.getBalance(event.getPlayer())));
        }
    }

}
