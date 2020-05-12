package com.novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novamachina.exnihilosequentia.common.setup.ModTiles;
import com.novamachina.exnihilosequentia.common.tileentity.sieve.SieveTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class SieveRender extends TileEntityRenderer<SieveTile> {


    public SieveRender(
        TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(ModTiles.SIEVE_TILE.get(), SieveRender::new);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u,
        float v) {
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
            .color(1.0f, 1.0f, 1.0f, 1.0f)
            .tex(u, v)
            .lightmap(0, 240)
            .normal(1, 0, 0)
            .endVertex();
    }

    @Override
    public void render(SieveTile tileEntity, float partialTicks, MatrixStack matrixStack,
        IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                    new ResourceLocation(blockTexture.getNamespace(),
                        "block/" + blockTexture.getPath()));
            IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucent());

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            double height     = 0.99f - tileEntity.getProgress();
            float  fillAmount = (float) (0.15625 * height + 0.84375);

            add(builder, matrixStack, 0, fillAmount, 0, sprite.getMinU(), sprite.getMinV());
            add(builder, matrixStack, 1, fillAmount, 0, sprite.getMaxU(), sprite.getMinV());
            add(builder, matrixStack, 1, fillAmount, 1, sprite.getMaxU(), sprite.getMaxV());
            add(builder, matrixStack, 0, fillAmount, 1, sprite.getMinU(), sprite.getMaxV());

            add(builder, matrixStack, 0, fillAmount, 1, sprite.getMinU(), sprite.getMaxV());
            add(builder, matrixStack, 1, fillAmount, 1, sprite.getMaxU(), sprite.getMaxV());
            add(builder, matrixStack, 1, fillAmount, 0, sprite.getMaxU(), sprite.getMinV());
            add(builder, matrixStack, 0, fillAmount, 0, sprite.getMinU(), sprite.getMinV());

            matrixStack.pop();
        }
    }
}
