package novamachina.exnihilosequentia.client.renderer.item.properties;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.Calendar;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public record Holiday() implements SelectItemModelProperty<String> {
  public static final SelectItemModelProperty.Type<Holiday, String> TYPE =
      SelectItemModelProperty.Type.create(MapCodec.unit(new Holiday()), Codec.STRING);

  @Nullable
  @Override
  public String get(
      ItemStack stack,
      @Nullable ClientLevel level,
      @Nullable LivingEntity entity,
      int seed,
      ItemDisplayContext context) {
    Calendar calendar = Calendar.getInstance();
    if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER) {
      return "halloween";
    } else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER
        && calendar.get(Calendar.DAY_OF_MONTH) >= 12
        && calendar.get(Calendar.DAY_OF_MONTH) <= 26) {
      return "christmas";
    }

    return null;
  }

  @Override
  public Type<? extends SelectItemModelProperty<String>, String> type() {
    return TYPE;
  }
}
