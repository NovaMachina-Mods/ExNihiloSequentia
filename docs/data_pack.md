# Data Pack Creation

Ex Nihilo: Sequentia supports datapacks for the creation of recipes. Defined below are the templates for the recipes that are supported.

!!! Important
    Anywhere that `item/tag` shows up means that you must use `item` or `tag`, not `item/tag`.

## Compost

```lang-json
{
    "type": "exnihilosequentia:compost",
    "input": {
        "item/tag": "minecraft:apple"
    },
    "amount": 100
}
```

- `input`: The item/block being inserted into a barrel to be composted.
- `amount`: The amount an item/block contributes to the solid amount in a barrel.

## Crook

```lang-json
{
    "type": "exnihilosequentia:crook",
    "results": [
        {
            "chance": 0.1,
            "item": "exnihilosequentia:silkworm",
            "count": 1
        }
    ],
    "input": {
        "item/tag": "minecraft:leaves"

}
```

- `results`: A list of items that can be dropped and the chance that they will be.
  - `chance`: A value from `0.0` to `1.0`.
  - `item`: The item to be dropped.
  - `count`: A value greater than 0.
- `input`: The block/type of block that must be broken by a crook to produce the results.

## Crucible

```lang-json
{
    "type": "exnihilosequentia",
    "input": {
        "item/tag": "minecraft:cobblestone"
    },
    "amount": 250,
    "fluidResult": {
        "fluid": "minecraft:lava"
    },
    "crucibleType": "fired"
}
```

- `input`: The block/type of block to be placed in a crucible to produce the connected fluid.
- `amount`: The amount of fluid that will be produced by the input (represented in millibuckets).
- `fluidResult`: The fluid that results from the input.
- `crucibleType` The type of crucible that this recipe will work in. Must be `fired` or `wood`. Any recipe that can be created in a `wood` crucible can also be made in a `fired` crucible. There is no need to create two recipes for each crucible if this is your desired result.

## Fluid Item Transformation

```lang-json
{
    "type": "exnihilosequentia:fluid_item",
    "fluid": {
        "fluid": "exnihilosequentia:sea_water"
    },
    "input": {
        "item/tag": "exnihilosequentia:seed_pink_coral"
    },
    "result": {
        "item": "minecraft:brain_coral_block"
    }
}
```

- `fluid`: The fluid in the barrel.
- `input`: The item or type of item to be consumed by the recipe.
- `result`: The resulting item/block.

## Fluid On Top

```lang-json
{
    "type: "exnihilosequentia:fluid_on_top",
    "fluidInTank": {
        "fluid": "minecraft:water"
    },
    "fluidOnTop": {
        "fluid": "minecraft:lava"
    },
    "result": {
        "item": "minecraft:cobblestone"
    }
}
```
- `fluidInTank`: The fluid in the tank that will be consumed.
- `fluidOnTop`: The fluid that will be placed on top of the barrel that will not be consumed.
- `result`: The resulting block.

## Fluid Transformation

```lang-json
{
    "type": "exnihilosequentia:fluid_transform",
    "fluidInTank": {
        "fluid": "minecraft:water"
    },
    "catalyst": {
        "item/tag": "minecraft:sand"
    },
    "result": {
        "fluid": "exnihilosequentia:sea_water"
    }
}
```

- `fluidInTank`: The fluid to be transformed.
- `catalyst`: The block/type of block that must be below the barrel to transform the fluid. May also be an item that is inserted into the barrel with the fluid in it.
- `result`: The resulting fluid.

## Hammer

```lang-json
{
    "type": "exnihilosequentia:hammer",
    "results": [
        {
            "item": "minecraft:sand",
            "chance": 1.0,
            "count": 1
        }
    ],
    "input": {
        "item": "minecraft:gravel"
    }
}
```

- `results`: A list of possible drops.
    - `item`: The item to be dropped. Must be an item.
    - `chance`: A value from `0.0` to `1.0`.
    - `count`: A value greater than `0`.
- `input`: The block to be hammered. May be a tag.
- `result`: The resulting block.

## Heat

```lang-json
{
    "type": "exnihilosequentia:heat",
    "block": "minecraft:fire",
    "amount": 4
}
```

- `block`: The block placed below a crucible that will generate heat.
- `amount`: The number of millibuckets that will be melted down per operation.

## Sieve

```lang-json
{
    "type": "exnihilosequentia:sieve",
    "rolls": [
        {
            "chance": 1.0,
            "mesh": "string"
        }
    ],
    "input": {
        "item/tag": "minecraft:dirt"
    },
    "result": {
        "item": "exnihilosequentia:pebble_stone"
    },
    "waterlogged": true
}
```

- `rolls`: A list of rolls for this recipe.
    - `chance`: A value from `0.0` to `1.0`.
    - `mesh`: The mesh required to cause this roll to be considered. Must be `string`, `flint`, `iron`, `diamond`, `emerald`, or `netherite`.
- `input`: The block/type of block that will be consumed by the sieve.
- `result`: The resulting item.
- `waterlogged`: The sieve must be placed in water to produce result. Either `true` or `false`. Optional and enitire tag may be omitted. Will default to `false`. 