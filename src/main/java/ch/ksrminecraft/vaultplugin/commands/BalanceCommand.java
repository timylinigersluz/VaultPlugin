package ch.ksrminecraft.vaultplugin.commands;

import ch.ksrminecraft.vaultplugin.VaultPlugin;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Economy economy = VaultPlugin.getEconomy();


            // eigenen Kontostand ausgeben
            if (strings.length == 0) {
                player.sendMessage(Component.text("Dein Kontostand: " + economy.getBalance(player)));
            }

            // Kontostand eines anderen Spielers ausgeben
            else if (strings.length == 1) {
                Player target = player.getServer().getPlayer(strings[0]);
                if (target != null) {
                    player.sendMessage(Component.text("Kontostand von " + target.getName() + ": " + economy.getBalance(target)));
                } else {
                    player.sendMessage(Component.text(ChatColor.RED + "Spieler nicht gefunden."));
                }
            } else {
                player.sendMessage(Component.text(ChatColor.RED + "Falsche Anzahl Argumente!"));
            }
        }
        return true;
    }
}
