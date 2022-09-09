package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.logging.LogUtils;
import javax.annotation.Nonnull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import novamachina.exnihilosequentia.common.blockentity.IFluidContainer;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractModBlockRenderer<T extends BlockEntity>
    implements BlockEntityRenderer<T> {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogUtils.getLogger());

  protected AbstractModBlockRenderer() {
    super();
  }

  // Added from ExCompressum, thanks to Blay09 for that piece of code :D
  @Nonnull
  public static BlockState getStateFromItemStack(@Nonnull final ItemStack itemStack) {
    if (itemStack.getItem() instanceof BlockItem blockItem) {
      @Nonnull final Block block = blockItem.getBlock();
      try {
        return block.defaultBlockState();
      } catch (Exception e) {
        // In case of weirdness, don't crash! Just fallback to default.
      }
      return block.defaultBlockState();
    }
    return Blocks.AIR.defaultBlockState();
  }

  protected void add(
      @Nonnull final VertexConsumer renderer,
      @Nonnull final PoseStack stack,
      @Nonnull final VertexLocation vertexLocation,
      @Nonnull final UVLocation uvLocation,
      @Nonnull final Color color,
      final int combinedLight) {
    renderer
        .vertex(
            stack.last().pose(),
            vertexLocation.getX(),
            vertexLocation.getY(),
            vertexLocation.getZ())
        .color(color.r, color.g, color.b, color.a)
        .uv(uvLocation.getU(), uvLocation.getV())
        .uv2(combinedLight)
        .normal(1, 0, 0)
        .endVertex();
  }

  @Nonnull
  protected String resolveTexture(@Nonnull final String path) {
    if ("cactus".equals(path)) {
      return "cactus_top";
    }
    return path;
  }

  protected void renderFluid(
      @NotNull IFluidContainer tileEntity,
      @NotNull PoseStack matrixStack,
      @NotNull MultiBufferSource buffer,
      int combinedLightIn,
      @org.jetbrains.annotations.Nullable ResourceLocation fluidTexture,
      @NotNull Color fluidColor,
      UVLocation location) {
    if (fluidTexture != null) {
      @Nonnull final VertexConsumer builder = buffer.getBuffer(RenderType.translucent());

      @Nonnull
      final TextureAtlasSprite sprite =
          Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidTexture);

      // Subtract 0.005 to prevent texture fighting
      final float fillAmount = (0.75f * tileEntity.getFluidProportion()) - 0.005f;

      matrixStack.pushPose();
      matrixStack.translate(.5, .5, .5);
      matrixStack.translate(-.5, -.5, -.5);

      add(
          builder,
          matrixStack,
          new VertexLocation(location.getU(), 0.25f + fillAmount, location.getV()),
          new UVLocation(sprite.getU0(), sprite.getV1()),
          fluidColor,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(location.getV(), 0.25f + fillAmount, location.getV()),
          new UVLocation(sprite.getU1(), sprite.getV1()),
          fluidColor,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(location.getV(), 0.25f + fillAmount, location.getU()),
          new UVLocation(sprite.getU1(), sprite.getV0()),
          fluidColor,
          combinedLightIn);
      add(
          builder,
          matrixStack,
          new VertexLocation(location.getU(), 0.25f + fillAmount, location.getU()),
          new UVLocation(sprite.getU0(), sprite.getV0()),
          fluidColor,
          combinedLightIn);

      matrixStack.popPose();
    }
  }
}
