package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class SieveRender extends AbstractModBlockRenderer<SieveTile> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public SieveRender(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register() {
        logger.debug("Registering sieve renderer");
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.SIEVE.get(), SieveRender::new);
    }

    @Override
    public void render(SieveTile tileEntity, float partialTicks, float combinedOverlay, PoseStack matrixStack,
                       MultiBufferSource buffer, int combinedLight) {

        ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(
                            new ResourceLocation(blockTexture.getNamespace(),
                                    "block/" + resolveTexture(blockTexture.getPath())));
            IVertexBuilder builder = buffer.getBuffer(RenderType.translucent());

            matrixStack.pushPose();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            double height = 0.99f - tileEntity.getProgress();
            float fillAmount = (float) (0.15625 * height + 0.84375);

            add(builder, matrixStack, new VertexLocation(0, fillAmount, 0), new UVLocation(sprite.getU0(), sprite.getV0()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(1, fillAmount, 0), new UVLocation(sprite.getU1(), sprite.getV0()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(1, fillAmount, 1), new UVLocation(sprite.getU1(), sprite.getV1()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(0, fillAmount, 1), new UVLocation(sprite.getU0(), sprite.getV1()), Color.WHITE, combinedLight);

            add(builder, matrixStack, new VertexLocation(0, fillAmount, 1), new UVLocation(sprite.getU0(), sprite.getV1()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(1, fillAmount, 1), new UVLocation(sprite.getU1(), sprite.getV1()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(1, fillAmount, 0), new UVLocation(sprite.getU1(), sprite.getV0()), Color.WHITE, combinedLight);
            add(builder, matrixStack, new VertexLocation(0, fillAmount, 0), new UVLocation(sprite.getU0(), sprite.getV0()), Color.WHITE, combinedLight);

            matrixStack.popPose();
        }
    }
}
