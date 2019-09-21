package com.javaer.plusxposed.util.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by Administrator on 2018/7/26.
 */

public class ViewUtil {

    /**
     * 单个圆角度
     * @param strokeWidth 边缘线条粗细
     * @param roundRadius 圆角度
     * @param strokeColor 边缘颜色
     * @return 绘图状态
     */
    public static GradientDrawable createGradientDrawable(int strokeWidth, int roundRadius, int strokeColor){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(roundRadius);
        drawable.setStroke(strokeWidth, strokeColor);

        return drawable;
    }

    /**
     * 设置圆角数组
     * @param strokeWidth 边缘线条粗细
     * @param Radius 圆角度
     * @param strokeColor 边缘颜色
     * @return 绘图状态
     */
    public static GradientDrawable createGradientDrawableRadius(int strokeWidth, float[] Radius, int strokeColor){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadii(Radius);
        drawable.setStroke(strokeWidth, strokeColor);

        return drawable;
    }

    /**
     * 状态选择器
     * @param normal 正常颜色
     * @param pressed 按压颜色
     * @param focused 聚焦颜色
     * @param unable 不可点击
     * @return 颜色状态
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * dp转换
     * @param dp dp
     * @param context context
     * @return
     */
    public static int dp2px(float dp, Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
