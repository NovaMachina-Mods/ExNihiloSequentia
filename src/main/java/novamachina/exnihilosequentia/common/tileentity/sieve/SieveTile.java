package novamachina.exnihilosequentia.common.tileentity.sieve;

import com.mojang.authlib.GameProfile;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.init.ModTiles;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SieveTile extends TileEntity {

    private ItemStack meshStack = ItemStack.EMPTY;
    private ItemStack blockStack = ItemStack.EMPTY;
    private EnumMesh meshType = EnumMesh.NONE;
    private float progress = 0;

    public SieveTile() {
        super(ModTiles.SIEVE.get());
    }

    public void insertMesh(ItemStack stack) {
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
        if (!meshStack.isEmpty()) {
            world.addEntity(
                new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
                    meshStack.copy()));
            meshStack = ItemStack.EMPTY;
            meshType = EnumMesh.NONE;
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
    public void read(BlockState state, CompoundNBT compound) {
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

        super.read(state, compound);
//        setSieveState();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
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
        }
    }

    public void activateSieve(boolean isWaterlogged) {
        if (isReadyToSieve()) {
            progress += 0.1F;

            if (progress >= 1.0F) {
                List<SieveRecipe> drops = ExNihiloRegistries.SIEVE_REGISTRY
                    .getDrops(((BlockItem) blockStack.getItem()).getBlock(), meshType, isWaterlogged);
                Random random = new Random();
                drops.forEach((entry -> {
                    entry.getRolls().stream().forEach(meshWithChance -> {
                        if(random.nextFloat() <= meshWithChance.getChance()) {
                            world.addEntity(new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, entry.getDrop()));
                        }
                    });
                }));
                resetSieve();
            }
        }
    }

    private void resetSieve() {
        meshStack.damageItem(1, new FakePlayer((ServerWorld) world, new GameProfile(UUID.randomUUID(), "Fake Player")), player -> System.out.println("Broken"));
        blockStack = ItemStack.EMPTY;
        progress = 0.0F;
        if(meshStack.isEmpty()) {
            meshType = EnumMesh.NONE;
            setSieveState();
        }
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
        CompoundNBT nbt = new CompoundNBT();
        if (!meshStack.isEmpty()) {
            CompoundNBT meshNBT = meshStack.write(new CompoundNBT());
            nbt.put("mesh", meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundNBT blockNbt = blockStack.write(new CompoundNBT());
            nbt.put("block", blockNbt);
        }
        nbt.putFloat("progress", progress);

        return new SUpdateTileEntityPacket(getPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        CompoundNBT nbt = packet.getNbtCompound();
        if (nbt.contains("mesh")) {
            meshStack = ItemStack.read((CompoundNBT) nbt.get("mesh"));
            if (meshStack.getItem() instanceof MeshItem) {
                meshType = ((MeshItem) meshStack.getItem()).getMesh();
            }
        } else {
            meshStack = ItemStack.EMPTY;
        }

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
