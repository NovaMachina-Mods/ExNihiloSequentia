package novamachina.exnihilosequentia.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

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

    @Override
    public ItemStack assemble(Container inv) {
        return this.outputDummy;
    }

    //TODO
    /*@Override
    public ItemStack getToastSymbol() {
        return getENSerializer();
    }*/

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return getENSerializer();
    }

    @Override
    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(Container inv, Level worldIn) {
        return false;
    }

    protected abstract RecipeSerializer<? extends SerializableRecipe> getENSerializer();
}
