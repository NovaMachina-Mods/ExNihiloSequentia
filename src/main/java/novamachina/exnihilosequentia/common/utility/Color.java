package novamachina.exnihilosequentia.common.utility;

import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class Color {

    @Nonnull public static final Color INVALID_COLOR = new Color(-1, -1, -1, -1);
    @Nonnull public static final Color WHITE = new Color(1, 1, 1, 1);
    @Nonnull private static final String COLOR_TAG = "color";
    public final float a;
    public final float b;
    public final float g;
    public final float r;

    public Color(float red, float green, float blue, float alpha) {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
    }

    public Color(int color) {
        this(color, true);
    }

    public Color(int color, boolean ignoreAlpha) {
        if (ignoreAlpha) {
            this.a = 1.0f;
        } else {
            this.a = (float) (color >> 24 & 255) / 255.0F;
        }
        this.r = (float) (color >> 16 & 255) / 255.0F;
        this.g = (float) (color >> 8 & 255) / 255.0F;
        this.b = (float) (color & 255) / 255.0F;
    }

    public Color(@Nonnull final String hex) {
        this(Integer.parseInt(hex, 16));
    }

    @Nonnull
    public static Color average(@Nonnull final Color colorA, @Nonnull final Color colorB, float percentage) {
        float opposite = 1 - percentage;
        //Gamma correction

        float averageR = (float) Math
                .sqrt((colorA.r * colorA.r) * (opposite) + (colorB.r * colorB.r) * (percentage));
        float averageG = (float) Math
                .sqrt((colorA.g * colorA.g) * (opposite) + (colorB.r * colorB.g) * (percentage));
        float averageB = (float) Math
                .sqrt((colorA.b * colorA.b) * (opposite) + (colorB.r * colorB.b) * (percentage));
        float averageA = colorA.a * opposite + colorB.a * percentage;

        return new Color(averageR, averageG, averageB, averageA);
    }

    @Nonnull
    public static Color fromNBT(@Nonnull final CompoundTag compoundNBT) {
        if (compoundNBT.contains(COLOR_TAG)) {
            CompoundTag colorTag = compoundNBT.getCompound(COLOR_TAG);
            float a = colorTag.getFloat("colorA");
            float r = colorTag.getFloat("colorR");
            float b = colorTag.getFloat("colorB");
            float g = colorTag.getFloat("colorG");

            return new Color(a, r, b, g);
        }
        return INVALID_COLOR;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Color color = (Color) o;
        return Float.compare(color.r, r) == 0 &&
                Float.compare(color.g, g) == 0 &&
                Float.compare(color.b, b) == 0 &&
                Float.compare(color.a, a) == 0;
    }

    @Nonnull
    public String getAsHexNoAlpha() {
        return Integer.toHexString(toIntNoAlpha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    public void putIntoNBT(@Nonnull final CompoundTag compoundNBT) {
        CompoundTag colorTag = new CompoundTag();
        colorTag.putFloat("colorA", a);
        colorTag.putFloat("colorR", r);
        colorTag.putFloat("colorB", b);
        colorTag.putFloat("colorG", g);
        compoundNBT.put(COLOR_TAG, colorTag);
    }

    public int toInt() {
        int color = 0;
        color |= (int) (this.a * 255) << 24;
        color |= (int) (this.r * 255) << 16;
        color |= (int) (this.g * 255) << 8;
        color |= (int) (this.b * 255);
        return color;
    }

    public int toIntNoAlpha() {
        int color = 0;
        color |= (int) (this.r * 255) << 16;
        color |= (int) (this.g * 255) << 8;
        color |= (int) (this.b * 255);
        return color;
    }

    @Override
    @Nonnull
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
