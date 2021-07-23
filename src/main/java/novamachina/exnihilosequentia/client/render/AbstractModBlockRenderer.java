package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public abstract class AbstractModBlockRenderer<T extends Entity> extends EntityRenderer<T> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    protected AbstractModBlockRenderer(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    protected void add(VertexConsumer renderer, PoseStack stack, VertexLocation vertexLocation, UVLocation uvLocation, Color color, int combinedLight) {
        renderer.vertex(stack.last().pose(), vertexLocation.getX(), vertexLocation.getY(), vertexLocation.getZ())
                .color(color.r, color.g, color.b, color.a)
                .uv(uvLocation.getU(), uvLocation.getV())
                .uv2(combinedLight)
                .normal(1, 0, 0)
                .endVertex();
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
