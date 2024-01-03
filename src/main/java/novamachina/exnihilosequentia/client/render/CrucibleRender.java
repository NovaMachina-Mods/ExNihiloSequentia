package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.client.util.LiquidBlockVertexConsumer;
import novamachina.exnihilosequentia.world.level.block.entity.CrucibleBlockEntity;
import novamachina.novacore.client.renderer.blockentity.BlockEntityRenderer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrucibleRender extends BlockEntityRenderer<CrucibleBlockEntity> {

  private static Logger log = LoggerFactory.getLogger(CrucibleRender.class);

  public CrucibleRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcher) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends CrucibleBlockEntity> tileTileEntityType) {
    log.debug("Register crucible renderer, Type" + tileTileEntityType);
    BlockEntityRenderers.register(tileTileEntityType, CrucibleRender::new);
  }

  @Override
  public void render(
      @Nonnull final CrucibleBlockEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLightIn,
      final int combinedOverlayIn) {
    renderFluid(matrixStack, buffer, tileEntity);
    renderSolid(tileEntity, matrixStack, buffer, combinedLightIn, combinedOverlayIn);
  }

  private void renderFluid(
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      CrucibleBlockEntity tileEntity) {
    if (tileEntity.getFluidAmount() > 0) {
      BlockState state = tileEntity.getFluid().defaultFluidState().createLegacyBlock();
      matrixStack.pushPose();

      final float fillAmount = (Math.min(tileEntity.getFluidProportion(), 1.0F));

      matrixStack.translate(0.125, 0.1875, 0.125);
      matrixStack.scale(0.75F, fillAmount, 0.75F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      blockRenderer.renderLiquid(
          tileEntity.getBlockPos(),
          tileEntity.getLevel(),
          new LiquidBlockVertexConsumer(
              buffer.getBuffer(
                  ItemBlockRenderTypes.getRenderLayer(tileEntity.getFluid().defaultFluidState())),
              matrixStack,
              tileEntity.getBlockPos()),
          state,
          tileEntity.getFluid().defaultFluidState());
      matrixStack.popPose();
    }
  }

  private void renderSolid(
      @NotNull CrucibleBlockEntity tileEntity,
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      int combinedLightIn,
      int combinedOverlayIn) {
    if (tileEntity.getSolidAmount() > 0) {
      BlockState state = null;
      if (tileEntity.getCurrentItem().getItem() instanceof BlockItem blockItem) {
        state = blockItem.getBlock().defaultBlockState();
      }
      matrixStack.pushPose();

      final float fillAmount = (Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.1875F;

      matrixStack.translate(0.125, 0.1875, 0.125);
      matrixStack.scale(0.75F, fillAmount, 0.75F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      if (state != null) {
        blockRenderer.renderSingleBlock(
            state,
            matrixStack,
            buffer,
            combinedLightIn,
            combinedOverlayIn,
            ModelData.EMPTY,
            RenderType.cutoutMipped());
      } else {
        log.warn("BlockState was null");
      }
      matrixStack.popPose();
    }
  }
}
