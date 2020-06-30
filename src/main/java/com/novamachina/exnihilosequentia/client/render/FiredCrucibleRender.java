package com.novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.FiredCrucibleTile;
import com.novamachina.exnihilosequentia.common.utility.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class FiredCrucibleRender extends TileEntityRenderer<FiredCrucibleTile> {

    public FiredCrucibleRender(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    public static void register() {
        ClientRegistry
            .bindTileEntityRenderer(ModTiles.CRUCIBLE_FIRED.get(), FiredCrucibleRender::new);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u,
        float v, Color color) {
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
            .color(color.r, color.g, color.b, color.a)
            .tex(u, v)
            .lightmap(0, 240)
            .normal(1, 0, 0)
            .endVertex();
    }

    @Override
    public void render(FiredCrucibleTile tileEntity, float partialTicks, MatrixStack matrixStack,
        IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ResourceLocation solidTexture = tileEntity.getSolidTexture();
        Fluid            fluid        = tileEntity.getFluid();
        ResourceLocation fluidTexture =
            fluid != null ? fluid.getAttributes().getStillTexture() : null;
        Color            color        =
            fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;

        if (fluidTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucent());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                    fluidTexture);

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * tileEntity.getFluidProportion()) - 0.005f;

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, 0, 0.25f + fillAmount, 1, sprite.getMinU(), sprite.getMaxV(),
                color);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 1, sprite.getMaxU(), sprite.getMaxV(),
                color);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 0, sprite.getMaxU(), sprite.getMinV(),
                color);
            add(builder, matrixStack, 0, 0.25f + fillAmount, 0, sprite.getMinU(), sprite.getMinV(),
                color);

            matrixStack.pop();
        }
        if (solidTexture != null) {
            TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                    solidTexture);
        }
    }
}
