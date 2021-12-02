package novamachina.exnihilosequentia.common.tileentity;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.FakePlayer;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.init.ExNihiloStats;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.item.mesh.EnumMesh;
import novamachina.exnihilosequentia.common.item.mesh.MeshItem;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class SieveTile extends BlockEntity {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    private static final String BLOCK_TAG = "block";
    private static final String PROGRESS_TAG = "progress";
    private static final String MESH_TAG = "mesh";
    private final Random random = new Random();

    private ItemStack meshStack = ItemStack.EMPTY;
    private ItemStack blockStack = ItemStack.EMPTY;
    private EnumMesh meshType = EnumMesh.NONE;
    private int progress = 0;

    private long lastSieveAction = 0;
    private UUID lastPlayer;

    public SieveTile(BlockPos pos, BlockState state) {
        super(ExNihiloTiles.SIEVE.get(), pos, state);
    }

    public void insertMesh(ItemStack stack, Player player) {
        logger.debug("Insert Mesh: " + stack);
        EnumMesh mesh = ((MeshItem) stack.getItem()).getMesh();
        if (meshStack.isEmpty()) {
            meshStack = stack.copy();
            meshStack.setCount(1);
            if(!player.isCreative()) {
                stack.shrink(1);
            }
            meshType = mesh;
            if (!isRemoved()) {
                setSieveState();
            }
            setChanged();
        }
    }

    public void removeMesh(boolean reRenderSieve) {
        logger.debug("Remove mesh: Re-render Sieve: " + reRenderSieve);
        if (!meshStack.isEmpty()) {
            assert level != null;
            level.addFreshEntity(
                new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F,
                    meshStack.copy()));
            meshStack = ItemStack.EMPTY;
            meshType = EnumMesh.NONE;
            if (reRenderSieve) {
                setSieveState();
            }
        }
    }

    public void setSieveState() {
        logger.debug("Set Sieve State, Mesh: " + meshType);
        BlockState state = getBlockState();
        if (state.getBlock() instanceof BlockSieve) {
            assert level != null;
            level.setBlockAndUpdate(getBlockPos(), state.setValue(BlockSieve.MESH, meshType));
        }
    }

    @Override
    public void load(CompoundTag compound) {
        if (compound.contains(MESH_TAG)) {
            meshStack = ItemStack.of((CompoundTag) Objects.requireNonNull(compound.get(MESH_TAG)));
            if (meshStack.getItem() instanceof MeshItem) {
                meshType = ((MeshItem) meshStack.getItem()).getMesh();
            }
        } else {
            meshStack = ItemStack.EMPTY;
        }

        if (compound.contains(BLOCK_TAG)) {
            blockStack = ItemStack.of((CompoundTag) Objects.requireNonNull(compound.get(BLOCK_TAG)));
        } else {
            blockStack = ItemStack.EMPTY;
        }

        progress = compound.getInt(PROGRESS_TAG);

        super.load(compound);
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag compound) {
        super.saveAdditional(compound);
        if (!meshStack.isEmpty()) {
            CompoundTag meshNBT = meshStack.save(new CompoundTag());
            compound.put(MESH_TAG, meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundTag blockNBT = blockStack.save(new CompoundTag());
            compound.put(BLOCK_TAG, blockNBT);
        }

        compound.putInt(PROGRESS_TAG, progress);
        setChanged();
    }

    @Override
    public void setRemoved() {
        //TODO remove complete method or do something else? (dupe with CarryOn mod)
        /*if (!level.isClientSide()) {
            removeMesh(false);
        }*/
        super.setRemoved();
    }

    public void insertSiftableBlock(ItemStack stack, Player player) {
        logger.debug("Insert Siftable Block: " + stack);
        if (!meshStack.isEmpty() && blockStack.isEmpty()) {
            blockStack = stack.copy();
            blockStack.setCount(1);
            if(!player.isCreative()) {
                stack.shrink(1);
            }
        }
    }

    public void activateSieve(Player player, boolean isWaterlogged) {
        logger.debug("Activate Sieve, isWaterlogged: " + isWaterlogged);

        // 4 ticks is the same period of holding down right click
        assert level != null;
        if (level.getLevelData().getGameTime() - lastSieveAction < 4) {
            //TODO deactivated macro punishment
            //Really good chance that they're using a macro
            /*if (player != null && level.getLevelData().getGameTime() - lastSieveAction == 0 && lastPlayer.equals(player.getUUID())) {
                player.setSecondsOnFire(1);

                Component message = new TextComponent("Autoclicker Bad").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(16711680)).withBold(true));

                player.sendMessage(message, player.getUUID());
            }*/
            return;
        }

        lastSieveAction = level.getLevelData().getGameTime();
        if (player != null) {
            lastPlayer = player.getUUID();
        }

        if (isReadyToSieve()) {
            progress += 1;

            if (progress >= Config.getMaxSieveClicks()) {
                logger.debug("Sieve progress complete");
                List<SieveRecipe> drops = ExNihiloRegistries.SIEVE_REGISTRY
                    .getDrops(((BlockItem) blockStack.getItem()).getBlock(), meshType, isWaterlogged);
                drops.forEach((entry -> entry.getRolls().forEach(meshWithChance -> {
                    if (random.nextFloat() <= meshWithChance.getChance()) {
                        logger.debug("Spawning Item: " + entry.getDrop());
                        level.addFreshEntity(new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 1.1F, worldPosition
                            .getZ() + 0.5F, entry.getDrop()));
                    }
                })));
                if(player != null) {
                    player.awardStat(ExNihiloStats.SIEVED);
                }
                resetSieve();
            }
        }
    }

    private void resetSieve() {
        logger.debug("Resetting sieve");
        if (Config.enableMeshDurability()) {
            logger.debug("Damaging mesh");
            assert level != null;
            meshStack.hurtAndBreak(1, new FakePlayer((ServerLevel) level, new GameProfile(UUID
                .randomUUID(), "Fake Player")), player -> logger.debug("Broken"));
        }
        blockStack = ItemStack.EMPTY;
        progress = 0;
        if (meshStack.isEmpty()) {
            logger.debug("Setting mesh to none, potential broken mesh");
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
        return (float)progress / Config.getMaxSieveClicks();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        if (!meshStack.isEmpty()) {
            CompoundTag meshNBT = meshStack.save(new CompoundTag());
            nbt.put(MESH_TAG, meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundTag blockNbt = blockStack.save(new CompoundTag());
            nbt.put(BLOCK_TAG, blockNbt);
        }
        nbt.putInt(PROGRESS_TAG, progress);

        return ClientboundBlockEntityDataPacket.create(this);
        //return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        CompoundTag nbt = packet.getTag();
        if (nbt != null) {
            if (nbt.contains(MESH_TAG)) {
                meshStack = ItemStack.of((CompoundTag) Objects.requireNonNull(nbt.get(MESH_TAG)));
                if (meshStack.getItem() instanceof MeshItem) {
                    meshType = ((MeshItem) meshStack.getItem()).getMesh();
                }
            } else {
                meshStack = ItemStack.EMPTY;
            }

            if (nbt.contains(BLOCK_TAG)) {
                blockStack = ItemStack.of((CompoundTag) Objects.requireNonNull(nbt.get(BLOCK_TAG)));
            } else {
                blockStack = ItemStack.EMPTY;
            }
            progress = nbt.getInt(PROGRESS_TAG);
        }
    }

    public EnumMesh getMesh() {
        return meshType;
    }
}
