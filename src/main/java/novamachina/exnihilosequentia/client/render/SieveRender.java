package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public class SieveRender extends AbstractModBlockRenderer<SieveTile> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public SieveRender(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    public static void register() {
        logger.debug("Registering sieve renderer");
        BlockEntityRenderers.register(ExNihiloTiles.SIEVE.get(), SieveRender::new);
    }

    @Override
    public void render(SieveTile tileEntity, float partialTicks, @Nonnull PoseStack matrixStack,
                       @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            BlockState state = getStateFromItemStack(tileEntity.getBlockStack());
            if (state != null) {
                matrixStack.pushPose();

                matrixStack.translate(0.01, 0.819, 0.01);
                matrixStack.scale(0.98F, 0.18F - tileEntity.getProgress() * 0.16F, 0.98F);

                BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
                blockRenderer.renderSingleBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

                matrixStack.popPose();
            }
        }
    }
}
