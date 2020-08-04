package com.novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
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
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CrucibleRender extends AbstractModBlockRenderer<BaseCrucibleTile> {

    public CrucibleRender(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
    }

    public static void register(
        TileEntityType<? extends BaseCrucibleTile> tileTileEntityType) {
        ClientRegistry
            .bindTileEntityRenderer(tileTileEntityType, CrucibleRender::new);
    }

    @Override
    public void render(BaseCrucibleTile tileEntity, float partialTicks, MatrixStack matrixStack,
        IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ResourceLocation solidTexture = tileEntity.getSolidTexture();
        Fluid            fluid        = tileEntity.getFluid();
        ResourceLocation fluidTexture =
            fluid != null ? fluid.getAttributes().getStillTexture() : null;
        Color fluidColor =
            fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;
        Color blockColor = getBlockColor(solidTexture, tileEntity);
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
                fluidColor);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 1, sprite.getMaxU(), sprite.getMaxV(),
                fluidColor);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 0, sprite.getMaxU(), sprite.getMinV(),
                fluidColor);
            add(builder, matrixStack, 0, 0.25f + fillAmount, 0, sprite.getMinU(), sprite.getMinV(),
                fluidColor);

            matrixStack.pop();
        }
        if (solidTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                    new ResourceLocation(solidTexture.getNamespace(),
                        "block/" + solidTexture.getPath()));

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, 0, 0.25f + fillAmount, 1, sprite.getMinU(), sprite.getMaxV(),
                blockColor);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 1, sprite.getMaxU(), sprite.getMaxV(),
                blockColor);
            add(builder, matrixStack, 1, 0.25f + fillAmount, 0, sprite.getMaxU(), sprite.getMinV(),
                blockColor);
            add(builder, matrixStack, 0, 0.25f + fillAmount, 0, sprite.getMinU(), sprite.getMinV(),
                blockColor);

            matrixStack.pop();
        }
    }

    private Color getBlockColor(ResourceLocation solidTexture,
        BaseCrucibleTile tileEntity) {
        if (solidTexture != null) {
            if (solidTexture.toString().contains("leaves")) {
                return new Color(
                    tileEntity.getWorld().getBiome(tileEntity.getPos()).getFoliageColor());
            }
        }
        return Color.WHITE;
    }
}
