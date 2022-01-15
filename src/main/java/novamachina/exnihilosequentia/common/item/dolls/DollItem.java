package novamachina.exnihilosequentia.common.item.dolls;

import java.util.List;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DollItem extends Item {
    @Nonnull private final EnumDoll type;

    public DollItem(@Nonnull final EnumDoll type) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.type = type;
    }

    @Override
    public void appendHoverText(@Nonnull final ItemStack stack, @Nullable final Level worldIn,
                                @Nonnull final List<Component> tooltip, @Nonnull final TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent(type.getToolTip()));
    }

    @Nonnull
    public String getDollType() {
        return type.getEntityName();
    }

    public Fluid getSpawnFluid() {
        @Nonnull final ResourceLocation fluidLocation = new ResourceLocation(type.getFluidModId(), type.getFluidName());

        if (ForgeRegistries.FLUIDS.containsKey(fluidLocation)) {

            return ForgeRegistries.FLUIDS.getValue(fluidLocation);
        }
        return Fluids.EMPTY;
    }

    public boolean spawnMob(@Nonnull final Level world, @Nonnull final BlockPos pos) {
        @Nonnull ResourceLocation spawneeResourceLocation = new ResourceLocation(type.getEntityModId(), type.getEntityName());

        if (ForgeRegistries.ENTITIES.containsKey(spawneeResourceLocation)) {
            @Nullable final EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(spawneeResourceLocation);
            if (entityType != null) {
                @Nullable final Entity spawnee = entityType.create(world);
                if (spawnee != null) {
                    spawnee.setPos(pos.getX(), pos.getY() + type.getYOffset(), pos.getZ());
                    return world.addFreshEntity(spawnee);
                }
            }
        }
        return false;
    }
}
