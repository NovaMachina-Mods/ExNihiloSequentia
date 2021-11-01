package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.api.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nullable;

public abstract class AbstractModBlockRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    protected AbstractModBlockRenderer(BlockEntityRendererProvider.Context context) {
    }

    protected void add(VertexConsumer renderer, PoseStack stack, VertexLocation vertexLocation, UVLocation uvLocation, Color color, int combinedLight) {
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
        if ("cactus".equals(path)) {
            return "cactus_top";
        }
        return path;
    }
}
