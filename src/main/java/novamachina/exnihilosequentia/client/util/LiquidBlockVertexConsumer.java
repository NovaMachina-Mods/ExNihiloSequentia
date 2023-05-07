package novamachina.exnihilosequentia.client.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;

public record LiquidBlockVertexConsumer(VertexConsumer prior, PoseStack pose, BlockPos pos)
    implements VertexConsumer {

  @Override
  public VertexConsumer vertex(double x, double y, double z) {
    final float dx = pos.getX() & 15;
    final float dy = pos.getY() & 15;
    final float dz = pos.getZ() & 15;
    return prior.vertex(pose.last().pose(), (float) x - dx, (float) y - dy, (float) z - dz);
  }

  @Override
  public VertexConsumer color(int r, int g, int b, int a) {
    return prior.color(r, g, b, a);
  }

  @Override
  public VertexConsumer uv(float u, float v) {
    return prior.uv(u, v);
  }

  @Override
  public VertexConsumer overlayCoords(int u, int v) {
    return prior.overlayCoords(u, v);
  }

  @Override
  public VertexConsumer uv2(int u, int v) {
    return prior.uv2(u, v);
  }

  @Override
  public VertexConsumer normal(float x, float y, float z) {
    return prior.normal(pose.last().normal(), x, y, z);
  }

  @Override
  public void endVertex() {
    prior.endVertex();
  }

  @Override
  public void defaultColor(int r, int g, int b, int a) {
    prior.defaultColor(r, g, b, a);
  }

  @Override
  public void unsetDefaultColor() {
    prior.unsetDefaultColor();
  }
}
