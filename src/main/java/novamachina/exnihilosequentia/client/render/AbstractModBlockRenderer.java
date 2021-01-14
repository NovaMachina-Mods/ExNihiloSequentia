package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import novamachina.exnihilosequentia.common.utility.Color;

public abstract class AbstractModBlockRenderer<T extends TileEntity> extends TileEntityRenderer<T> {
    protected AbstractModBlockRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    protected void add(IVertexBuilder renderer, MatrixStack stack, VertexLocation vertexLocation, UVLocation uvLocation, Color color) {
        renderer.pos(stack.getLast().getMatrix(), vertexLocation.getX(), vertexLocation.getY(), vertexLocation.getZ())
                .color(color.r, color.g, color.b, color.a)
                .tex(uvLocation.getU(), uvLocation.getV())
                .lightmap(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }
}
