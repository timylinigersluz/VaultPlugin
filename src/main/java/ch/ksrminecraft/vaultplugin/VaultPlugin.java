package ch.ksrminecraft.vaultplugin;

import ch.ksrminecraft.vaultplugin.commands.BalanceCommand;
import ch.ksrminecraft.vaultplugin.listeners.BlockBreakListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VaultPlugin extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("kontostand").setExecutor(new BalanceCommand());
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Vault: Economy-Objekt zurückgeben → für andere Klassen
    public static Economy getEconomy() {
        return econ;
    }

}
