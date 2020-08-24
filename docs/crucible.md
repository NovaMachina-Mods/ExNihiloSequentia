Crucible
========
The crucible is used to melt various blocks and items into fluids. Right click the barrel to place an item or block into the crucible. Place a heat source under the barrel to begin the melting process. Once a crucible is full, right click with a bucket to add or remove a fluid.

The Fired Crucible can be used to melt down everything that the Wooden Crucible can, however, not everything that the Fired Crucible melts down can be melted by the wooden crucible.

!!! Note
    An Unfired Crucible *MUST* be smelted to be used.

!!! Warning
    Once an item is in the crucible, it cannot be removed. A crucible will void any fluid in it when it is broken.

Recipe
------
### Wooden Crucible
![](images/recipes/crucible_wooden.png)
---
- 4x Wood Log (Any)
- 1x Wood Slab (Any)
- 2x Stick

### Unfired Crucible
![](images/recipes/crucible_unfired.png)
---
- 7x Porcelain Clay (Any)
- Smelt an Unfired Crucible to obtain a Fired Crucible.

### Porcelain Clay
![](images/recipes/porcelain_clay.png)
---
- 1x Bone Meal
- 1x Clay

Wooden Crucible Meltable Items
------------------------------
Items in the following list will melt down into water in the wooden crucible.

| Item         | Amount |
|--------------|:------:|
| All Saplings | 250    |
| All Leaves   | 250    |

Fired Crucible Meltable Items
-----------------------------
Items in the following list will melt down into lava in the fired crucible. In addition, the Fired Crucible can melt down all the items of the Wooden Crucible seciton above into water.

| Item         | Amount |
|--------------|:------:|
| Cobblestone  | 250    |
| Diorite      | 250    |
| Andesite     | 250    |
| Granite      | 250    |
| Stone        | 250    |
| Gravel       | 200    |
| Sand         | 100    |
| Dust         | 50     |
| Netherrack   | 1000   |
| Obsidian     | 1000   |

Heat Sources
------------
The following blocks and fluids can be placed below a crucible to begin the melting process. Their heat amounts determine how quickly they melt items.

| Source      | Heat Rate |
|-------------|:---------:|
| Lava        | 3         |
| Fire        | 4         |
| Torch       | 1         |
| Magma Block | 2         |
| Glowstone   | 2         |

Configuration
-------------
### Crucible Meltable Item Configuration
Configuration for Crucible Meltable Items are found in `~/config/exnihilosequentia/CrucibleRegistry.json`.

The format of the file is: 
```
[
  {
    "entry: "modid:name",
    "fluid: "modid:name",
    "amount: 0 < number,
    "crucibleType": WOOD or FIRED
  },
  ...
]
```
- `modid`: The mod id the block/item/fluid comes from (i.e. `exnihilosequentia`)
- `name`: The name of block/item/fluid (i.e. `witchwater`)

- `entry`: The input item/block (Must be an item, block or a tag). Required field.
- `fluid`: The fluid the entry will melt into (Must be a fluid). Required field.
- `amount`: The amount of fluid produced (Must be an integer greater than 0). Required field.
- `crucibleType`: The minimum crucible requred to start the melting process. (Must be WOOD or FIRED). Required field.

You may chain together as many:
```
{
  "entry: "modid:name",
  "fluid: "modid:name",
  "amount: 0 < number,
  "crucibleType": WOOD or FIRED
}
```
blocks as you'd like as long as they are separated by commas and all of them remain inside `[ ]`.
!!! Important
    Duplicate entries are not allowed. The first recipe in the list will be the one used.
    
### Heat Source Configuration
Configuration for Heat Sources are found in `~/config/exnihilosequentia/HeatRegistry.json`.

The format of the file is: 
```
[
  {
    "entry: "modid:name",
    "amount: 0 < number
  },
  ...
]
```
- `modid`: The mod id the block/item/fluid comes from (i.e. `exnihilosequentia`)
- `name`: The name of block/item/fluid (i.e. `witchwater`)

- `entry`: The block or fluid is a heat source (Must be a block, fluid or a tag). Required field.
- `amount`: The amount of heat the source produces (Must be an integer greater than 0). Required field.

You may chain together as many:
```
{
    "entry: "modid:name",
    "amount: integer
  }
```
blocks as you'd like as long as they are separated by commas and all of them remain inside `[ ]`.
!!! Important
    Duplicate entries are not allowed. The first recipe in the list will be the one used.