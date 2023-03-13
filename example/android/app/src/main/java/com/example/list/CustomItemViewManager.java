package com.example.list;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class CustomItemViewManager extends ViewGroupManager<CustomItemView>  {
    @NonNull
    @Override
    public String getName() {
        return "RNDDreamListItem";
    }

    @NonNull
    @Override
    protected CustomItemView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new CustomItemView(themedReactContext);
    }
}
