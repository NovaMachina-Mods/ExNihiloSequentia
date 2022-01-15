package novamachina.exnihilosequentia.common.item.dolls;

import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
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
    public void appendHoverText(@Nonnull final ItemStack stack, @Nullable final World worldIn,
                                @Nonnull final List<ITextComponent> tooltip, @Nonnull final ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(type.getToolTip()));
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

    public boolean spawnMob(@Nonnull final World world, @Nonnull final BlockPos pos) {
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
