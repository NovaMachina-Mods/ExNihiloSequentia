package novamachina.exnihilosequentia.common.item.pebbles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SnowballItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.init.ExNihiloSounds;
import novamachina.exnihilosequentia.common.utility.Config;

public class PebbleItem extends SnowballItem {
    public PebbleItem() {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
    }
}
