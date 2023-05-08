package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
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
import net.minecraftforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.client.util.LiquidBlockVertexConsumer;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

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
    renderFluid(matrixStack, buffer, tileEntity);
    renderSolid(tileEntity, matrixStack, buffer, combinedLightIn, combinedOverlayIn);
  }

  private void renderFluid(
          @NotNull PoseStack matrixStack,
          @NotNull MultiBufferSource buffer,
          BaseCrucibleEntity tileEntity) {
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
          @NotNull BaseCrucibleEntity tileEntity,
          @NotNull PoseStack matrixStack,
          @NotNull MultiBufferSource buffer,
          int combinedLightIn,
          int combinedOverlayIn) {
    if (tileEntity.getSolidAmount() > 0) {
      BlockState state = null;
      if(tileEntity.getCurrentItem().getItem() instanceof BlockItem blockItem) {
        state = blockItem.getBlock().defaultBlockState();
      }
      matrixStack.pushPose();

      final float fillAmount = (Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.1875F;

      matrixStack.translate(0.125, 0.1875, 0.125);
      matrixStack.scale(0.75F, fillAmount, 0.75F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      if(state != null) {
        blockRenderer.renderSingleBlock(
                state,
                matrixStack,
                buffer,
                combinedLightIn,
                combinedOverlayIn,
                ModelData.EMPTY,
                RenderType.cutoutMipped());
      } else {
        logger.warn("BlockState was null");
      }
      matrixStack.popPose();
    }
  }
}
