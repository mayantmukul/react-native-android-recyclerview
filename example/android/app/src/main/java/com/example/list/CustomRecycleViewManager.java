package com.example.list;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class CustomRecycleViewManager extends ViewGroupManager<RecyclerView> {
    private static final String REACT_CLASS = "RNDDreamList";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected RecyclerView createViewInstance(@NonNull ThemedReactContext reactContext) {
        RecyclerView recyclerView = new RecyclerView(reactContext);
        recyclerView.setHasFixedSize(false);
        ((DefaultItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(reactContext));
        recyclerView.setAdapter(new CustomAdapter());
        return recyclerView;
    }

    @Override
    public void addView(RecyclerView parent, View child, int index) {
        Log.d("ListManager", "Add View index " + index);
        Assertions.assertCondition(child instanceof CustomItemView, "Views attached to CustomRecycleView must be CustomItemView views.");
        CustomItemView item = (CustomItemView) child;
        ((CustomAdapter) parent.getAdapter()).addViewToAdapter(item);
        ((CustomAdapter) parent.getAdapter()).notifyItemChanged(index);
    }

//    @Override
//    public int getChildCount(RecyclerView parent) {
//        return ((CustomAdapter) parent.getAdapter()).getItemCount();
//    }
//
//    @Override
//    public View getChildAt(RecyclerView parent, int index) {
//        return ((CustomAdapter) parent.getAdapter()).getChildAtIndex(index);
//    }
}
