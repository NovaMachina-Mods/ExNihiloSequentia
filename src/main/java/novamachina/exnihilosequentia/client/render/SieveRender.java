package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.world.level.block.entity.SieveBlockEntity;
import novamachina.novacore.client.renderer.blockentity.AbstractBlockEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SieveRender extends AbstractBlockEntityRenderer<SieveBlockEntity> {

  private static Logger log = LoggerFactory.getLogger(SieveRender.class);

  public SieveRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcherIn) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends SieveBlockEntity> tileEntityType) {
    log.debug("Registering sieve renderer");
    BlockEntityRenderers.register(tileEntityType, SieveRender::new);
  }

  @Override
  public void render(
      @Nonnull final SieveBlockEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLight,
      final int combinedOverlay) {
    matrixStack.pushPose();
    matrixStack.mulPose(Axis.XP.rotationDegrees(90));
    matrixStack.translate(0.53, 0.53, -0.6875);
    ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    itemRenderer.renderStatic(
        tileEntity.getMeshStack(),
        ItemDisplayContext.FIXED,
        combinedLight,
        combinedOverlay,
        matrixStack,
        buffer,
        Minecraft.getInstance().level,
        0);
    matrixStack.popPose();

    if (tileEntity.getBlockStack() != ItemStack.EMPTY) {
      @Nonnull final BlockState state = getStateFromItemStack(tileEntity.getBlockStack());
      matrixStack.pushPose();

      matrixStack.translate(0.01, 0.819, 0.01);
      matrixStack.scale(0.98F, 0.18F - tileEntity.getProgress() * 0.16F, 0.98F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      blockRenderer.renderSingleBlock(
          state,
          matrixStack,
          buffer,
          combinedLight,
          combinedOverlay,
          ModelData.EMPTY,
          RenderType.cutoutMipped());
      matrixStack.popPose();
    }
  }
}
