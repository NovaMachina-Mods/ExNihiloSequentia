package novamachina.exnihilosequentia.client.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;

public record LiquidBlockVertexConsumer(VertexConsumer prior, PoseStack pose, BlockPos pos)
    implements VertexConsumer {

  @Override
  public VertexConsumer addVertex(float x, float y, float z) {
    final float dx = pos.getX() & 15;
    final float dy = pos.getY() & 15;
    final float dz = pos.getZ() & 15;
    return prior.addVertex(pose.last().pose(), x - dx, y - dy, z - dz);
  }

  @Override
  public VertexConsumer setColor(int r, int g, int b, int a) {
    return prior.setColor(r, g, b, a);
  }

  @Override
  public VertexConsumer setUv(float u, float v) {
    return prior.setUv(u, v);
  }

  @Override
  public VertexConsumer setUv1(int u, int v) {
    return prior.setUv1(u, v);
  }

  @Override
  public VertexConsumer setUv2(int u, int v) {
    return prior.setUv2(u, v);
  }

  @Override
  public VertexConsumer setNormal(float x, float y, float z) {
    return prior.setNormal(pose.last(), x, y, z);
  }
}
