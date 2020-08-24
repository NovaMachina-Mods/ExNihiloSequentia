package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.Config;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagCollection;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class BarrelTile extends TileEntity implements ITickableTileEntity {
    private ItemStackHandler inventory;
    private final LazyOptional<IItemHandler> inventoryHolder = LazyOptional.of(() -> inventory);
    private BarrelFluidHandler tank;
    private final LazyOptional<IFluidHandler> tankHolder = LazyOptional.of(() -> tank);
    private AbstractBarrelMode mode;
    private int solidAmount;
    public static int MAX_SOLID_AMOUNT = Config.BARREL_MAX_SOLID_AMOUNT.get();
    public static int MAX_FLUID_AMOUNT = Config.BARREL_NUMBER_OF_BUCKETS.get() * FluidAttributes.BUCKET_VOLUME;

    public BarrelTile() {
        super(ModTiles.BARREL.get());
        this.mode = BarrelModeRegistry.getModeFromName(Constants.BarrelModes.EMPTY);
        inventory = new ItemStackHandler();
        tank = new BarrelFluidHandler(this);
        solidAmount = 0;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void tick() {
        if (world.isRemote()) {
            return;
        }

        if (mode.isEmptyMode() || mode.getModeName().equals(Constants.BarrelModes.FLUID)) {
            BlockPos abovePos = pos.add(0, 1, 0);
            if(getWorld().isRainingAt(abovePos)) {
                FluidStack stack = new FluidStack(Fluids.WATER, Config.RAIN_FILL_AMOUNT.get());
                tank.fill(stack, IFluidHandler.FluidAction.EXECUTE);
            }
        }
        mode.tick(this);
        getWorld().notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 2);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.putString("barrelMode", mode.getModeName());
        compound.put("modeInfo", mode.write());
        compound.putInt("solidAmount", solidAmount);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        if(compound.contains("inventory")) {
            inventory.deserializeNBT(compound.getCompound("inventory"));
        }
        if(compound.contains("tank")) {
            tank.readFromNBT(compound.getCompound("tank"));
        }
        if(compound.contains("barrelMode")) {
            mode = BarrelModeRegistry.getModeFromName(compound.getString("barrelMode"));
        }
        if(compound.contains("modeInfo")) {
            mode.read(compound.getCompound("modeInfo"));
        }
        if(compound.contains("solidAmount")) {
            this.solidAmount = compound.getInt("solidAmount");
        }
        super.read(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT nbt = pkt.getNbtCompound();
        if(nbt.contains("inventory")) {
            inventory.deserializeNBT(nbt.getCompound("inventory"));
        }
        if(nbt.contains("tank")) {
            tank.readFromNBT(nbt.getCompound("tank"));
        }
        mode = BarrelModeRegistry.getModeFromName(nbt.getString("mode"));
        if(nbt.contains("modeInfo")) {
            mode.read(nbt.getCompound("modeInfo"));
        }
        solidAmount = nbt.getInt("solidAmount");
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("inventory", inventory.serializeNBT());
        nbt.put("tank", tank.writeToNBT(new CompoundNBT()));
        nbt.putString("mode", mode.getModeName());
        nbt.put("modeInfo", mode.write());
        nbt.putInt("solidAmount", solidAmount);

        return new SUpdateTileEntityPacket(getPos(), -1, nbt);
    }

    public ActionResultType onBlockActivated(PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        return mode.onBlockActivated(this, player, handIn, fluidHandler, itemHandler);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryHolder.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return tankHolder.cast();
        }
        return super.getCapability(cap, side);
    }

    public void setMode(String nextState) {
        mode = BarrelModeRegistry.getModeFromName(nextState);
        if(mode == null) {
            mode = BarrelModeRegistry.getModeFromName("empty");
        }
    }

    public int getSolidAmount() {
        return this.solidAmount;
    }

    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    public boolean addSolid(int amount) {
        if(amount <= 0) {
            return false;
        }
        if(solidAmount == MAX_SOLID_AMOUNT) {
            return false;
        }
        solidAmount += amount;
        if(solidAmount > MAX_SOLID_AMOUNT) {
            solidAmount = MAX_SOLID_AMOUNT;
        }
        return true;
    }

    public void removeSolid(int amount) {
        solidAmount -= amount;
        if(solidAmount < 0) {
            solidAmount = 0;
        }
    }

    public AbstractBarrelMode getMode() {
        return mode;
    }

    public void setMode(AbstractBarrelMode mode) {
        this.mode = mode;
    }

    public ResourceLocation getSolidTexture() {
        if (!inventory.getStackInSlot(0).isEmpty()) {
            return inventory.getStackInSlot(0).getItem().getRegistryName();
        }
        return null;
    }

    public Fluid getFluid() {
        if (!tank.isEmpty()) {
            return tank.getFluid().getFluid();
        }
        return null;
    }

    public float getSolidProportion() {
        return (float)solidAmount / MAX_SOLID_AMOUNT;
    }

    public float getFluidProportion() {
        return (float)tank.getFluidAmount() / MAX_FLUID_AMOUNT;
    }


    @Override
    public void remove() {
        super.remove();
        NonNullList<ItemStack> list = NonNullList.create();
        list.add(inventory.getStackInSlot(0));
        InventoryHelper.dropItems(getWorld(), getPos(), list);
    }

    public List<ITextComponent> getWailaInfo() {
        return mode.getWailaInfo(this);
    }
}
