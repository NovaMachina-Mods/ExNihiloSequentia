package novamachina.exnihilosequentia.api.crafting.crucible;

import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
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
        .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    private static RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer;
    private Ingredient input;
    private int amount;
    private FluidStack resultFluid;
    private CrucilbeTypeEnum crucibleType;

    public CrucibleRecipe(ResourceLocation id, Ingredient input, int amount, FluidStack fluid, CrucilbeTypeEnum crucibleType) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.resultFluid = fluid;
        this.crucibleType = crucibleType;
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

    public void setInput(Ingredient input) {
        this.input = input;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public void setResultFluid(FluidStack resultFluid) {
        this.resultFluid = resultFluid;
    }

    public CrucilbeTypeEnum getCrucibleType() {
        return crucibleType;
    }

    public void setCrucibleType(String crucibleType) {
        this.crucibleType = CrucilbeTypeEnum.getTypeByName(crucibleType);
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
