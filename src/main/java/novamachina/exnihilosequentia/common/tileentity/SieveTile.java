package novamachina.exnihilosequentia.common.tileentity;

import com.mojang.authlib.GameProfile;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import novamachina.exnihilosequentia.api.ExNihiloRegistries;
import novamachina.exnihilosequentia.api.crafting.sieve.SieveRecipe;
import novamachina.exnihilosequentia.common.block.BlockSieve;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
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
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SieveTile extends TileEntity {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());
    @Nonnull private static final String BLOCK_TAG = "block";
    @Nonnull private static final String PROGRESS_TAG = "progress";
    @Nonnull private static final String MESH_TAG = "mesh";
    @Nonnull private final Random random = new Random();

    @Nonnull private ItemStack meshStack = ItemStack.EMPTY;
    @Nonnull private ItemStack blockStack = ItemStack.EMPTY;
    @Nonnull private EnumMesh meshType = EnumMesh.NONE;
    private float progress = 0;

    private long lastSieveAction = 0;
    private UUID lastPlayer;

    public SieveTile() {
        this(ExNihiloTiles.SIEVE.get());
    }

    public SieveTile(TileEntityType<? extends SieveTile> tileEntityType) {
        super(tileEntityType);
    }

    public void insertMesh(@Nonnull final ItemStack stack, @Nonnull final PlayerEntity player) {
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

    public void removeMesh(boolean rerenderSieve) {
        logger.debug("Remove mesh: Rerender Sieve: " + rerenderSieve);
        if (!meshStack.isEmpty()) {
            if (level != null) {
                level.addFreshEntity(
                        new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.5F, worldPosition.getZ() + 0.5F,
                                meshStack.copy()));
            }
            meshStack = ItemStack.EMPTY;
            meshType = EnumMesh.NONE;
            if (rerenderSieve) {
                setSieveState();
            }
        }
    }

    public void setSieveState() {
        logger.debug("Set Sieve State, Mesh: " + meshType);
        @Nonnull final BlockState state = getBlockState();
        if (state.getBlock() instanceof BlockSieve && level != null) {
            level.setBlockAndUpdate(getBlockPos(), state.setValue(BlockSieve.MESH, meshType));
        }
    }

    @Override
    public void load(@Nonnull final BlockState state, @Nonnull final CompoundNBT compound) {
        if (compound.contains(MESH_TAG)) {
            @Nullable final INBT meshTag = compound.get(MESH_TAG);
            if (meshTag != null) {
                meshStack = ItemStack.of((CompoundNBT) meshTag);
                if (meshStack.getItem() instanceof MeshItem) {
                    meshType = ((MeshItem) meshStack.getItem()).getMesh();
                }
            } else {
                meshStack = ItemStack.EMPTY;
            }
        } else {
            meshStack = ItemStack.EMPTY;
        }

        if (compound.contains(BLOCK_TAG)) {
            @Nullable final INBT blockTag = compound.get(BLOCK_TAG);
            if (blockTag != null) {
                blockStack = ItemStack.of((CompoundNBT) blockTag);
            } else {
                blockStack = ItemStack.EMPTY;
            }
        } else {
            blockStack = ItemStack.EMPTY;
        }

        progress = compound.getFloat(PROGRESS_TAG);

        super.load(state, compound);
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull final CompoundNBT compound) {
        if (!meshStack.isEmpty()) {
            CompoundNBT meshNBT = meshStack.save(new CompoundNBT());
            compound.put(MESH_TAG, meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundNBT blockNBT = blockStack.save(new CompoundNBT());
            compound.put(BLOCK_TAG, blockNBT);
        }

        compound.putFloat(PROGRESS_TAG, progress);

        return super.save(compound);
    }

    @Override
    public void setRemoved() {
        //TODO this one is doing that dupe with CarryOn
        /*if (!level.isClientSide()) {
            removeMesh(false);
        }*/
        super.setRemoved();
    }

    public void insertSiftableBlock(@Nonnull final ItemStack stack, @Nonnull final PlayerEntity player) {
        logger.debug("Insert Siftable Block: " + stack);
        if (!meshStack.isEmpty() && blockStack.isEmpty()) {
            blockStack = stack.copy();
            blockStack.setCount(1);
            if(!player.isCreative()) {
                stack.shrink(1);
            }
        }
    }

    public void activateSieve(@Nullable final PlayerEntity player, boolean isWaterlogged) {
        logger.debug("Activate Sieve, isWaterlogged: " + isWaterlogged);
        float fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, meshStack);
        float efficiency = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, meshStack);

        // 4 ticks is the same period of holding down right click
        if (level != null) {
            if (level.getLevelData().getGameTime() - lastSieveAction < 4) {
                // Really good chance that they're using a macro
                if (player != null && level.getLevelData().getGameTime() - lastSieveAction == 0 && lastPlayer.equals(player.getUUID())) {
                    ITextComponent message = new StringTextComponent("Autoclicker Bad").setStyle(Style.EMPTY.withColor(Color.fromRgb(16711680)).withBold(true));

                    player.sendMessage(message, Util.NIL_UUID);
                }
                return;
            }
        }

        if (level != null) {
            lastSieveAction = level.getLevelData().getGameTime();
        }
        if (player != null) {
            lastPlayer = player.getUUID();
        }

        if (isReadyToSieve()) {
            progress += 1 * (1+efficiency/5);

            if (progress >= Config.getMaxSieveClicks()) {
                logger.debug("Sieve progress complete");
                List<SieveRecipe> drops = ExNihiloRegistries.SIEVE_REGISTRY
                    .getDrops(((BlockItem) blockStack.getItem()).getBlock(), meshType, isWaterlogged);
                drops.forEach((entry -> entry.getRolls().forEach(meshWithChance -> {
                    if (random.nextFloat() <= meshWithChance.getChance() * (1F + (fortune/3))) {
                        logger.debug("Spawning Item: " + entry.getDrop());
                        level.addFreshEntity(new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 1.1F, worldPosition
                            .getZ() + 0.5F, entry.getDrop()));
                    }
                })));
                resetSieve();
            }
        }
    }

    private void resetSieve() {
        logger.debug("Resetting sieve");
        if (Config.enableMeshDurability()) {
            logger.debug("Damaging mesh");
            meshStack.hurtAndBreak(1, new FakePlayer((ServerWorld) level, new GameProfile(UUID
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

    @Nullable
    public ResourceLocation getTexture() {
        if (!blockStack.isEmpty()) {
            return blockStack.getItem().getRegistryName();
        }
        return null;
    }

    @Nonnull
    public ItemStack getBlockStack() {
        return blockStack;
    }

    public float getProgress() {
        return progress / Config.getMaxSieveClicks();
    }

    @Override
    @Nonnull
    public SUpdateTileEntityPacket getUpdatePacket() {
        @Nonnull final CompoundNBT nbt = new CompoundNBT();
        if (!meshStack.isEmpty()) {
            CompoundNBT meshNBT = meshStack.save(new CompoundNBT());
            nbt.put(MESH_TAG, meshNBT);
        }

        if (!blockStack.isEmpty()) {
            CompoundNBT blockNbt = blockStack.save(new CompoundNBT());
            nbt.put(BLOCK_TAG, blockNbt);
        }
        nbt.putFloat(PROGRESS_TAG, progress);

        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbt);
    }

    @Override
    public void onDataPacket(@Nonnull final NetworkManager net, @Nonnull final SUpdateTileEntityPacket packet) {
        CompoundNBT nbt = packet.getTag();
        if (nbt.contains(MESH_TAG)) {
            @Nullable final INBT meshTag = nbt.get(MESH_TAG);
            if (meshTag != null) {
                meshStack = ItemStack.of((CompoundNBT) meshTag);
                if (meshStack.getItem() instanceof MeshItem) {
                    meshType = ((MeshItem) meshStack.getItem()).getMesh();
                }
            } else {
                meshStack = ItemStack.EMPTY;
            }
        } else {
            meshStack = ItemStack.EMPTY;
        }

        if (nbt.contains(BLOCK_TAG)) {
            @Nullable final INBT blockTag = nbt.get(BLOCK_TAG);
            if (blockTag != null) {
                blockStack = ItemStack.of((CompoundNBT) blockTag);
            } else {
                blockStack = ItemStack.EMPTY;
            }
        } else {
            blockStack = ItemStack.EMPTY;
        }
        progress = nbt.getFloat(PROGRESS_TAG);
    }

    @Nonnull
    public EnumMesh getMesh() {
        return meshType;
    }
}
