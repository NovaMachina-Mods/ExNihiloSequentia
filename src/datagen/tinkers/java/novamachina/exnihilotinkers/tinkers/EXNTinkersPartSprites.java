package novamachina.exnihilotinkers.tinkers;

import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

import javax.annotation.Nonnull;

public class EXNTinkersPartSprites extends AbstractPartSpriteProvider {
    public EXNTinkersPartSprites() {
        super(EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Tinkers' Parts for Ex Nihilo";
    }

    @Override
    protected void addAllSpites() {
        addHead("crook_head");

        buildTool("crook").addBreakableHead("head").addHandle("handle");
        buildTool("hammer").addBreakableHead("head").addHandle("handle");
    }

}
