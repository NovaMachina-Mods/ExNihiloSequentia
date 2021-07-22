package novamachina.exnihilosequentia.common.item.dolls;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class DollItem extends Item {
    private final EnumDoll type;

    public DollItem(EnumDoll type) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.type = type;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent(type.getToolTip()));
    }

    public String getDollType() {
        return type.getEntityName();
    }

    public Fluid getSpawnFluid() {
        ResourceLocation fluidLocation = new ResourceLocation(type.getFluidModId(), type.getFluidName());

        if (ForgeRegistries.FLUIDS.containsKey(fluidLocation)) {

            return ForgeRegistries.FLUIDS.getValue(fluidLocation);
        }
        return Fluids.EMPTY;
    }

    public boolean spawnMob(Level world, BlockPos pos) {
        ResourceLocation spawneeResourceLocation = new ResourceLocation(type.getEntityModId(), type.getEntityName());

        if (ForgeRegistries.ENTITIES.containsKey(spawneeResourceLocation)) {
            Entity spawnee = ForgeRegistries.ENTITIES.getValue(spawneeResourceLocation).create(world);
            spawnee.setPos(pos.getX(), pos.getY() + type.getYOffset(), pos.getZ());
            return world.addFreshEntity(spawnee);
        }
        return false;
    }
}
