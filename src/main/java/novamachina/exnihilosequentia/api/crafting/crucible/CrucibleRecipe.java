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
import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants.ModIds;

public class CrucibleRecipe extends SerializableRecipe {
    public static final IRecipeType<CrucibleRecipe> RECIPE_TYPE = IRecipeType
            .register(ModIds.EX_NIHILO_SEQUENTIA + ":crucible");
    private static RegistryObject<RecipeSerializer<CrucibleRecipe>> serializer;
    private int amount;
    private CrucilbeTypeEnum crucibleType;
    private Ingredient input;
    private FluidStack resultFluid;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CrucilbeTypeEnum getCrucibleType() {
        return crucibleType;
    }

    public void setCrucibleType(String crucibleType) {
        this.crucibleType = CrucilbeTypeEnum.getTypeByName(crucibleType);
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
    protected RecipeSerializer<CrucibleRecipe> getENSerializer() {
        return serializer.get();
    }
}
