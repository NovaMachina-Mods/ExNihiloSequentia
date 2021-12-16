package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public abstract class AbstractModBlockRenderer<T extends TileEntity> extends TileEntityRenderer<T> {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    protected AbstractModBlockRenderer(@Nonnull final TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    protected void add(@Nonnull final IVertexBuilder renderer, @Nonnull final MatrixStack stack,
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
