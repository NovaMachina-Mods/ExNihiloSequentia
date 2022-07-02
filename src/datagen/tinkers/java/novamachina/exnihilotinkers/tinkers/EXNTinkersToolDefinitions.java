package novamachina.exnihilotinkers.tinkers;

import net.minecraft.data.DataGenerator;
import novamachina.exnihilotinkers.common.tinkers.ToolDefinitions;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nonnull;

import static novamachina.exnihilotinkers.common.init.tconstruct.EXNTinkersTinkerItems.crookHead;
import static slimeknights.tconstruct.tools.TinkerToolParts.toolHandle;
import static slimeknights.tconstruct.tools.TinkerToolParts.toolBinding;
import static slimeknights.tconstruct.tools.TinkerToolParts.hammerHead;

public class EXNTinkersToolDefinitions extends AbstractToolDefinitionDataProvider {
    public EXNTinkersToolDefinitions(DataGenerator generator) {
        super(generator, EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Tool Definitions for Ex Nihilo";
    }

    @Override
    protected void addToolDefinitions() {
        define(ToolDefinitions.exnCrook)
                .part(crookHead)
                .part(toolHandle)
                .stat(ToolStats.ATTACK_DAMAGE, -2F)
                .stat(ToolStats.ATTACK_SPEED, 3F)
                .smallToolStartingSlots()
                .build();
        define(ToolDefinitions.exnHammer)
                .part(hammerHead)
                .part(toolBinding)
                .part(toolHandle)
                .stat(ToolStats.ATTACK_DAMAGE, 0.5F)
                .stat(ToolStats.ATTACK_SPEED, 1.2F)
                .smallToolStartingSlots()
                .build();
    }

}
