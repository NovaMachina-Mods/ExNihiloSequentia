package com.novamachina.exnihilosequentia.common.utility;

import java.util.Objects;
import net.minecraft.nbt.CompoundNBT;

public class Color {

    public static final Color INVALID_COLOR = new Color(-1, -1, -1, -1);
    public static final Color WHITE         = new Color(1, 1, 1, 1);

    public final float r;
    public final float g;
    public final float b;
    public final float a;

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
            this.r = (float) (color >> 16 & 255) / 255.0F;
            this.g = (float) (color >> 8 & 255) / 255.0F;
            this.b = (float) (color & 255) / 255.0F;
        } else {
            this.a = (float) (color >> 24 & 255) / 255.0F;
            this.r = (float) (color >> 16 & 255) / 255.0F;
            this.g = (float) (color >> 8 & 255) / 255.0F;
            this.b = (float) (color & 255) / 255.0F;
        }
    }

    public Color(String hex) {
        this(Integer.parseInt(hex, 16));
    }

    public static Color average(Color colorA, Color colorB, float percentage) {
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

    public static Color fromNBT(CompoundNBT compoundNBT) {
        if (compoundNBT.contains("color")) {
            CompoundNBT colorTag = compoundNBT.getCompound("color");
            float       a        = colorTag.getFloat("colorA");
            float       r        = colorTag.getFloat("colorR");
            float       b        = colorTag.getFloat("colorB");
            float       g        = colorTag.getFloat("colorG");

            return new Color(a, r, b, g);
        }
        return INVALID_COLOR;
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

    public String getAsHexNoAlpha() {
        return Integer.toHexString(toIntNoAlpha());
    }

    @Override
    public boolean equals(Object o) {
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

    @Override
    public int hashCode() {

        return Objects.hash(r, g, b, a);
    }

    @Override
    public String toString() {
        return "Color{" +
            "r=" + r +
            ", g=" + g +
            ", b=" + b +
            ", a=" + a +
            '}';
    }

    public void putIntoNBT(CompoundNBT compoundNBT) {
        CompoundNBT colorTag = new CompoundNBT();
        colorTag.putFloat("colorA", a);
        colorTag.putFloat("colorR", r);
        colorTag.putFloat("colorB", b);
        colorTag.putFloat("colorG", g);
        compoundNBT.put("color", colorTag);
    }
}
