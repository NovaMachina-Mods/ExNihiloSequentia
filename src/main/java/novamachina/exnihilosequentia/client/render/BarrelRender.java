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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.client.util.LiquidBlockVertexConsumer;
import novamachina.exnihilosequentia.world.level.block.entity.BarrelBlockEntity;
import novamachina.novacore.client.renderer.blockentity.BlockEntityRenderer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarrelRender extends BlockEntityRenderer<BarrelBlockEntity> {

  private static Logger log = LoggerFactory.getLogger(BarrelRender.class);

  public BarrelRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcherIn) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends BarrelBlockEntity> tileEntityType) {
    log.debug("Register barrel renderer");
    BlockEntityRenderers.register(tileEntityType, BarrelRender::new);
  }

  @Override
  public void render(
      @Nonnull final BarrelBlockEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLightIn,
      final int combinedOverlayIn) {
    renderFluid(matrixStack, buffer, tileEntity);
    renderInventory(matrixStack, buffer, combinedLightIn, combinedOverlayIn, tileEntity);
    renderSolid(tileEntity, matrixStack, buffer, combinedLightIn, combinedOverlayIn);
  }

  private void renderSolid(
      @NotNull BarrelBlockEntity tileEntity,
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      int combinedLightIn,
      int combinedOverlayIn) {
    if (tileEntity.getSolidAmount() > 0) {
      BlockState state = Blocks.OAK_LEAVES.defaultBlockState();
      matrixStack.pushPose();

      final float fillAmount = (Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.0625F;

      matrixStack.translate(0.125, 0.0625, 0.125);
      matrixStack.scale(0.75F, fillAmount, 0.75F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      blockRenderer.renderSingleBlock(
          state,
          matrixStack,
          buffer,
          combinedLightIn,
          combinedOverlayIn,
          ModelData.EMPTY,
          RenderType.cutoutMipped());
      matrixStack.popPose();
    }
  }

  private void renderFluid(
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      BarrelBlockEntity tileEntity) {
    if (tileEntity.getFluidAmount() > 0) {
      BlockState state = tileEntity.getFluid().defaultFluidState().createLegacyBlock();
      matrixStack.pushPose();

      final float fillAmount = (Math.min(tileEntity.getFluidProportion(), 1.0F));

      matrixStack.translate(0.125, 0.0625, 0.125);
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

  private void renderInventory(
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      int combinedLightIn,
      int combinedOverlayIn,
      BarrelBlockEntity tileEntity) {
    if (tileEntity.getInventoryBlock() != ItemStack.EMPTY) {
      BlockState state = getStateFromItemStack(tileEntity.getInventoryBlock());
      matrixStack.pushPose();

      matrixStack.translate(0.125, 0.0625, 0.125);
      matrixStack.scale(0.75F, 0.9375F, 0.75F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      blockRenderer.renderSingleBlock(
          state,
          matrixStack,
          buffer,
          combinedLightIn,
          combinedOverlayIn,
          ModelData.EMPTY,
          RenderType.cutoutMipped());
      matrixStack.popPose();
    }
  }
}
