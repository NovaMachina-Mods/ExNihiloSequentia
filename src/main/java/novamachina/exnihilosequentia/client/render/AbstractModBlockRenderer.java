package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public abstract class AbstractModBlockRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    protected AbstractModBlockRenderer(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super();
        //TODO do this properly
    }

    protected void add(@Nonnull final VertexConsumer renderer, @Nonnull final PoseStack stack,
                       @Nonnull final VertexLocation vertexLocation, @Nonnull final UVLocation uvLocation,
                       @Nonnull final Color color, final int combinedLight) {
        renderer.vertex(stack.last().pose(), vertexLocation.getX(), vertexLocation.getY(), vertexLocation.getZ())
                .color(color.r, color.g, color.b, color.a)
                .uv(uvLocation.getU(), uvLocation.getV())
                .uv2(combinedLight)
                .normal(1, 0, 0)
                .endVertex();
    }

    //Added from ExCompressum, thanks to Blay09 for that piece of code :D
    @Nonnull
    public static BlockState getStateFromItemStack(@Nonnull final ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem) {
            @Nonnull final Block block = ((BlockItem) itemStack.getItem()).getBlock();
            try {
                return block.defaultBlockState();
            } catch (Exception e) {
                // In case of weirdness, don't crash! Just fallback to default.
            }
            return block.defaultBlockState();
        }
        return Blocks.AIR.defaultBlockState();
    }

    @Nonnull
    protected String resolveTexture(@Nonnull final String path) {
        if ("cactus".equals(path)) {
            return "cactus_top";
        }
        return path;
    }
}
