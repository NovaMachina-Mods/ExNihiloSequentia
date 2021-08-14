package novamachina.exnihilosequentia.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nonnull;

public class EndCakeBlock extends CakeBlock {

    public EndCakeBlock() {
        super(new BlockBuilder().getProperties().strength(0.5F).sound(SoundType.WOOL));
    }


    /**
     * @deprecated Ask Mojang
     */
    @Deprecated
    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos,
                                             Player player, @Nonnull InteractionHand handIn, @Nonnull BlockHitResult blockRayTraceResult) {
        ItemStack itemStack = player.getItemInHand(handIn);

        if (itemStack.isEmpty()) {
            return eatCake(worldIn, pos, state, player);
        } else {
            int bites = state.getValue(BITES);

            if (itemStack.getItem() == Items.ENDER_EYE && bites > 0) {
                if (!worldIn.isClientSide()) {
                    worldIn.setBlockAndUpdate(pos, state.setValue(BITES, bites - 1));
                    itemStack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    private InteractionResult eatCake(Level worldIn, BlockPos pos, BlockState state,
                                     Player player) {
        if (!worldIn.isClientSide() && player.getVehicle() == null && player
                .isCreative() && worldIn instanceof ServerLevel && !player.isPassenger()) {
            ResourceKey<Level> registryKey = worldIn
                    .dimension() == Level.OVERWORLD ? Level.END : Level.OVERWORLD;
            ServerLevel serverWorld = ((ServerLevel) worldIn).getServer().getLevel(registryKey);
            if (serverWorld == null) {
                return InteractionResult.FAIL;
            }

            player.changeDimension(serverWorld);
        }

        if (!player.canEat(true) || player.getCommandSenderWorld().dimension() == Level.END) {
            return InteractionResult.FAIL;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            worldIn.playSound(player, pos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.0F);
            player.getFoodData().eat(2, 0.1F);
            int i = state.getValue(BITES);

            if (i < 6) {
                worldIn.setBlockAndUpdate(pos, state.setValue(BITES, i + 1));
            } else {
                worldIn.removeBlock(pos, false);
            }

            if (!worldIn.isClientSide() && player.getVehicle() == null && worldIn instanceof ServerLevel && !player
                    .isPassenger()) {
                ResourceKey<Level> registryKey = worldIn
                        .dimension() == Level.END ? Level.OVERWORLD : Level.END;
                ServerLevel serverWorld = ((ServerLevel) worldIn).getServer().getLevel(registryKey);
                if (serverWorld == null) {
                    return InteractionResult.FAIL;
                }

                player.changeDimension(serverWorld);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
