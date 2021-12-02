package novamachina.exnihilosequentia.api.crafting.heat;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.api.crafting.IRecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

import javax.annotation.Nonnull;
import java.util.Objects;

public class HeatRecipe extends SerializableRecipe {
    public static final RecipeType<HeatRecipe> RECIPE_TYPE = RecipeType.register(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + ":heat");
    private static RegistryObject<IRecipeSerializer<HeatRecipe>> serializer;
    private int amount;
    private Block input;
    private StatePropertiesPredicate properties;

    public HeatRecipe(ResourceLocation id, Block input, int amount) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = null;
    }

    public HeatRecipe(ResourceLocation id, Block input, int amount, StatePropertiesPredicate properties) {
        super(null, RECIPE_TYPE, id);
        this.input = input;
        this.amount = amount;
        this.properties = properties;
    }

    public static RegistryObject<IRecipeSerializer<HeatRecipe>> getStaticSerializer() {
        return serializer;
    }

    public static void setSerializer(RegistryObject<IRecipeSerializer<HeatRecipe>> serializer) {
        HeatRecipe.serializer = serializer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Block getInput() {
        return input;
    }

    public StatePropertiesPredicate getProperties() {
        return this.properties;
    }

    public void setInput(Block input) {
        this.input = input;
    }

    public void setProperties(StatePropertiesPredicate properties) {
        this.properties = properties;
    }

    public boolean isMatch(BlockState state) {
        return Objects.equals(state.getBlock().getRegistryName(), this.input.getRegistryName())
                && (this.properties == null || this.properties.matches(state));
    }

    @Nonnull
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected IRecipeSerializer<HeatRecipe> getENSerializer() {
        return serializer.get();
    }
}
