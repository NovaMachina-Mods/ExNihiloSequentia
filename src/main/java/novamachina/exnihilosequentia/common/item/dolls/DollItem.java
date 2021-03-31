package novamachina.exnihilosequentia.common.item.dolls;

import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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

public class DollItem extends Item {
    private final EnumDoll type;

    public DollItem(EnumDoll type) {
        super(new Item.Properties().tab(ExNihiloInitialization.ITEM_GROUP));
        this.type = type;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(type.getToolTip()));
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

    public boolean spawnMob(World world, BlockPos pos) {
        ResourceLocation spawneeResourceLocation = new ResourceLocation(type.getEntityModId(), type.getEntityName());

        if (ForgeRegistries.ENTITIES.containsKey(spawneeResourceLocation)) {
            Entity spawnee = ForgeRegistries.ENTITIES.getValue(spawneeResourceLocation).create(world);
            spawnee.setPos(pos.getX(), pos.getY() + type.getYOffset(), pos.getZ());
            return world.addFreshEntity(spawnee);
        }
        return false;
    }
}
