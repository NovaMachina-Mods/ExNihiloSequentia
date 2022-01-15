package novamachina.exnihilosequentia.api.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class SerializableRecipe implements IRecipe<IInventory> {
    @Nonnull protected final ResourceLocation id;
    @Nullable protected final ItemStack outputDummy;
    @Nonnull protected final IRecipeType<?> type;

    protected SerializableRecipe(@Nullable final ItemStack outputDummy, @Nonnull final IRecipeType<?> type,
                                 @Nonnull final ResourceLocation id) {
        this.outputDummy = outputDummy;
        this.type = type;
        this.id = id;
    }

    @Override
    public boolean canCraftInDimensions(final int width, final int height) {
        return false;
    }

    @Override
    @Nullable
    public ItemStack assemble(@Nonnull final IInventory inv) {
        return this.outputDummy;
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return getENSerializer().getIcon();
    }

    @Override
    @Nonnull
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return getENSerializer();
    }

    @Override
    @Nonnull
    public IRecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(@Nonnull final IInventory inv, @Nonnull final World worldIn) {
        return false;
    }

    protected abstract RecipeSerializer<? extends SerializableRecipe> getENSerializer();
}
