Configuration File
==================
Below is a detailed description of what a value does in the configuration file.

!!! Important
    A tick is 1/20 of a second.
    A bucket holds 1000 millibuckets

## barrelNumberOfBuckets
The number of buckets of fluid a barrel will hold.
*Must be an integer greater than 0.*

## rainFillAmount
The number of millibuckets per tick that a barrel will fill with water per tick.
*Must be an integer greater than 0.*

## woodBarrelMaxTemp
The maximum temperature of a fluid the wooden can accept. Water is 300 and lava is 1300. Anything above 433 is considered hot.
*Must be an integer greater than or equal to 0.*


## secondsToSpawnMobs
The number of seconds that it takes to spawn mobs in a barrel.
*Must be an integer greater than 0.*

## secondsToCompost
The number of seconds that it takes to compost solids in a barrel once its full.
*Must be an integer greater than 0.*

## maxSolidAmount
The max solid amount that a barrel will hold. When the solid amount in a barrel reaches this number, it will start composting. This solid amount will be the equivalent of one dirt block.
*Must be an integer greater than 0.*

## secondsToTransformFluid
The number of seconds that it takes to transform a fluid in a barrel.
*Must be an integer greater than 0.*

## maxBonusStringCount
The maximum number of bonus string that an infested leaf will drop when broken with a crook.
*Must be an integer greater than 0.*

## minStringCount
The maximum number of bonus string that an infested leaf will drop when broken with a crook.
*Must be an integer greater than or equal to 0.*

## vanillaDropSimulateCount
The number of times a crook will simulate breaking a block to get its vanilla drops.
*Must be an integer greater than 0.*

## ticksBetweenMelts
The crucible does not perform a melt operation every tick. This config allows the user to define how many ticks take place in between operations. If it is set to 1, it will perform an operation every tick.
*Must be an integer greater than 0.*

## crucibleNumberOfBuckets
The number of buckets that the crucible will hold.
*Must be a number greater than 0.*

## woodHeatRate
The Wooden Crucible does not use the Heat Registry. Regardless of the heat source placed below it, the Wooden Crucible will melt items down at the rate of this config.
*Must be a number greater than 0.*

## secondsToTransformLeaves
The number of seconds that it takes to completely infest leaves.
*Must be an integer greater than 0.*

## spreadChance
The percent chance that an infested leaf block will spread to a non-infested leaf block next to it.
*Must be a value between 0.001 and 1.0.*

## ticksBetweenSpreadAttempt
Infested leaves do not attempt to spread every tick. This config allows the user to define how many ticks take place in between operations. If it is set to 1, infested leaves will attempt to spread every tick.
*Must be a value greater than 0.*

## flattenSieveRecipes
Sets whether sieves will get the results for all the meshes below the one currently in the sieve. For example, if an iron mesh is in the sieve, it will get results for the iron, flint and string meshes.
*Must be `true` or `false`*.

## sieveRadius
The number of blocks away that the sieve will try to find other sieves to activate. For example, if set to 2, the sieve will look in a 5 x 5 block area (with the sieve that was activated in the center) for other sieves. *Must be a value between 0 and 5*.

## enableMeshDurability
This enables meshes to be breakable. *Must be `true` or `false`*.

## enableThermalExpansion
Enables ore drops for Thermal Expansion ores

## enableImmersiveEngineering
Enables ore drops for Immersive Engineering ores

## enableMekanism
Enables ore drops for Mekanism ores

## enableCreate
Enables ore drops for Create ores

## enableSilentMechanism
Enables ore drops for Silent Mechanism ores

## enableDebugLogging
Enables extra logging.