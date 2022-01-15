package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilosequentia.common.tileentity.crucible.BaseCrucibleTile;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CrucibleRender extends AbstractModBlockRenderer<BaseCrucibleTile> {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public CrucibleRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcher) {
        super(rendererDispatcher);
    }

    public static void register(@Nonnull final BlockEntityType<? extends BaseCrucibleTile> tileTileEntityType) {
        logger.debug("Register crucible renderer, Type" + tileTileEntityType);
        BlockEntityRenderers.register(tileTileEntityType, CrucibleRender::new);
    }

    @Override
    public void render(@Nonnull final BaseCrucibleTile tileEntity, final float partialTicks,
                       @Nonnull final PoseStack matrixStack, @Nonnull final MultiBufferSource buffer,
                       final int combinedLightIn, final int combinedOverlayIn) {
        @Nullable final ResourceLocation solidTexture = tileEntity.getSolidTexture();
        @Nullable final Fluid fluid = tileEntity.getFluid();
        @Nullable final ResourceLocation fluidTexture =
                fluid != null ? fluid.getAttributes().getStillTexture() : null;
        @Nonnull final Color fluidColor =
                fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;
        @Nonnull final Color blockColor = getBlockColor(solidTexture, tileEntity);
        if (fluidTexture != null) {
            @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.translucent());

            @Nonnull final TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(
                            fluidTexture);

            // Subtract 0.005 to prevent texture fighting
            final float fillAmount = (0.75f * tileEntity.getFluidProportion()) - 0.005f;

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0, 0.25f + fillAmount, 1), new UVLocation(sprite
                            .getU0(), sprite.getV1()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(1, 0.25f + fillAmount, 1), new UVLocation(sprite
                            .getU1(), sprite.getV1()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(1, 0.25f + fillAmount, 0), new UVLocation(sprite
                            .getU1(), sprite.getV0()),
                    fluidColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0, 0.25f + fillAmount, 0), new UVLocation(sprite
                            .getU0(), sprite.getV0()),
                    fluidColor, combinedLightIn);

            matrixStack.popPose();
        }
        if (solidTexture != null) {
            @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.solid());

            @Nonnull final TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(
                            new ResourceLocation(solidTexture.getNamespace(),
                                    "block/" + resolveTexture(solidTexture.getPath())));

            // Subtract 0.005 to prevent texture fighting
            final float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0, 0.25f + fillAmount, 1), new UVLocation(sprite
                            .getU0(), sprite.getV1()),
                    blockColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(1, 0.25f + fillAmount, 1), new UVLocation(sprite
                            .getU1(), sprite.getV1()),
                    blockColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(1, 0.25f + fillAmount, 0), new UVLocation(sprite
                            .getU1(), sprite.getV0()),
                    blockColor, combinedLightIn);
            add(builder, matrixStack, new VertexLocation(0, 0.25f + fillAmount, 0), new UVLocation(sprite
                            .getU0(), sprite.getV0()),
                    blockColor, combinedLightIn);

            matrixStack.popPose();
        }
    }

    @Nonnull
    private Color getBlockColor(ResourceLocation solidTexture,
                                BaseCrucibleTile tileEntity) {
        if (solidTexture != null && solidTexture.toString().contains("leaves") && tileEntity.getLevel() != null) {
            return new Color(tileEntity.getLevel().getBiome(tileEntity.getBlockPos()).getFoliageColor());
        }
        return Color.WHITE;
    }
}
