package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class CrucibleRender extends AbstractModBlockRenderer<BaseCrucibleEntity> {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public CrucibleRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcher) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends BaseCrucibleEntity> tileTileEntityType) {
    logger.debug("Register crucible renderer, Type" + tileTileEntityType);
    BlockEntityRenderers.register(tileTileEntityType, CrucibleRender::new);
  }

  @Override
  public void render(
      @Nonnull final BaseCrucibleEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLightIn,
      final int combinedOverlayIn) {
    @Nullable final ResourceLocation solidTexture = tileEntity.getSolidTexture();
    @Nullable final Fluid fluid = tileEntity.getFluid();
    @Nullable
    final ResourceLocation fluidTexture =
        fluid != null ? IClientFluidTypeExtensions.of(fluid).getStillTexture() : null;
    @Nonnull
    final Color fluidColor =
        fluid != null
            ? new Color(IClientFluidTypeExtensions.of(fluid).getTintColor())
            : Color.INVALID_COLOR;
    renderFluid(
        tileEntity,
        matrixStack,
        buffer,
        combinedLightIn,
        fluidTexture,
        fluidColor,
        new UVLocation(0, 1));
    if (solidTexture != null) {
      @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.solid());

      @Nonnull
      final TextureAtlasSprite sprite =
          Minecraft.getInstance()
              .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
              .apply(
                  new ResourceLocation(
                      solidTexture.getNamespace(),
                      "block/" + resolveTexture(solidTexture.getPath())));

      // Subtract 0.005 to prevent texture fighting
      final float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

      matrixStack.pushPose();
      matrixStack.translate(.5, .5, .5);
      matrixStack.translate(-.5, -.5, -.5);

      add(
          builder,
          matrixStack,
          new VertexLocation(0, 0.25f + fillAmount, 1),
          new UVLocation(sprite.getU0(), sprite.getV1()),
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(1, 0.25f + fillAmount, 1),
          new UVLocation(sprite.getU1(), sprite.getV1()),
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(1, 0.25f + fillAmount, 0),
          new UVLocation(sprite.getU1(), sprite.getV0()),
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(0, 0.25f + fillAmount, 0),
          new UVLocation(sprite.getU0(), sprite.getV0()),
          combinedLightIn);

      matrixStack.popPose();
    }
  }
}
