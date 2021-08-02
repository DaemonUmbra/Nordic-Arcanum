package com.lordskittles.nordicarcanum.client.utils;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer;
import com.lordskittles.arcanumapi.common.utilities.MathUtilities;

import java.util.Random;

public class Color {

    public static Color fromRandomHue(Random random, float hueMin, float hueMax, float saturation, float value, float alpha) {

        float hue = MathUtilities.map(random.nextFloat() * 6, 0, 6, hueMin, hueMax);

        return hsvToRgb(hue, saturation, value, alpha);
    }

    public static Color hsvToRgb(float hue, float saturation, float value, float alpha) {

        float[] rgbArray = ArcaneRenderer.HSVtoRGB(hue, saturation, value);

        return new Color(rgbArray[0], rgbArray[1], rgbArray[2], alpha);
    }

    public float r;
    public float g;
    public float b;
    public float a;

    public Color(float r, float g, float b, float a) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
