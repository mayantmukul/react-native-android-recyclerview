package com.example.list;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.views.view.ReactViewGroup;

public class RNDListView extends RecyclerView {
    public RNDListView(@NonNull Context context) {
        super(context);
        setAdapter(new RNDListAdapter());
        setLayoutManager(new LinearLayoutManager(context));
    }
}
