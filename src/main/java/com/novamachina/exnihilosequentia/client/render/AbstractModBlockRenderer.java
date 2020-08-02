package com.novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novamachina.exnihilosequentia.common.utility.Color;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public abstract class AbstractModBlockRenderer<T extends TileEntity> extends TileEntityRenderer<T> {
    public AbstractModBlockRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    protected void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u,
                     float v, Color color) {
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
            .color(color.r, color.g, color.b, color.a)
            .tex(u, v)
            .lightmap(0, 240)
            .normal(1, 0, 0)
            .endVertex();
    }
}
