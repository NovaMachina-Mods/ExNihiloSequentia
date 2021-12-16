package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatRecipe extends SerializableRecipe {
    @Nonnull public static final IRecipeType<HeatRecipe> RECIPE_TYPE = IRecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    @Nullable private static RegistryObject<RecipeSerializer<HeatRecipe>> serializer;
    private int amount;
    @Nullable private Block input;
    @Nullable private StatePropertiesPredicate properties;

    public HeatRecipe(@Nonnull final ResourceLocation id, @Nullable final Block input, final int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = null;
    }

    public HeatRecipe(@Nonnull final ResourceLocation id, @Nonnull final Block input, final int amount,
                      @Nonnull final StatePropertiesPredicate properties) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = properties;
    }

    @Nullable
    public static RegistryObject<RecipeSerializer<HeatRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<RecipeSerializer<HeatRecipe>> serializer) {
        HeatRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    @Nullable
    public Block getInput() {
        return input;
    }

    @Nullable
    public StatePropertiesPredicate getProperties() {
        return this.properties;
    }

    public void setInput(@Nonnull final Block input) {
        this.input = input;
    }

    public void setProperties(@Nonnull final StatePropertiesPredicate properties) {
        this.properties = properties;
    }

    public boolean isMatch(@Nonnull final BlockState state) {
        if (input == null)
            return false;
        @Nullable final ResourceLocation resourceLocation = state.getBlock().getRegistryName();
        if (resourceLocation == null)
            return false;
        return resourceLocation.equals(input.getRegistryName()) && (properties == null || properties.matches(state));
    }

    @Override
    @Nonnull
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    @Nullable
    protected RecipeSerializer<HeatRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
