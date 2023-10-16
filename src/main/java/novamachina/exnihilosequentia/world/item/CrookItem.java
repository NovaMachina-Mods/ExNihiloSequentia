package novamachina.exnihilosequentia.world.item;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
import novamachina.exnihilosequentia.tags.ExNihiloTags;
import novamachina.novacore.world.item.ItemDefinition;

public class CrookItem extends DiggerItem {

  public CrookItem(Tier tier, final int maxDamage) {
    super(
        0.5F,
        0.5F,
        tier,
        ExNihiloTags.MINEABLE_WITH_CROOK,
        new Item.Properties().defaultDurability(maxDamage));
  }

  @Override
  public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
    ItemDefinition<CrookItem> woodRegistryObject = EXNItems.CROOK_WOOD;
    if (itemStack.getItem() == woodRegistryObject.asItem()) {
      return 200;
    } else {
      return 0;
    }
  }
}
