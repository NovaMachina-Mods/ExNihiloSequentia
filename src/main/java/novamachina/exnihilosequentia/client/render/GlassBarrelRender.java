package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Color;

public class GlassBarrelRender extends BarrelRender {
    public GlassBarrelRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(AbstractBarrelTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ResourceLocation inventoryTexture = tileEntity.getSolidTexture();
        ResourceLocation solidTexture = Blocks.OAK_LEAVES.getRegistryName();
        Fluid fluid = tileEntity.getFluid();
        ResourceLocation fluidTexture =
                fluid != null ? fluid.getAttributes().getStillTexture() : null;
        Color fluidColor =
                fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;
        if (fluidTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.translucent());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(
                            fluidTexture);

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * tileEntity.getFluidProportion()) - 0.005f;

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getU0(), sprite.getV1()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getU1(), sprite.getV1()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getU1(), sprite.getV0()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getU0(), sprite.getV0()),
                    fluidColor, combinedLightIn);

            matrixStack.popPose();
        }
        if (inventoryTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.solid());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(
                            new ResourceLocation(inventoryTexture.getNamespace(),
                                    "block/" + inventoryTexture.getPath()));

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = 1.0f - 0.005f;

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, fillAmount, 0.9375f), new UVLocation(sprite.getU0(), sprite.getV1()),
                    Color.WHITE, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, fillAmount, 0.9375f), new UVLocation(sprite.getU1(), sprite.getV1()),
                    Color.WHITE, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, fillAmount, 0.0625f), new UVLocation(sprite.getU1(), sprite.getV0()),
                    Color.WHITE, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.0625f, fillAmount, 0.0625f), new UVLocation(sprite.getU0(), sprite.getV0()),
                    Color.WHITE, combinedLightIn);

            matrixStack.popPose();
        }

        if (tileEntity.getSolidAmount() > 0) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.translucent());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(
                            new ResourceLocation(solidTexture.getNamespace(),
                                    "block/" + solidTexture.getPath()));

            Color color = getBlockColor(solidTexture, tileEntity);

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getU0(), sprite.getV1()),
                    color, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getU1(), sprite.getV1()),
                    color, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getU1(), sprite.getV0()),
                    color, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getU0(), sprite.getV0()),
                    color, combinedLightIn);

            matrixStack.popPose();
        }
    }
}
