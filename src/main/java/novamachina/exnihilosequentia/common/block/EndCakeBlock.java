package novamachina.exnihilosequentia.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EndCakeBlock extends CakeBlock {

    public EndCakeBlock() {
        super(new BlockBuilder().getProperties().strength(0.5F));
    }


    /**
     * @deprecated Ask Mojang
     */
    @Nonnull
    @Deprecated
    @Override
    public ActionResultType use(@Nonnull final BlockState state, @Nonnull final World worldIn,
                                @Nonnull final BlockPos pos, @Nonnull final PlayerEntity player,
                                @Nonnull final Hand handIn, @Nonnull final BlockRayTraceResult blockRayTraceResult) {
        @Nonnull final ItemStack itemStack = player.getItemInHand(handIn);

        if (itemStack.isEmpty()) {
            return eatCake(worldIn, pos, state, player);
        } else {
            final int bites = state.getValue(BITES);

            if (itemStack.getItem() == Items.ENDER_EYE && bites > 0) {
                if (!worldIn.isClientSide()) {
                    worldIn.setBlockAndUpdate(pos, state.setValue(BITES, bites - 1));
                    itemStack.shrink(1);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.CONSUME;
    }

    private ActionResultType eatCake(@Nonnull final World worldIn, @Nonnull final BlockPos pos,
                                     @Nonnull final BlockState state, @Nonnull final PlayerEntity player) {
        if (!worldIn.isClientSide() && player.getVehicle() == null && player
                .isCreative() && worldIn instanceof ServerWorld && !player.isPassenger()) {
            @Nonnull final RegistryKey<World> registrykey = worldIn
                    .dimension() == World.OVERWORLD ? World.END : World.OVERWORLD;
            @Nullable final ServerWorld serverworld = ((ServerWorld) worldIn).getServer().getLevel(registrykey);
            if (serverworld == null) {
                return ActionResultType.FAIL;
            }

            player.changeDimension(serverworld);
        }

        if (!player.canEat(true) || player.getCommandSenderWorld().dimension() == World.END) {
            return ActionResultType.FAIL;
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            player.getFoodData().eat(2, 0.1F);
            int i = state.getValue(BITES);

            if (i < 6) {
                worldIn.setBlockAndUpdate(pos, state.setValue(BITES, i + 1));
            } else {
                worldIn.removeBlock(pos, false);
            }

            if (!worldIn.isClientSide() && player.getVehicle() == null && worldIn instanceof ServerWorld && !player
                    .isPassenger()) {
                @Nonnull final RegistryKey<World> registrykey = worldIn
                        .dimension() == World.END ? World.OVERWORLD : World.END;
                @Nullable final ServerWorld serverworld = ((ServerWorld) worldIn).getServer().getLevel(registrykey);
                if (serverworld == null) {
                    return ActionResultType.FAIL;
                }

                player.changeDimension(serverworld);
            }
        }
        return ActionResultType.SUCCESS;
    }
}
