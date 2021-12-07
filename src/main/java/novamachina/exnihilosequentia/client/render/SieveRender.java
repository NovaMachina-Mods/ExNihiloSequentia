package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class SieveRender extends AbstractModBlockRenderer<SieveTile> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public SieveRender(
            TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register(TileEntityType<? extends SieveTile> tileEntityType) {
        logger.debug("Registering sieve renderer");
        ClientRegistry.bindTileEntityRenderer(tileEntityType, SieveRender::new);
    }

    @Override
    public void render(SieveTile tileEntity, float partialTicks, MatrixStack matrixStack,
                       IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            BlockState state = getStateFromItemStack(tileEntity.getBlockStack());
            if (state != null) {
                matrixStack.pushPose();

                matrixStack.translate(0.01, 0.819, 0.01);
                matrixStack.scale(0.98F, 0.18F - tileEntity.getProgress() * 0.16F, 0.98F);

                BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
                blockRenderer.renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

                matrixStack.popPose();
            }
        }
    }
}
