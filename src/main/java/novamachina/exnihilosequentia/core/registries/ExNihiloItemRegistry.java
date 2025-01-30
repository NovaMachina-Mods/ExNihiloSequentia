package novamachina.exnihilosequentia.core.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.world.item.CrookItem;
import novamachina.exnihilosequentia.world.item.DollItem;
import novamachina.exnihilosequentia.world.item.HammerItem;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.Ore;
import novamachina.exnihilosequentia.world.item.OreItem;
import novamachina.exnihilosequentia.world.item.ResourceItem;
import novamachina.novacore.core.registries.ItemRegistry;
import novamachina.novacore.world.item.ItemDefinition;

public class ExNihiloItemRegistry extends ItemRegistry {
  public ExNihiloItemRegistry(String modId) {
    super(modId);
  }

  public ItemDefinition<MeshItem> meshItem(
      String englishName,
      String shortId,
      MeshType meshType,
      Item.Properties properties,
      MeshItem.MeshFunction itemSupplier,
      ItemDefinition.ItemType type) {
    properties = properties.setId(key(shortId));
    MeshItem item = itemSupplier.apply(meshType, properties);
    ItemDefinition<MeshItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public ItemDefinition<CrookItem> crookItem(
      String englishName,
      String shortId,
      ToolMaterial tier,
      float baseDamage,
      float attackSpeed,
      Item.Properties properties,
      CrookItem.CrookFunction itemSupplier,
      ItemDefinition.ItemType type) {

    properties = properties.setId(key(shortId));
    CrookItem item = itemSupplier.apply(tier, baseDamage, attackSpeed, properties);
    ItemDefinition<CrookItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public ItemDefinition<HammerItem> hammerItem(
      String englishName,
      String shortId,
      ToolMaterial tier,
      float baseDamage,
      float attackSpeed,
      Item.Properties properties,
      HammerItem.HammerFunction itemSupplier,
      ItemDefinition.ItemType type) {

    properties = properties.setId(key(shortId));
    HammerItem item = itemSupplier.apply(tier, baseDamage, attackSpeed, properties);
    ItemDefinition<HammerItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public ItemDefinition<ResourceItem> resourceItem(
      String englishName,
      String shortId,
      Block triggerBlock,
      Block replaceBlock,
      Item.Properties properties,
      ResourceItem.ResourceItemFunction itemSupplier,
      ItemDefinition.ItemType type) {

    properties = properties.setId(key(shortId));
    ResourceItem item = itemSupplier.apply(triggerBlock, replaceBlock, properties);
    ItemDefinition<ResourceItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public ItemDefinition<DollItem> dollItem(
      String englishName,
      String shortId,
      String entityModId,
      String entityName,
      String fluidModId,
      String fluidName,
      double yOffset,
      String tooltip,
      Item.Properties properties,
      DollItem.DollItemFunction itemSupplier,
      ItemDefinition.ItemType type) {

    properties = properties.setId(key(shortId));
    DollItem item =
        itemSupplier.apply(
            entityModId, entityName, fluidModId, fluidName, yOffset, tooltip, properties);
    ItemDefinition<DollItem> definition =
        new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }

  public <T extends OreItem> ItemDefinition<T> oreItem(
      String englishName,
      String shortId,
      Ore ore,
      Item.Properties properties,
      OreItem.OreItemFunction<T> itemSupplier,
      ItemDefinition.ItemType type) {

    properties = properties.setId(key(shortId));
    T item = itemSupplier.apply(ore, properties);
    ItemDefinition<T> definition = new ItemDefinition<>(englishName, id(shortId), item, type);

    this.register(definition);
    return definition;
  }
}
