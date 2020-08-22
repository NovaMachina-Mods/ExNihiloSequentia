package com.novamachina.exnihilosequentia.common.item.dolls;

import com.novamachina.exnihilosequentia.common.setup.ModInitialization;
import mezz.jei.api.registration.IModIngredientRegistration;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class DollItem extends Item {
    private DollEnum type;

    public DollItem(DollEnum type) {
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP));
        this.type = type;
    }

    public boolean spawnMob(World world, BlockPos pos) {
        ResourceLocation spawneeResourceLocation = new ResourceLocation(type.getEntityModId(), type.getEntityName());

        if(ForgeRegistries.ENTITIES.containsKey(spawneeResourceLocation)) {
            Entity spawnee = ForgeRegistries.ENTITIES.getValue(spawneeResourceLocation).create(world);
            spawnee.setPosition(pos.getX(), pos.getY() + type.getYOffset(), pos.getZ());
            return world.addEntity(spawnee);
        }
        return false;
    }

    public Fluid getSpawnFluid() {
        ResourceLocation fluidLocation = new ResourceLocation(type.getFluidModId(), type.getFluidName());

        if(ForgeRegistries.FLUIDS.containsKey(fluidLocation)) {
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidLocation);
            return fluid;
        }
        return null;
    }

    public String getDollType() {
        return type.getEntityName();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent(type.getToolTip()));
    }
}
