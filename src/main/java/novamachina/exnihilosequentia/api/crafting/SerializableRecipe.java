package novamachina.exnihilosequentia.api.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public abstract class SerializableRecipe implements Recipe<Container> {
    protected final ResourceLocation id;
    protected final ItemStack outputDummy;
    protected final RecipeType<?> type;

    protected SerializableRecipe(ItemStack outputDummy, RecipeType<?> type, ResourceLocation id) {
        this.outputDummy = outputDummy;
        this.type = type;
        this.id = id;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack assemble(@Nonnull Container inv) {
        return this.outputDummy;
    }

    //TODO
    /*
    @Override
    public ItemStack getToastSymbol() {
        return getENSerializer();
    }*/

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return getENSerializer();
    }

    @Nonnull
    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(@Nonnull Container inv, @Nonnull Level worldIn) {
        return false;
    }

    protected abstract RecipeSerializer<? extends SerializableRecipe> getENSerializer();
}
