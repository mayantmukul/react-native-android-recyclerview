package com.example.list;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class RNDListViewManager extends ViewGroupManager<RNDListView> {
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

    @Override
    public void addView(RNDListView parent, View child, int index) {
        RNDListAdapter adapter = parent.getAdapter();
        adapter.addViewAtIndex(child, index);
        adapter.notifyItemInserted(index);

        // NOTE: Ugly hack to make the RecyclerView load again. Somehow just calling
        // notifyDataSetChanged() is not enough
        parent.scrollBy(0, 1);

        if (!adapter.isFilled()) {
            parent.requestNextBatch();
        }
    }

    @ReactProp(name = "size")
    public void setSize(RNDListView view, int size) {
        view.getAdapter().setLimit(size);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder().put(
                "nextBatch",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of(
                                "bubbled", "onNextBatch"
                        )
                )
        ).build();
    }
}
