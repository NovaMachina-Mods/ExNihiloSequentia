package novamachina.exnihilosequentia.api.crafting.sieve;

import com.google.gson.JsonArray;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

import java.util.Arrays;

public class SieveRecipeBuilder extends ExNihiloFinishedRecipe<SieveRecipeBuilder> {
    private final JsonArray meshArray = new JsonArray();

    private SieveRecipeBuilder() {
        super(SieveRecipe.getStaticSerializer().get());

        addWriter(jsonObject -> jsonObject.add("rolls", meshArray));
    }

    public static SieveRecipeBuilder builder() {
        return new SieveRecipeBuilder();
    }

    private void addRoll(MeshWithChance mesh) {
        meshArray.add(mesh.serialize());
    }

    public SieveRecipeBuilder addRolls(MeshWithChance... meshes) {
        for (int i=0; i < meshes.length; i++) {
            addRoll(Arrays.stream(meshes).toList().get(i));
        }
        return this;
    }

    public SieveRecipeBuilder drop(ItemLike drop) {
        return this.addResult(drop);
    }

    public SieveRecipeBuilder input(Ingredient input) {
        return this.addInput(input);
    }

    public SieveRecipeBuilder isWaterlogged() {
        return this.addBoolean("waterlogged", true);
    }
}
