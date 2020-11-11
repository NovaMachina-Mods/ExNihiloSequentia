package novamachina.exnihilosequentia.common.registries.crucible;

import novamachina.exnihilosequentia.common.tileentity.crucible.CrucilbeTypeEnum;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class Meltable {

    public static Meltable DEFAULT = new Meltable(0, null, CrucilbeTypeEnum.WOOD);

    private final int amount;
    private final ResourceLocation fluid;
    private final CrucilbeTypeEnum crucibleType;

    public Meltable(int amount, ResourceLocation fluid, CrucilbeTypeEnum crucibleType) {
        this.amount = amount;
        this.fluid = fluid;
        this.crucibleType = crucibleType;
    }

    public int getAmount() {
        return amount;
    }

    public Fluid getFluid() {
        return ForgeRegistries.FLUIDS.getValue(fluid);
    }

    public CrucilbeTypeEnum getCrucibleType() {
        return crucibleType;
    }
}
