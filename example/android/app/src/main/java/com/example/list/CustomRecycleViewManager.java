package com.example.list;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class CustomRecycleViewManager extends ViewGroupManager<CustomRecycleView> {
    private static final String REACT_CLASS = "RCTRecycleview";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected CustomRecycleView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new CustomRecycleView(reactContext);
    }

    @Override
    public void addView(CustomRecycleView parent, View child, int index) {
        Log.d("ListManager", "Add View index " + index);
        Assertions.assertCondition(child instanceof RNRecycleviewItemview, "Views attached to RNRecycleview must be RNRecycleviewItemview views.");
        RNRecycleviewItemview item = (RNRecycleviewItemview) child;
        parent.addViewToAdapter(item, index);
        if(index == 499) {
            parent.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public int getChildCount(CustomRecycleView parent) {
        return parent.getChildCountFromAdapter();
    }

    @Override
    public View getChildAt(CustomRecycleView parent, int index) {
        return parent.getChildAtFromAdapter(index);
    }
}
