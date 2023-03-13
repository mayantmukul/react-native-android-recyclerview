package com.example.list;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class RNDListViewManager extends SimpleViewManager<RNDListView> {
    public static final String NAME = "RNDListView";

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @NonNull
    @Override
    protected RNDListView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new RNDListView(themedReactContext);
    }
}
