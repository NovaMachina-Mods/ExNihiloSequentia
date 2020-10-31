package com.novamachina.exnihilosequentia.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.novamachina.exnihilosequentia.common.api.ExNihiloRegistries;
import com.novamachina.exnihilosequentia.common.registries.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.utility.Config;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class ReloadCommand implements Command<CommandSource> {
    public static LiteralArgumentBuilder<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("reload").requires(commandSource -> commandSource.hasPermissionLevel(1))
            .then(Commands.literal("sieve").executes((c) -> reloadRegistry(c, ExNihiloRegistries.SIEVE_REGISTRY)))
//            .then(Commands.literal("crook").executes((c) -> reloadRegistry(c, ExNihiloRegistries.CROOK_REGISTRY)))
            .then(Commands.literal("compost").executes((c) -> reloadRegistry(c, ExNihiloRegistries.COMPOST_REGISTRY)))
//            .then(Commands.literal("hammer").executes((c) -> reloadRegistry(c, ExNihiloRegistries.HAMMER_REGISTRY)))
            .then(Commands.literal("heat").executes((c) -> reloadRegistry(c, ExNihiloRegistries.HEAT_REGISTRY)))
            .then(Commands.literal("crucible").executes((c) -> reloadRegistry(c, ExNihiloRegistries.CRUCIBLE_REGISTRY)))
            .then(Commands.literal("fluidOnTop").executes((c) -> reloadRegistry(c, ExNihiloRegistries.FLUID_ON_TOP_REGISTRY)))
            .then(Commands.literal("fluidTransform").executes((c) -> reloadRegistry(c, ExNihiloRegistries.FLUID_TRANSFORM_REGISTRY)))
            .then(Commands.literal("fluidBlock").executes((c) -> reloadRegistry(c, ExNihiloRegistries.FLUID_BLOCK_REGISTRY)))
            .then(Commands.literal("all").executes((c) -> reloadAll(c)));
    }

    private static int reloadAll(CommandContext<CommandSource> c) {
        if(Config.USE_JSON_REGISTRIES.get()) {
            ExNihiloRegistries.BUS.clearRegistries();
            ExNihiloRegistries.BUS.useJson();
            c.getSource().getEntity().sendMessage(new TranslationTextComponent("command.reload.success"), UUID.randomUUID());
        } else {
            c.getSource().getEntity().sendMessage(new TranslationTextComponent("command.reload.warning"), UUID.randomUUID());
        }
        return 1;
    }

    private static int reloadRegistry(CommandContext<CommandSource> c, AbstractModRegistry registry) {
        if(Config.USE_JSON_REGISTRIES.get()) {
            registry.clear();
            registry.useJson();
            c.getSource().getEntity().sendMessage(new TranslationTextComponent("command.reload.success"), UUID.randomUUID());
        } else {
            c.getSource().getEntity().sendMessage(new TranslationTextComponent("command.reload.warning"), UUID.randomUUID());
        }
        return 1;
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        return 0;
    }
}