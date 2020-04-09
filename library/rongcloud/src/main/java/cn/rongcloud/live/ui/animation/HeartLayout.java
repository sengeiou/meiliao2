/*
 * Copyright (C) 2015 tyrantgit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.rongcloud.live.ui.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.Random;

import cn.rongcloud.live.R;


public class HeartLayout extends RelativeLayout {

    private AbstractPathAnimator mAnimator;
    private Random mRandom;

    public HeartLayout(Context context) {
        this(context,null);
    }

    public HeartLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HeartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
        mRandom = new Random();
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HeartLayout, defStyleAttr, 0);

        mAnimator = new PathAnimator(AbstractPathAnimator.Config.fromTypeArray(a));

        a.recycle();
    }

    public AbstractPathAnimator getAnimator() {
        return mAnimator;
    }

    public void setAnimator(AbstractPathAnimator animator) {
        clearAnimation();
        mAnimator = animator;
    }

    public void clearAnimation() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).clearAnimation();
        }
        removeAllViews();
    }

    public void addHeart(int color) {
        HeartView heartView = new HeartView(getContext());
        heartView.setColor(color);
        mAnimator.start(heartView, this);
    }

    public void addHeart() {
        HeartView heartView = new HeartView(getContext());
        heartView.setColor(randomColor());
        mAnimator.start(heartView, this);
    }

    public void addHeart(int color, int heartResId, int heartBorderResId) {
        HeartView heartView = new HeartView(getContext());
        heartView.setColorAndDrawables(color, heartResId, heartBorderResId);
        mAnimator.start(heartView, this);
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }
}
