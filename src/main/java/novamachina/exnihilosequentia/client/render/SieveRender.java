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
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SieveRender extends AbstractModBlockRenderer<SieveTile> {
    @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public SieveRender(@Nonnull final TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register(@Nonnull final TileEntityType<? extends SieveTile> tileEntityType) {
        logger.debug("Registering sieve renderer");
        ClientRegistry.bindTileEntityRenderer(tileEntityType, SieveRender::new);
    }

    @Override
    public void render(@Nonnull final SieveTile tileEntity, final float partialTicks,
                       @Nonnull final MatrixStack matrixStack, @Nonnull final IRenderTypeBuffer buffer,
                       final int combinedLight, final int combinedOverlay) {

        @Nullable final ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            @Nonnull final BlockState state = getStateFromItemStack(tileEntity.getBlockStack());
            matrixStack.pushPose();

            matrixStack.translate(0.01, 0.819, 0.01);
            matrixStack.scale(0.98F, 0.18F - tileEntity.getProgress() * 0.16F, 0.98F);

            BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            blockRenderer.renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);

            matrixStack.popPose();
        }
    }
}
