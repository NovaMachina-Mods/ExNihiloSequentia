package novamachina.exnihilosequentia.api.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class SerializableRecipe implements IRecipe<IInventory> {
    protected final ResourceLocation id;
    protected final ItemStack outputDummy;
    protected final IRecipeType<?> type;

    protected SerializableRecipe(ItemStack outputDummy, IRecipeType<?> type, ResourceLocation id) {
        this.outputDummy = outputDummy;
        this.type = type;
        this.id = id;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.outputDummy;
    }

    @Override
    public ItemStack getIcon() {
        return getENSerializer().getIcon();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return getENSerializer();
    }

    @Override
    public IRecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    protected abstract RecipeSerializer<? extends SerializableRecipe> getENSerializer();
}
