package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class BarrelRender extends AbstractModBlockRenderer<AbstractBarrelEntity> {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  public BarrelRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcherIn) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends AbstractBarrelEntity> tileEntityType) {
    logger.debug("Register barrel renderer");
    BlockEntityRenderers.register(tileEntityType, BarrelRender::new);
  }

  @Override
  public void render(
      @Nonnull final AbstractBarrelEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLightIn,
      final int combinedOverlayIn) {
    @Nullable final ResourceLocation inventoryTexture = tileEntity.getSolidTexture();
    @Nullable final ResourceLocation solidTexture = Blocks.OAK_LEAVES.getRegistryName();
    Fluid fluid = tileEntity.getFluid();
    @Nullable
    final ResourceLocation fluidTexture =
        fluid != null ? fluid.getAttributes().getStillTexture() : null;
    @Nullable
    final Color fluidColor =
        fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;
    renderFluid(
        tileEntity,
        matrixStack,
        buffer,
        combinedLightIn,
        fluidTexture,
        fluidColor,
        new UVLocation(0.0625f, 0.9375f));
    if (inventoryTexture != null) {
      @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.solid());

      @Nonnull
      final TextureAtlasSprite sprite =
          Minecraft.getInstance()
              .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
              .apply(
                  new ResourceLocation(
                      inventoryTexture.getNamespace(), "block/" + inventoryTexture.getPath()));

      // Subtract 0.005 to prevent texture fighting
      final float fillAmount = 1.0f - 0.005f;

      matrixStack.pushPose();
      matrixStack.translate(.5, .5, .5);
      matrixStack.translate(-.5, -.5, -.5);

      add(
          builder,
          matrixStack,
          new VertexLocation(0.0625f, fillAmount, 0.9375f),
          new UVLocation(sprite.getU0(), sprite.getV1()),
          Color.WHITE,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(0.9375f, fillAmount, 0.9375f),
          new UVLocation(sprite.getU1(), sprite.getV1()),
          Color.WHITE,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(0.9375f, fillAmount, 0.0625f),
          new UVLocation(sprite.getU1(), sprite.getV0()),
          Color.WHITE,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(0.0625f, fillAmount, 0.0625f),
          new UVLocation(sprite.getU0(), sprite.getV0()),
          Color.WHITE,
          combinedLightIn);

      matrixStack.popPose();
    }

    if (tileEntity.getSolidAmount() > 0) {
      @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.translucent());

      if (solidTexture != null) {
        @Nonnull
        final TextureAtlasSprite sprite =
            Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(
                    new ResourceLocation(
                        solidTexture.getNamespace(), "block/" + solidTexture.getPath()));

        @Nonnull final Color color = getBlockColor(solidTexture, tileEntity);

        // Subtract 0.005 to prevent texture fighting
        final float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

        matrixStack.pushPose();
        matrixStack.translate(.5, .5, .5);
        matrixStack.translate(-.5, -.5, -.5);

        add(
            builder,
            matrixStack,
            new VertexLocation(0.0625f, 0.25f + fillAmount, 0.9375f),
            new UVLocation(sprite.getU0(), sprite.getV1()),
            color,
            combinedLightIn);
        add(
            builder,
            matrixStack,
            new VertexLocation(0.9375f, 0.25f + fillAmount, 0.9375f),
            new UVLocation(sprite.getU1(), sprite.getV1()),
            color,
            combinedLightIn);
        add(
            builder,
            matrixStack,
            new VertexLocation(0.9375f, 0.25f + fillAmount, 0.0625f),
            new UVLocation(sprite.getU1(), sprite.getV0()),
            color,
            combinedLightIn);
        add(
            builder,
            matrixStack,
            new VertexLocation(0.0625f, 0.25f + fillAmount, 0.0625f),
            new UVLocation(sprite.getU0(), sprite.getV0()),
            color,
            combinedLightIn);
      }

      matrixStack.popPose();
    }
  }

  @Nonnull
  private Color getBlockColor(
      @Nullable final ResourceLocation solidTexture,
      @Nonnull final AbstractBarrelEntity tileEntity) {
    if (solidTexture != null
        && solidTexture.toString().contains("leaves")
        && tileEntity.getLevel() != null) {
      return new Color(
          tileEntity.getLevel().getBiome(tileEntity.getBlockPos()).value().getFoliageColor());
    }
    return Color.WHITE;
  }
}
