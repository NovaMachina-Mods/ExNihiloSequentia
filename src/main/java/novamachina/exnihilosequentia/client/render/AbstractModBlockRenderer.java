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

import javax.annotation.Nullable;

public abstract class AbstractModBlockRenderer<T extends TileEntity> extends TileEntityRenderer<T> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    protected AbstractModBlockRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    protected void add(IVertexBuilder renderer, MatrixStack stack, VertexLocation vertexLocation, UVLocation uvLocation, Color color, int combinedLight) {
        renderer.vertex(stack.last().pose(), vertexLocation.getX(), vertexLocation.getY(), vertexLocation.getZ())
                .color(color.r, color.g, color.b, color.a)
                .uv(uvLocation.getU(), uvLocation.getV())
                .uv2(combinedLight)
                .normal(1, 0, 0)
                .endVertex();
    }

    //Added from ExCompressum, thanks to Blay09 for that piece of code :D
    @Nullable
    public static BlockState getStateFromItemStack(ItemStack itemStack) {
        if (itemStack.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) itemStack.getItem()).getBlock();
            try {
                return block.defaultBlockState();
            } catch (Exception e) {
                // In case of weirdness, don't crash! Just fallback to default.
            }
            return block.defaultBlockState();
        }
        return Blocks.AIR.defaultBlockState();
    }

    protected String resolveTexture(String path) {
        switch (path) {
            case "cactus": {
                return "cactus_top";
            }
            default: {
                return path;
            }
        }
    }
}
