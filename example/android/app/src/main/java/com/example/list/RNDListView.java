package com.example.list;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Objects;

public class RNDListView extends RecyclerView {
    public RNDListView(@NonNull Context context) {
        super(context);
        setAdapter(new RNDListAdapter());
        setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    @NonNull
    public RNDListAdapter getAdapter() {
        return (RNDListAdapter) Objects.requireNonNull(super.getAdapter());
    }

    public void requestNextBatch() {
        ReactContext reactContext = (ReactContext) getContext();
        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "nextBatch", Arguments.createMap());
    }
}
