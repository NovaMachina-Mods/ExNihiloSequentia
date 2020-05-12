package com.novamachina.exnihilosequentia.common.tileentity.sieve;

import com.novamachina.exnihilosequentia.common.block.BlockSieve;
import com.novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import com.novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class SieveTile extends TileEntity {

    private ItemStack meshStack  = ItemStack.EMPTY;
    private ItemStack blockStack = ItemStack.EMPTY;
    private EnumMesh  meshType   = EnumMesh.NONE;
    private float     progress   = 0;

    public SieveTile() {
        super(ModTiles.SIEVE_TILE.get());
    }

    public void insertMesh(ItemStack stack) {
        LogUtil.info("Insert Mesh");
        EnumMesh mesh = ((MeshItem) stack.getItem()).getMesh();
        if (meshStack.isEmpty()) {
            meshStack = stack.copy();
            meshStack.setCount(1);
            stack.shrink(1);
            meshType = mesh;
            if (!isRemoved()) {
                setSieveState();
            }
            markDirty();
        }
    }

    public void removeMesh(boolean rerenderSieve) {
        LogUtil.info("Remove Mesh");
        if (!meshStack.isEmpty()) {
            world.addEntity(
                new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
                    meshStack.copy()));
            meshStack = ItemStack.EMPTY;
            meshType  = EnumMesh.NONE;
            if (rerenderSieve) {
                setSieveState();
            }
        }
    }

    private void setSieveState() {
        BlockState state = getBlockState();
        if (state.getBlock() instanceof BlockSieve) {
            world.setBlockState(getPos(), state.with(BlockSieve.MESH, meshType));
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        LogUtil.info("Read Sieve State");
        if (compound.contains("mesh")) {
            meshStack = ItemStack.read((CompoundNBT) compound.get("mesh"));
            if (meshStack.getItem() instanceof MeshItem) {
                meshType = ((MeshItem) meshStack.getItem()).getMesh();
            }
        } else {
            meshStack = ItemStack.EMPTY;
        }

        if (compound.contains("block")) {
            blockStack = ItemStack.read((CompoundNBT) compound.get("block"));
        } else {
            blockStack = ItemStack.EMPTY;
        }

        progress = compound.getFloat("progress");

        super.read(compound);
//        setSieveState();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        LogUtil.info("Write Sieve State");
        if (!meshStack.isEmpty()) {
            CompoundNBT meshNBT = meshStack.write(new CompoundNBT());
            compound.put("mesh", meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundNBT blockNBT = blockStack.write(new CompoundNBT());
            compound.put("block", blockNBT);
        }

        compound.putFloat("progress", progress);

        return super.write(compound);
    }

    @Override
    public void remove() {
        if (!world.isRemote()) {
            removeMesh(false);
        }
        super.remove();
    }

    public void insertSiftableBlock(ItemStack stack) {
        if (!meshStack.isEmpty() && blockStack.isEmpty()) {
            blockStack = stack.copy();
            blockStack.setCount(1);
            stack.shrink(1);
            LogUtil.info("Inserted " + blockStack.getItem().toString());
        }
        LogUtil.info(meshStack.isEmpty() ? "No Mesh" : "Block already in sieve");
    }

    public void activateSieve() {
        if (isReadyToSieve()) {
            progress += 0.1F;
            LogUtil.info("Progress: " + progress);

            if (progress >= 1.0F) {
                LogUtil.info("Getting Drops");
                List<Item> drops = SieveDrops
                    .getDrops(((BlockItem) blockStack.getItem()).getBlock(), meshType);
                drops.forEach((item -> {
                    world.addEntity(new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F,
                        pos.getZ() + 0.5F, new ItemStack(item)));
                }));
                resetSieve();
            }
        }
    }

    private void resetSieve() {
        blockStack = ItemStack.EMPTY;
        progress   = 0.0F;
    }

    public boolean isReadyToSieve() {
        return !meshStack.isEmpty() && !blockStack.isEmpty();
    }

    public ResourceLocation getTexture() {
        if (!blockStack.isEmpty()) {
            return blockStack.getItem().getRegistryName();
        }
        return null;
    }

    public ItemStack getBlockStack() {
        return blockStack;
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        LogUtil.info("Send Packet");
        CompoundNBT nbt = new CompoundNBT();
        if (!blockStack.isEmpty()) {
            CompoundNBT blockNbt = blockStack.write(new CompoundNBT());
            nbt.put("block", blockNbt);
        }
        nbt.putFloat("progress", progress);

        return new SUpdateTileEntityPacket(getPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        LogUtil.info("Receive packet");
        CompoundNBT nbt = packet.getNbtCompound();
        if (nbt.contains("block")) {
            blockStack = ItemStack.read((CompoundNBT) nbt.get("block"));
        } else {
            blockStack = ItemStack.EMPTY;
        }
        progress = nbt.getFloat("progress");
    }

    public EnumMesh getMesh() {
        return meshType;
    }
}
