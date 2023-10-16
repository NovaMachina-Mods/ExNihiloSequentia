package novamachina.exnihilosequentia.world.level.block.entity;

import com.mojang.authlib.GameProfile;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.FakePlayer;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.exnihilosequentia.world.level.block.SieveBlock;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SieveBlockEntity extends BlockEntity {

  private static Logger log = LoggerFactory.getLogger(SieveBlockEntity.class);

  @Nonnull private static final String BLOCK_TAG = "block";
  @Nonnull private static final String MESH_TAG = "mesh";
  @Nonnull private static final String PROGRESS_TAG = "progress";
  @Nonnull private final Random random = new SecureRandom();
  @Nonnull private ItemStack blockStack = ItemStack.EMPTY;
  private long lastSieveAction = 0;
  private ItemStack meshStack = ItemStack.EMPTY;
  @Nonnull private MeshType meshType = MeshType.NONE;
  private float progress = 0;

  public ItemStack getMeshStack() {
    return meshStack;
  }

  public SieveBlockEntity(
      BlockEntityType<? extends SieveBlockEntity> blockEntityType, BlockPos pos, BlockState state) {
    super(blockEntityType, pos, state);
  }

  public void activateSieve(@Nullable final Player player, boolean isWaterlogged) {
    log.debug("Activate Sieve, isWaterlogged: " + isWaterlogged);
    float fortune =
        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, meshStack);
    float efficiency =
        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, meshStack);

    // 4 ticks is the same period of holding down right click
    if (level != null && level.getLevelData().getGameTime() - lastSieveAction < 4) {
      // Really good chance that they're using a macro
      return;
    }

    if (level != null) {
      lastSieveAction = level.getLevelData().getGameTime();
    }

    if (isReadyToSieve()) {
      progress += 1 * (1 + efficiency / 5);

      if (progress >= Config.getMaxSieveClicks()) {
        log.debug("Sieve progress complete");
        List<SiftingRecipe> drops =
            ExNihiloRegistries.SIEVE_REGISTRY.getDrops(
                ((BlockItem) blockStack.getItem()).getBlock(), meshType, isWaterlogged);
        drops.forEach(
            (entry ->
                entry
                    .getRolls()
                    .forEach(
                        meshWithChance -> {
                          if (random.nextFloat()
                              <= meshWithChance.getChance() * (1F + (fortune / 3))) {
                            log.debug("Spawning Item: " + entry.getDrop());
                            level.addFreshEntity(
                                new ItemEntity(
                                    level,
                                    worldPosition.getX() + 0.5F,
                                    worldPosition.getY() + 1.1F,
                                    worldPosition.getZ() + 0.5F,
                                    entry.getDrop()));
                          }
                        })));
        resetSieve();
      }
    }
  }

  @Nonnull
  public ItemStack getBlockStack() {
    return blockStack;
  }

  @Nonnull
  public MeshType getMeshType() {
    return meshType;
  }

  public float getProgress() {
    return progress / Config.getMaxSieveClicks();
  }

  @Override
  @Nonnull
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public @NotNull CompoundTag getUpdateTag() {
    @Nonnull final CompoundTag nbt = new CompoundTag();
    if (!meshStack.isEmpty()) {
      CompoundTag meshNBT = meshStack.save(new CompoundTag());
      nbt.put(MESH_TAG, meshNBT);
    }

    if (!blockStack.isEmpty()) {
      CompoundTag blockNbt = blockStack.save(new CompoundTag());
      nbt.put(BLOCK_TAG, blockNbt);
    }
    nbt.putFloat(PROGRESS_TAG, progress);
    return nbt;
  }

  public void insertMesh(@Nonnull final ItemStack stack, @Nonnull final Player player) {
    log.debug("Insert Mesh: " + stack);
    MeshItem mesh = (MeshItem) stack.getItem();
    if (meshStack.isEmpty()) {
      meshStack = stack.copy();
      meshStack.setCount(1);
      if (!player.isCreative()) {
        stack.shrink(1);
      }
      meshType = mesh.getType();
      if (!isRemoved()) {
        setSieveState();
      }
      setChanged();
    }
  }

  public void insertSiftableBlock(@Nonnull final ItemStack stack, @Nonnull final Player player) {
    log.debug("Insert Siftable Block: " + stack);
    if (!meshStack.isEmpty() && blockStack.isEmpty()) {
      blockStack = stack.copy();
      blockStack.setCount(1);
      if (!player.isCreative()) {
        stack.shrink(1);
      }
    }
  }

  public boolean isReadyToSieve() {
    return !meshStack.isEmpty() && !blockStack.isEmpty();
  }

  @Override
  public void load(@Nonnull final CompoundTag compound) {
    if (compound.contains(MESH_TAG)) {
      @Nullable final Tag meshTag = compound.get(MESH_TAG);
      if (meshTag != null) {
        meshStack = ItemStack.of((CompoundTag) meshTag);
        if (meshStack.getItem() instanceof MeshItem meshItem) {
          meshType = meshItem.getType();
        }
      } else {
        meshStack = ItemStack.EMPTY;
      }
    } else {
      meshStack = ItemStack.EMPTY;
    }

    if (compound.contains(BLOCK_TAG)) {
      @Nullable final Tag blockTag = compound.get(BLOCK_TAG);
      if (blockTag != null) {
        blockStack = ItemStack.of((CompoundTag) blockTag);
      } else {
        blockStack = ItemStack.EMPTY;
      }
    } else {
      blockStack = ItemStack.EMPTY;
    }

    progress = compound.getFloat(PROGRESS_TAG);

    super.load(compound);
  }

  @Override
  public void onDataPacket(
      @Nonnull final Connection net, @Nonnull final ClientboundBlockEntityDataPacket packet) {
    CompoundTag nbt = packet.getTag();
    if (nbt.contains(MESH_TAG)) {
      @Nullable final Tag meshTag = nbt.get(MESH_TAG);
      if (meshTag != null) {
        meshStack = ItemStack.of((CompoundTag) meshTag);
        if (meshStack.getItem() instanceof MeshItem meshItem) {
          meshType = meshItem.getType();
        }
      } else {
        meshStack = ItemStack.EMPTY;
      }
    } else {
      meshStack = ItemStack.EMPTY;
    }

    if (nbt.contains(BLOCK_TAG)) {
      @Nullable final Tag blockTag = nbt.get(BLOCK_TAG);
      if (blockTag != null) {
        blockStack = ItemStack.of((CompoundTag) blockTag);
      } else {
        blockStack = ItemStack.EMPTY;
      }
    } else {
      blockStack = ItemStack.EMPTY;
    }
    progress = nbt.getFloat(PROGRESS_TAG);
  }

  public void removeMesh(boolean rerenderSieve) {
    log.debug("Remove mesh: Rerender Sieve: " + rerenderSieve);
    if (!meshStack.isEmpty()) {
      if (level != null) {
        level.addFreshEntity(
            new ItemEntity(
                level,
                worldPosition.getX() + 0.5F,
                worldPosition.getY() + 0.5F,
                worldPosition.getZ() + 0.5F,
                meshStack.copy()));
      }
      meshStack = ItemStack.EMPTY;
      meshType = MeshType.NONE;
      if (rerenderSieve) {
        setSieveState();
      }
    }
  }

  private void resetSieve() {
    log.debug("Resetting sieve");
    if (Config.enableMeshDurability()) {
      log.debug("Damaging mesh");
      meshStack.hurtAndBreak(
          1,
          new FakePlayer((ServerLevel) level, new GameProfile(UUID.randomUUID(), "Fake Player")),
          player -> log.debug("Broken"));
    }
    blockStack = ItemStack.EMPTY;
    progress = 0;
    if (meshStack.isEmpty()) {
      log.debug("Setting mesh to none, potential broken mesh");
      meshType = MeshType.NONE;
      setSieveState();
    }
  }

  @Override
  public void saveAdditional(@Nonnull final CompoundTag compound) {
    if (!meshStack.isEmpty()) {
      CompoundTag meshNBT = meshStack.save(new CompoundTag());
      compound.put(MESH_TAG, meshNBT);
    }

    if (!blockStack.isEmpty()) {
      CompoundTag blockNBT = blockStack.save(new CompoundTag());
      compound.put(BLOCK_TAG, blockNBT);
    }

    compound.putFloat(PROGRESS_TAG, progress);
  }

  @Override
  public void setRemoved() {
    // TODO this one is doing that dupe with CarryOn
    /*if (!level.isClientSide()) {
        removeMesh(false);
    }*/
    super.setRemoved();
  }

  public void setSieveState() {
    log.debug("Set Sieve State, Mesh: " + meshType);
    @Nonnull final BlockState state = getBlockState();
    if (state.getBlock() instanceof SieveBlock && level != null) {
      level.setBlockAndUpdate(getBlockPos(), state.setValue(SieveBlock.MESH, meshType));
    }
  }
}
