package org.nerdbearcraft.nerdBearCraft;

import org.bukkit.plugin.java.JavaPlugin;
import org.nerdbearcraft.nerdBearCraft.commands.*;
import org.nerdbearcraft.nerdBearCraft.commands.impl.*;
import org.nerdbearcraft.nerdBearCraft.listeners.*;

public final class NerdBearCraft extends JavaPlugin {
    private static NerdBearCraft instance;
    {
        instance = this;
    }
    public static NerdBearCraft getInstance() {
        return instance;
    }

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        commandManager = new CommandManager();

        commandManager.register(new HelpCommand());
        commandManager.register(new ItemTypeCommand());
        commandManager.register(new ItemDamageCommand());
        commandManager.register(new ItemDataCommand());
        commandManager.register(new LagClearCommand());

        getServer().getConsoleSender().sendMessage("[NerdBearCraft] Loading NerdBearCraft");
        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new MenuGuiEvent(), this);
        getServer().getPluginManager().registerEvents(new BondorWandEvent(), this);
        getServer().getPluginManager().registerEvents(new VanillaMobSpawnEvent(), this);

        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("bondorwand").setExecutor(new BondorWandCommand());
        getCommand("spawnbondorboss").setExecutor(new BondorBossSpawnCommand());;
        getServer().getConsoleSender().sendMessage("[NerdBearCraft] Successfully enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}