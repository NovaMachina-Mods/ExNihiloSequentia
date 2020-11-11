package novamachina.exnihilosequentia.common.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import novamachina.exnihilosequentia.common.command.ReloadCommand;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> command = dispatcher.register(
            Commands.literal(Constants.ModIds.EX_NIHILO_SEQUENTIA)
                .then(ReloadCommand.register(dispatcher))
        );
    }
}