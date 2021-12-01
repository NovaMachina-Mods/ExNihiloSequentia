package novamachina.exnihilosequentia.api.crafting.crucible;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.api.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucibleTypeEnum;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CrucibleRecipe extends SerializableRecipe {
    public static final RecipeType<CrucibleRecipe> RECIPE_TYPE = RecipeType
            .register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    private static RegistryObject<IRecipeSerializer<CrucibleRecipe>> serializer;
    private int amount;
    private CrucibleTypeEnum crucibleType;
    private Ingredient input;
    private FluidStack resultFluid;

    public CrucibleRecipe(ResourceLocation id, Ingredient input, int amount, FluidStack fluid, CrucibleTypeEnum crucibleType) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.resultFluid = fluid;
        this.crucibleType = crucibleType;
    }

    public static RegistryObject<IRecipeSerializer<CrucibleRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<CrucibleRecipe>> serializer) {
        CrucibleRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CrucibleTypeEnum getCrucibleType() {
        return crucibleType;
    }

    public void setCrucibleType(String crucibleType) {
        this.crucibleType = CrucibleTypeEnum.getTypeByName(crucibleType);
    }

    public Ingredient getInput() {
        return input;
    }

    public void setInput(Ingredient input) {
        this.input = input;
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getItems());
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public FluidStack getResultFluid() {
        return resultFluid;
    }

    public void setResultFluid(FluidStack resultFluid) {
        this.resultFluid = resultFluid;
    }

    @Override
    protected IRecipeSerializer<CrucibleRecipe> getENSerializer() {
        return serializer.get();
    }
}
