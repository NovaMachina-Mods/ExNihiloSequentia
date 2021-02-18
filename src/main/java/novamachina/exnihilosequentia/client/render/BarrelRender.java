package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import novamachina.exnihilosequentia.common.init.ExNihiloTiles;
import novamachina.exnihilosequentia.common.tileentity.barrel.AbstractBarrelTile;
import novamachina.exnihilosequentia.common.utility.Color;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class BarrelRender extends AbstractModBlockRenderer<AbstractBarrelTile> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public BarrelRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register() {
        logger.debug("Register barrel renderer");
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_ACACIA.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_BIRCH.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_DARK_OAK.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_JUNGLE.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_SPRUCE.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_OAK.get(), BarrelRender::new);
        ClientRegistry.bindTileEntityRenderer(ExNihiloTiles.BARREL_STONE.get(), BarrelRender::new);
    }

    @Override
    public void render(AbstractBarrelTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ResourceLocation inventoryTexture = tileEntity.getSolidTexture();
        ResourceLocation solidTexture = Blocks.OAK_LEAVES.getRegistryName();
        Fluid fluid = tileEntity.getFluid();
        ResourceLocation fluidTexture =
                fluid != null ? fluid.getAttributes().getStillTexture() : null;
        Color fluidColor =
                fluid != null ? new Color(fluid.getAttributes().getColor()) : Color.INVALID_COLOR;
        if (fluidTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucent());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                            fluidTexture);

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * tileEntity.getFluidProportion()) - 0.005f;

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getMinU(), sprite.getMaxV()),
                    fluidColor);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getMaxU(), sprite.getMaxV()),
                    fluidColor);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getMaxU(), sprite.getMinV()),
                    fluidColor);
            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getMinU(), sprite.getMinV()),
                    fluidColor);

            matrixStack.pop();
        }
        if (inventoryTexture != null) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                            new ResourceLocation(inventoryTexture.getNamespace(),
                                    "block/" + inventoryTexture.getPath()));

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = 1.0f - 0.005f;

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, fillAmount, 0.9375f), new UVLocation(sprite.getMinU(), sprite.getMaxV()),
                    Color.WHITE);
            add(builder, matrixStack, new VertexLocation(0.9375f, fillAmount, 0.9375f), new UVLocation(sprite.getMaxU(), sprite.getMaxV()),
                    Color.WHITE);
            add(builder, matrixStack, new VertexLocation(0.9375f, fillAmount, 0.0625f), new UVLocation(sprite.getMaxU(), sprite.getMinV()),
                    Color.WHITE);
            add(builder, matrixStack, new VertexLocation(0.0625f, fillAmount, 0.0625f), new UVLocation(sprite.getMinU(), sprite.getMinV()),
                    Color.WHITE);

            matrixStack.pop();
        }

        if (tileEntity.getSolidAmount() > 0) {
            IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());

            TextureAtlasSprite sprite = Minecraft.getInstance()
                    .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                            new ResourceLocation(solidTexture.getNamespace(),
                                    "block/" + solidTexture.getPath()));

            Color color = getBlockColor(solidTexture, tileEntity);

            // Subtract 0.005 to prevent texture fighting
            float fillAmount = (0.75f * Math.min(tileEntity.getSolidProportion(), 1.0F)) - 0.005f;

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getMinU(), sprite.getMaxV()),
                    color);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.9375f), new UVLocation(sprite.getMaxU(), sprite.getMaxV()),
                    color);
            add(builder, matrixStack, new VertexLocation(0.9375f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getMaxU(), sprite.getMinV()),
                    color);
            add(builder, matrixStack, new VertexLocation(0.0625f, 0.25f + fillAmount, 0.0625f), new UVLocation(sprite.getMinU(), sprite.getMinV()),
                    color);

            matrixStack.pop();
        }
    }

    private Color getBlockColor(ResourceLocation solidTexture,
                                AbstractBarrelTile tileEntity) {
        if (solidTexture != null && solidTexture.toString().contains("leaves")) {
            return new Color(
                    tileEntity.getWorld().getBiome(tileEntity.getPos()).getFoliageColor());
        }
        return Color.WHITE;
    }
}
