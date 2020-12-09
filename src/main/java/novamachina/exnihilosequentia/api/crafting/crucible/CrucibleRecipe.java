package novamachina.exnihilosequentia.api.crafting.crucible;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class CrucibleRecipe extends SerializableRecipe {
    public static final IRecipeType<CrucibleRecipe> RECIPE_TYPE = IRecipeType
        .register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    private static RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer;
    private final Ingredient input;
    private final int amount;
    private final FluidStack resultFluid;
    private final CrucilbeTypeEnum crucipleType;

    public CrucibleRecipe(ResourceLocation id, Ingredient input, int amount, FluidStack fluid, CrucilbeTypeEnum crucibleType) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.resultFluid = fluid;
        this.crucipleType = crucibleType;
    }

    public static RegistryObject<RecipeSerializer<CrucibleRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer) {
        CrucibleRecipe.serializer = serializer;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getAmount() {
        return amount;
    }

    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public CrucilbeTypeEnum getCrucibleType() {
        return crucipleType;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return serializer.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }
}
