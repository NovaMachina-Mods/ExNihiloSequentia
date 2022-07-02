package novamachina.exnihilotinkers.tinkers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import novamachina.exnihilotinkers.common.init.tconstruct.EXNTinkersTinkerItems;
import novamachina.exnihilotinkers.common.utility.EXNTinkersConstants;
import slimeknights.tconstruct.library.data.recipe.IRecipeHelper;
import slimeknights.tconstruct.tools.data.ToolsRecipeProvider;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class EXNTinkersToolRecipes extends ToolsRecipeProvider implements IRecipeHelper {
    public EXNTinkersToolRecipes(DataGenerator generator) {
        super(generator);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Tool Recipes for Ex Nihilo";
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        this.addEXNToolBuildingRecipes(consumer);
        this.addEXNPartRecipes(consumer);
    }

    @Nonnull
    @Override
    public String getModId() {
        return EXNTinkersConstants.ModIds.EX_NIHILO_TINKERS;
    }

    private void addEXNToolBuildingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, EXNTinkersTinkerItems.exnHammer, folder);
        toolBuilding(consumer, EXNTinkersTinkerItems.exnCrook, folder);
    }

    private void addEXNPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "smeltery/tools/parts/";
        String castFolder = "smeltery/parts/";

        partRecipes(consumer, EXNTinkersTinkerItems.crookHead, EXNTinkersTinkerItems.crookHeadCast, 3, partFolder, castFolder);
    }

}
