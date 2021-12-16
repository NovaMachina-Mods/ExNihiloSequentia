package novamachina.exnihilosequentia.api.crafting.crucible;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRecipe extends SerializableRecipe {
    @Nonnull public static final IRecipeType<CrucibleRecipe> RECIPE_TYPE = IRecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    @Nullable private static RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer;
    private int amount;
    @Nullable private CrucibleTypeEnum crucibleType;
    @Nonnull private Ingredient input;
    @Nonnull private FluidStack resultFluid;

    public CrucibleRecipe(@Nonnull final ResourceLocation id, @Nonnull final Ingredient input, final int amount,
                          @Nonnull final FluidStack fluid, @Nonnull final CrucibleTypeEnum crucibleType) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.resultFluid = fluid;
        this.crucibleType = crucibleType;
    }

    @Nullable
    public static RegistryObject<RecipeSerializer<CrucibleRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer) {
        CrucibleRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Nullable
    public CrucibleTypeEnum getCrucibleType() {
        return crucibleType;
    }

    public void setCrucibleType(@Nonnull final String crucibleType) {
        this.crucibleType = CrucibleTypeEnum.getTypeByName(crucibleType);
    }

    @Nonnull
    public Ingredient getInput() {
        return input;
    }

    public void setInput(@Nonnull final Ingredient input) {
        this.input = input;
    }

    @Nonnull
    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Nonnull
    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public void setResultFluid(@Nonnull final FluidStack resultFluid) {
        this.resultFluid = resultFluid;
    }

    @Override
    @Nullable
    protected RecipeSerializer<CrucibleRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
