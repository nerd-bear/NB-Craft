package org.nerdbearcraft.nerdBearCraft.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand {
    protected final @NotNull String name;
    protected final @NotNull String description;
    protected final @NotNull List<String> aliases;

    public AbstractCommand(@NotNull String name, @NotNull String description, @NotNull List<String> aliases) {
        this.name = name;
        this.description = description;
        this.aliases = new ArrayList<>(aliases);
    }

    protected abstract LiteralCommandNode<CommandSourceStack> node();
}