import mods.exnihilosequentia.SieveRecipe;

<recipetype:exnihilosequentia:sieve>.create("dummy_sifting")
    .setInput(<tag:items:minecraft:sand>).addDrop(<item:minecraft:diamond_block>)
    .addRoll("string", 1F).setWaterlogged();