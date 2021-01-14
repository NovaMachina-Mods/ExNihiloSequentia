CraftTweaker Support
====================
Ex Nihilo: Sequentia supports CraftTweaker scripts. Be sure to check the [CraftTweaker Documentation] before getting started here to make sure you understand how CraftTweaker works.

All Ex Nihilo: Sequentia recipes follow a builder pattern, so ordering is not important as long as all the required methods are there.

Compost Recipes
---------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setInput(IIngredient input)`: Item or Tag. Required.
- `setAmount(int amount)`: A value greater than 0. Required.

### Example
```
import mods.exnihilosequentia.ZenCompostRecipe;

<recipetype:exnihilosequentia:compost>.create("example").setInput(<item:minecraft:oak_leaves>).setAmount(100);
```

Crook Recipes
-------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setInput(IIngredient input)`: Block to be crooked. May be a block or a tag. Required.
- `addDrop(IItemStack drop, float chance)`: The item to drop and its chance. May be called multiple times to add more drops.

### Example
```
import mods.exnihilosequentia.ZenCrookRecipe;

<recipetype:exnihilosequentia:crook>.create("example").setInput(<item:minecraft:oak_leaves>).addDrop(<item:exnihilosequentia:silkworm>, 1).addDrop(<item:minecraft:diamond>, 2);
```

Crucible Recipes
----------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setInput(IIngredient input)`: Item or Tag. Required.
- `setAmount(int amount)`: A value greater than 0. Required.
- `setCrucibleType(String crucibleType)`: Must be `wood` or `fired`. Defaults to `wood`.
- `setResultFluid(IFluidStack fluid)`: Resulting fluid. Required.

### Example
```
import mods.exnihilosequentia.ZenCrucibleRecipe;

<recipetype:exnihilosequentia:crucible>.create("example").setInput(<item:minecraft:cobblestone>).setAmount(100).setCrucibleType("fired").setResutFluid(<fluid:minecraft:lava>);
```

Fluid Item Recipes
------------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setFluidInTank(IFluidStack fluidInTank)`: Fluid in the tank. Required.
- `setInputItem(IIngredient inputItem)`: Item or Tag. Required.
- `setResult(IItemStack result)`: Resulting item. Required.

### Example
```
import mods.exnihilosequentia.ZenFluidItemRecipe;

<recipetype:exnihilosequentia:fluid_item>.create("example").setFluidInTank(<fluid:minecraft:water>).setInputItem(<item:minecraft:diamond>).setResult(<item:minecraft:dirt>);
```

Fluid On Top Recipes
--------------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setFluidInTank(IFluidStack fluidInTank)`: Fluid in the tank. Required.
- `setFluidOnTop(IFluidStack fluidOnTop)`: Fluid placed on top. Required.
- `setResult(IItemStack result)`: Resulting item. Required.

### Example
```
import mods.exnihilosequentia.ZenFluidOnTopRecipe;

<recipetype:exnihilosequentia:fluid_on_top>.create("example").setFluidInTank(<fluid:minecraft:lava>).setFluidOnTop(<fluid:minecraft:water>).setResult(<item:minecraft:obsidian>);
```

Fluid Transform Recipes
-----------------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setFluidInTank(IFluidStack fluidInTank)`: Fluid in the tank. Required.
- `setCatalyst(IIngredient catalyst)`: Block to be placed below or item to be inserted. Required.
- `setResult(IFluidStack result)`: Resulting fluid. Required.

### Example
```
import mods.exnihilosequentia.ZenFluidTransformRecipe;

<recipetype:exnihilosequentia:fluid_transform>.create("example").setFluidInTank(<fluid:minecraft:lava>).setCatalyst(<item:minecraft:diamond>).setResult(<fluid:minecraft:water>);
```

Hammer Recipes
--------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setInput(IIngredient input)`: Block to be hammerd. May be a block to tag. Required.
- `addOutput(IItemStack drop)`: The item to drop 100% of the time. May be called multiple times to add more drops.
- `addOutput(IItemStack drop, float chance)`: The item to drop and its chance. May be called multiple times to add more drops.

### Example
```
import mods.exnihilosequentia.ZenHammerRecipe;

<recipetype:exnihilosequentia:hammer>.create("example").setInput(<item:minecraft:cobblestone>).addOutput(<item:minecraft:gravel>).addOutput(<item:minecraft:diamond>, 0.01);
```

Heat Recipes
------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setBlock(MCBlock input)`: Block to add as a heat source. Required.
- `setAmount(int amount)`: Heating amount. Required.

### Example
```
import mods.exnihilosequentia.ZenHeatRecipe;

<recipetype:exnihilosequentia:heat>.create("example").setBlock(<block:minecraft:cobblestone>).setAmount(100);
```

Sieve Recipes
-------------
### Methods
- `create(String name)`: Must be a unique name. Must be first. Required.
- `setInput(IIngredient input)`: Block to be sieved. May be a block or a tag. Required.
- `addDrop(IItemStack drop)`: Item to be dropped in this recipe. Required.
- `addRoll(String mesh, float chance)`: A mesh and its associated drop chance for the above drop. Mesh must be `string`, `flint`, `iron`, `diamond`, `emerald`, or `netherite`. May be called multiple times to add more rolls.
- `setWaterlogged()`: Sets the recipe to require a waterlogged sieve.

### Example
```
import mods.exnihilosequentia.ZenSieveRecipe;

<recipetype:exnihilosequentia:sieve>.create("example").setInput(<item:minecraft:cobblestone>).addDrop(<item:minecraft:netherite_ingot>).addRoll("diamond", 0.01).addRoll("string", 1.0).setWaterlogged();
```

[CraftTweaker Documentation]: https://docs.blamejared.com/