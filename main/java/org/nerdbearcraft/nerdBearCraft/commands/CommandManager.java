package org.nerdbearcraft.nerdBearCraft.commands;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;
import org.nerdbearcraft.nerdBearCraft.NerdBearCraft;

public class CommandManager {
    private LifecycleEventManager<Plugin> manager;

    public CommandManager() {
        manager = NerdBearCraft.getInstance().getLifecycleManager();
    }

    public void register(AbstractCommand cmd) {
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event ->
                event.registrar().register(cmd.node(), cmd.description, cmd.aliases)
                );
    }
}
