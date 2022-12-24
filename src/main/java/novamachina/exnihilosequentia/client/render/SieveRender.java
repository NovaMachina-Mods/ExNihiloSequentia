package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;

public class SieveRender extends AbstractModBlockRenderer<SieveEntity> {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  public SieveRender(@Nonnull final BlockEntityRendererProvider.Context rendererDispatcherIn) {
    super();
  }

  public static void register(
      @Nonnull final BlockEntityType<? extends SieveEntity> tileEntityType) {
    logger.debug("Registering sieve renderer");
    BlockEntityRenderers.register(tileEntityType, SieveRender::new);
  }

  @Override
  public void render(
      @Nonnull final SieveEntity tileEntity,
      final float partialTicks,
      @Nonnull final PoseStack matrixStack,
      @Nonnull final MultiBufferSource buffer,
      final int combinedLight,
      final int combinedOverlay) {

    @Nullable final ResourceLocation blockTexture = tileEntity.getTexture();
    if (blockTexture != null) {
      @Nonnull final BlockState state = getStateFromItemStack(tileEntity.getBlockStack());
      matrixStack.pushPose();

      matrixStack.translate(0.01, 0.819, 0.01);
      matrixStack.scale(0.98F, 0.18F - tileEntity.getProgress() * 0.16F, 0.98F);

      BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
      blockRenderer.renderSingleBlock(
          state,
          matrixStack,
          buffer,
          LightTexture.FULL_BLOCK,
          combinedOverlay,
          ModelData.EMPTY,
          RenderType.cutoutMipped());

      matrixStack.popPose();
    }
  }
}
