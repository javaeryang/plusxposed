package com.javaer.plusxposed.libs.flyco.animation.ZoomEnter;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.MeasureSpec;

import com.javaer.plusxposed.libs.flyco.animation.BaseAnimatorSet;
public class ZoomInLeftEnter extends BaseAnimatorSet {
	public ZoomInLeftEnter() {
		duration = 750;
	}

	@Override
	public void setAnimation(View view) {
		view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		int w = view.getMeasuredWidth();

		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "translationX", -w, 48, 0),//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1));
	}
}
