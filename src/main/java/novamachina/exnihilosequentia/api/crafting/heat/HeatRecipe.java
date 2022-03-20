package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.ExNihiloRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatRecipe extends SerializableRecipe {
    public static RecipeType<HeatRecipe> RECIPE_TYPE;
    @Nullable private static RegistryObject<ExNihiloRecipeSerializer<HeatRecipe>> serializer;
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

    public static RegistryObject<ExNihiloRecipeSerializer<HeatRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(@Nonnull final RegistryObject<ExNihiloRecipeSerializer<HeatRecipe>> serializer) {
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
    protected ExNihiloRecipeSerializer<HeatRecipe> getENSerializer() {
        if (serializer == null)
            return null;
        return serializer.get();
    }
}
