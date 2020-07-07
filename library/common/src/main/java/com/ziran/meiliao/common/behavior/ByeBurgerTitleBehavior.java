package com.ziran.meiliao.common.behavior;

/**
 * Created by wing on 11/5/16.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Bye Bye Burger Android Title Bar Behavior
 *
 * Created by wing on 11/4/16.
 */

public class ByeBurgerTitleBehavior extends ByeBurgerBehavior {
  public ByeBurgerTitleBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    if (canInit) {
      mAnimateHelper = TranslateAnimateHelper.get(child);
      canInit = false;
    }
    return super.layoutDependsOn(parent, child, dependency);
  }


  @Override protected void onNestPreScrollInit(View child) {

    if (isFirstMove) {
      isFirstMove = false;
      mAnimateHelper.setStartY(child.getY());
      mAnimateHelper.setMode(TranslateAnimateHelper.MODE_TITLE);
    }
  }
}

