package com.jawnnypoo.openmeh.util;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by John on 4/20/2015.
 */
public class ColorUtil {

    private static float[] hsv = new float[3];
    public static ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    public static LinearInterpolator linearInterpolator = new LinearInterpolator();
    public static AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();

    public static int getDarkerColor(int color) {
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return Color.HSVToColor(hsv);
    }

    public static void setStatusBarAndNavBarColor(Window window, int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
    }

    public static void animateStatusBarAndNavBarColors(Window window, int endColor, int duration) {
        if (Build.VERSION.SDK_INT >= 21) {
            statusBar(window, Color.BLACK, endColor, duration);
            navigationBar(window, Color.BLACK, endColor, duration);
        }
    }

    public static ColorStateList createColorStateList(int color) {
        return ColorStateList.valueOf(color);
    }

    public static ColorStateList createColorStateList(int color, int pressed) {
        return new ColorStateList(new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        }, new int[]{
                pressed,
                color
        });
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int getBackgroundColor(View v) {
        Drawable background = v.getBackground();
        if (background instanceof ColorDrawable) {
            return ((ColorDrawable) background).getColor();
        } else {
            return Color.TRANSPARENT;
        }
    }

    public static Animator backgroundColor(View v, int endColor, int duration) {
        ObjectAnimator oa = ObjectAnimator.ofObject(v, "backgroundColor", argbEvaluator,
                getBackgroundColor(v), endColor);
        oa.setDuration(duration);
        oa.setInterpolator(linearInterpolator);
        oa.start();
        return oa;
    }

    public static Animator statusBar(Window window, int startColor, int endColor, int duration) {
        ObjectAnimator oa = ObjectAnimator.ofObject(window, "statusBarColor", argbEvaluator,
                startColor, endColor);
        oa.setDuration(duration);
        oa.setInterpolator(linearInterpolator);
        oa.start();
        return oa;
    }

    public static Animator navigationBar(Window window, int startColor, int endColor, int duration) {
        ObjectAnimator oa = ObjectAnimator.ofObject(window, "navigationBarColor", argbEvaluator,
                startColor, endColor);
        oa.setDuration(duration);
        oa.setInterpolator(linearInterpolator);
        oa.start();
        return oa;
    }
}
